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
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.context.ApplicationContextInitializer;
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
@Ignore
@SpringApplicationConfiguration(ApplicationContextInitializer.class)
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
	
	@Test
	public void recuperarInformacoesUsuarioLogado() throws Exception {
		
		//Recupera as informações do usuário.
		mockMvc.perform(get("/api/acessos/usuario").header("login", "usuario-teste")).andExpect(status().isOk())
			.andExpect(jsonPath("$.login", is("usuario-teste")))
			.andExpect(jsonPath("$.nome", is("usuario-teste")))
			.andExpect(jsonPath("$.setorLotacao.codigo", is(600000627)))
			.andExpect(jsonPath("$.setorLotacao.sigla", is("SEJ")))
			.andExpect(jsonPath("$.setorLotacao.nome", is("SECRETARIA JUDICIÁRIA")));
	}

	@Test
	public void carregarGruposUsuario() throws Exception {
		//Recupera as informações do usuário.
		mockMvc.perform(get("/api/acessos/usuarios/grupos")
			.header("login", "gestor-autuacao")
			.param("login", "gestor-autuacao")).andExpect(status().isOk())
			.andExpect(jsonPath("$", hasSize(2)));
	}
	
	@Test
	public void carregarPapeisUsuario() throws Exception {
		//Recupera as informações do usuário.
		mockMvc.perform(get("/api/acessos/usuarios/papeis")
			.header("login", "usuario-teste")
			.param("login", "usuario-teste")).andExpect(status().isOk())
			.andExpect(jsonPath("$", hasSize(1)));
	}
	
	@Test
	public void configurarPermissoesUsuario() throws Exception {
		//Realiza a autuação.
		mockMvc.perform(post("/api/acessos/permissoes/configuracao").contentType(MediaType.APPLICATION_JSON)
			.content(permissoesUsuario.toString())).andExpect(status().isOk());
	}
	
	@Test
	public void cadastrarNovoUsuario() throws Exception {
		ObjectNode userJson = mapper.createObjectNode();
		
		userJson.put("login", "joao.silva");
		userJson.put("nome", "João da Silva");
		userJson.put("email", "joao.silva@exemplo.com.br");
		userJson.put("cpf", "71405168633");
		userJson.put("telefone", "6133332222");
		
		mockMvc.perform(
				post("/api/acessos/usuarios")
					.contentType(MediaType.APPLICATION_JSON)
					.content(userJson.toString()))
				.andExpect(status().isOk())
				.andExpect(jsonPath("$.id", instanceOf(Integer.class)))
				.andExpect(jsonPath("$.login", is("joao.silva")))
				.andExpect(jsonPath("$.nome", is("João da Silva")));
	}
	
	@Test
	public void cadastrarNovoUsuarioSemInformacoesObrigatorias() throws Exception {
		ObjectNode userJson = mapper.createObjectNode();		
		userJson.put("login", "joao.silva");
		
		mockMvc.perform(
				post("/api/acessos/usuarios")
					.contentType(MediaType.APPLICATION_JSON)
					.content(userJson.toString()))
				.andExpect(status().is4xxClientError());
	}
	
	@Test
	public void listarTodosOsPapeis() throws Exception {
		mockMvc.perform(
			get("/api/acessos/papeis"))
			.andExpect(jsonPath("$", instanceOf(JSONArray.class)));
	}
	
	@Test
	public void listarTodosOsGrupos() throws Exception {
		mockMvc.perform(
			get("/api/acessos/grupos"))
			.andExpect(jsonPath("$", instanceOf(JSONArray.class)));
	}
}
