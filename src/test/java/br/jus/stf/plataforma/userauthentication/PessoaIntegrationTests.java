package br.jus.stf.plataforma.userauthentication;

import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.lessThanOrEqualTo;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.sql.SQLException;

import org.junit.Before;
import org.junit.Test;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.boot.test.WebIntegrationTest;
import org.springframework.http.MediaType;

import br.jus.stf.core.framework.testing.IntegrationTestsSupport;

/**
 * Classe responsável pelos testes de integração da API de identidade da Plataforma.
 * 
 * @author Rafael Alencar
 * 
 * @since 09.06.2016
 *
 */
@SpringApplicationConfiguration(ApplicationContextInitializer.class)
@WebIntegrationTest({"server.port:0", "eureka.client.enabled:false"})
public class PessoaIntegrationTests extends IntegrationTestsSupport {
	
	@Before
	public void limparAmbiente() throws SQLException {
		loadDataTests("limparAmbiente.sql");
	}
	
	@Test
	public void cadastrarPessoas() throws Exception {
		loadDataTests("cadastrarPessoas.sql");
		
		String pessoas = "{\"nomes\":[\"Maria\",\"João\"]}";
		
		mockMvc.perform(post("/api/pessoas").contentType(MediaType.APPLICATION_JSON).content(pessoas))
				.andExpect(status().isOk()).andExpect(jsonPath("$", hasSize(2)))
				.andExpect(jsonPath("$[0].id", equalTo(9000))).andExpect(jsonPath("$[1].id", lessThanOrEqualTo(3)));
	}
	
	@Test
	public void cadastrarPessoa() throws Exception {
		String pessoa = "{\"id\":9002,\"nome\":\"Joana\",\"cpf\":\"84548465146\",\"oab\":\"0123/DF\",\"email\":\"joana@stf.jus.br\",\"telefone\":\"(61)3217-1477\"}";
		
		mockMvc.perform(post("/api/pessoas/cadastrar").contentType(MediaType.APPLICATION_JSON).content(pessoa))
				.andExpect(status().isOk()).andExpect(jsonPath("$.id", equalTo(9002)))
				.andExpect(jsonPath("$.nome", equalTo("Joana")));
	}
	
	@Test
	public void alocarPessoas() throws Exception {
		loadDataTests("cadastrarPessoas.sql");
		
		String pessoas = "{\"nomes\":[\"Maria\",\"Carla\",\"Lara\"]}";
		
		mockMvc.perform(post("/api/pessoas/alocar").contentType(MediaType.APPLICATION_JSON).content(pessoas))
				.andExpect(status().isOk()).andExpect(jsonPath("$", hasSize(3)))
				.andExpect(jsonPath("$[0]", equalTo(9000))).andExpect(jsonPath("$[1]", lessThanOrEqualTo(3)))
				.andExpect(jsonPath("$[2]", equalTo(9001)));
	}
	
	@Test
	public void pesquisar() throws Exception {
		loadDataTests("pesquisar.sql");
		
		mockMvc.perform(get("/api/pessoas/9005")).andExpect(status().isOk())
				.andExpect(jsonPath("$.nome", equalTo("Luiza")));
	}
	
	@Test
	public void consultarPessoasPorNumero() throws Exception {
		loadDataTests("consultarPessoasPorNumero.sql");
		
		mockMvc.perform(get("/api/pessoas/documento/10213124955")).andExpect(status().isOk())
				.andExpect(jsonPath("$", hasSize(1))).andExpect(jsonPath("$[0].nome", equalTo("Marta")))
				.andExpect(jsonPath("$[0].id", equalTo(9004)));
	}
	
	@Test
	public void cadastrarAssociado() throws Exception {
		loadDataTests("cadastrarAssociado.sql");
		
		String associado = "{\"nome\":\"Paula\",\"cpf\":\"17602433530\",\"tipo\":\"REPRESENTANTE\",\"cargoFuncao\":\"Advogado\",\"orgao\":9003}";
		
		mockMvc.perform(post("/api/pessoas/associado").contentType(MediaType.APPLICATION_JSON).content(associado))
				.andExpect(status().isOk());
	}
	
	@Test
	public void listar() throws Exception {
		loadDataTests("listar.sql");
		
		mockMvc.perform(get("/api/pessoas")).andExpect(status().isOk()).andExpect(jsonPath("$", hasSize(5)));
	}
	
}