package br.jus.stf.plataforma.userauthentication;

import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.instanceOf;
import static org.hamcrest.Matchers.is;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;

import br.jus.stf.core.framework.testing.IntegrationTestsSupport;
import net.minidev.json.JSONArray;

/**
 * Classe responsável pelos testes de integração da API de acessos da Plataforma.
 * 
 * @author Anderson.Araujo
 * 
 * @since 30.11.2015
 *
 */
@SpringBootTest(value = {"server.port:0", "eureka.client.enabled:false"}, classes = ApplicationContextInitializer.class)
public class AcessoIntegrationTests extends IntegrationTestsSupport {
	
	private StringBuilder permissoesUsuario;
	private ObjectMapper mapper;
	
	@Before
	public void carregarDadosTeste() {
		permissoesUsuario = new StringBuilder();
		permissoesUsuario.append("{\"idUsuario\":9,");
		permissoesUsuario.append("\"papeisAdicionados\":[1,2,3,4],");
		permissoesUsuario.append("\"gruposAdicionados\":[2],");
		permissoesUsuario.append("\"papeisRemovidos\":[5],");
		permissoesUsuario.append("\"gruposRemovidos\":[1,2]}");
		
		mapper = new ObjectMapper();
	}
	@Ignore
	@Test
	public void recuperaInformacoesUsuarioLogado() throws Exception {
		mockMvc.perform(get("/api/acessos/usuario").header("login", "usuario-teste")).andExpect(status().isOk())
				.andExpect(jsonPath("$.id", is(9))).andExpect(jsonPath("$.nome", is("Usuario Teste")))
				.andExpect(jsonPath("$.setorLotacao.codigo", is(600000627)))
				.andExpect(jsonPath("$.setorLotacao.sigla", is("SEJ")))
				.andExpect(jsonPath("$.setorLotacao.nome", is("SECRETARIA JUDICIÁRIA")));
	}
	
	@Test
	public void listaGruposUsuario() throws Exception {
		mockMvc.perform(get("/api/acessos/usuarios/grupos").param("login", "gestor-autuacao"))
				.andExpect(status().isOk()).andExpect(jsonPath("$", hasSize(2)));
	}
	
	@Test
	public void listaPapeisUsuario() throws Exception {
		mockMvc.perform(get("/api/acessos/usuarios/papeis").param("login", "gestor-autuacao"))
				.andExpect(status().isOk()).andExpect(jsonPath("$", hasSize(2)));
	}
	
	@Test
	public void configuraPermissoesUsuario() throws Exception {
		mockMvc.perform(post("/api/acessos/permissoes/configuracao").contentType(MediaType.APPLICATION_JSON)
				.content(permissoesUsuario.toString())).andExpect(status().isOk());
	}

    @Test
	public void cadastraNovoUsuario() throws Exception {
		ObjectNode userJson = mapper.createObjectNode();
		
		userJson.put("login", "joao.silva");
		userJson.put("nome", "João da Silva");
		userJson.put("pessoaId", 9000);
		
		mockMvc.perform(
				post("/api/acessos/usuarios").contentType(MediaType.APPLICATION_JSON).content(userJson.toString()))
				.andExpect(status().isOk()).andExpect(jsonPath("$.id", instanceOf(Integer.class)))
				.andExpect(jsonPath("$.login", is("joao.silva"))).andExpect(jsonPath("$.nome", is("João da Silva")))
				.andExpect(jsonPath("$.pessoaId", is(9000)));
	}

    @Test
	public void tentaCadastrarNovoUsuarioSemInformacoesObrigatorias() throws Exception {
		ObjectNode userJson = mapper.createObjectNode();		
		userJson.put("login", "joao.silva");
		
		mockMvc.perform(
				post("/api/acessos/usuarios")
					.contentType(MediaType.APPLICATION_JSON)
					.content(userJson.toString()))
				.andExpect(status().is4xxClientError());
	}
	
	@Test
	public void listaTodosPapeis() throws Exception {
		mockMvc.perform(get("/api/acessos/papeis")).andExpect(jsonPath("$", instanceOf(JSONArray.class)));
	}

	@Test
	public void listaTodosGrupos() throws Exception {
		mockMvc.perform(get("/api/acessos/grupos")).andExpect(jsonPath("$", instanceOf(JSONArray.class)));
	}
	
	@Test
	public void recuperaUsuarioId() throws Exception {
		mockMvc.perform(get("/api/acessos/usuarios/id").param("login", "usuario-teste")).andExpect(status().isOk())
				.andExpect(jsonPath("$", is(9)));
	}
	
	@Test
	public void listaRecursosUsuario() throws Exception {
		mockMvc.perform(get("/api/acessos/usuarios/recursos").param("login", "gestor-autuacao"))
				.andExpect(status().isOk()).andExpect(jsonPath("$", hasSize(11)));
	}
	
	@Test
	public void listaPapeisRecurso() throws Exception {
		mockMvc.perform(get("/api/acessos/recursos/papeis").param("nome", "preautuar").param("tipo", "ACAO"))
				.andExpect(status().isOk()).andExpect(jsonPath("$", hasSize(1)));
	}
	
	@Test
	public void recuperaUsuarioPorLogin() throws Exception {
		mockMvc.perform(get("/api/acessos/usuarios").param("login", "usuario-teste")).andExpect(status().isOk())
				.andExpect(jsonPath("$.id", is(9))).andExpect(jsonPath("$.nome", is("Usuario Teste")))
				.andExpect(jsonPath("$.setorLotacao.codigo", is(600000627)))
				.andExpect(jsonPath("$.setorLotacao.sigla", is("SEJ")))
				.andExpect(jsonPath("$.setorLotacao.nome", is("SECRETARIA JUDICIÁRIA")));
	}
	
	@Test
	public void recuperaUsuarioPorUsuarioId() throws Exception {
		mockMvc.perform(get("/api/acessos/usuarios/9")).andExpect(status().isOk())
				.andExpect(jsonPath("$.login", is("usuario-teste"))).andExpect(jsonPath("$.nome", is("Usuario Teste")))
				.andExpect(jsonPath("$.setorLotacao.codigo", is(600000627)))
				.andExpect(jsonPath("$.setorLotacao.sigla", is("SEJ")))
				.andExpect(jsonPath("$.setorLotacao.nome", is("SECRETARIA JUDICIÁRIA")));
	}
	
}