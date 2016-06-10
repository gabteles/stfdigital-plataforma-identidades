package br.jus.stf.plataforma.userauthentication;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.Ignore;
import org.junit.Test;
import org.springframework.boot.test.SpringApplicationConfiguration;
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
@Ignore
@SpringApplicationConfiguration(ApplicationContextInitializer.class)
public class PessoaIntegrationTests extends IntegrationTestsSupport {
	
	@Test
	public void cadastrarPessoas() throws Exception {
		String pessoas = "{\"nomes\":[\"Maria\"]}";
		
		mockMvc.perform(
				post("/api/pessoas")
					.contentType(MediaType.APPLICATION_JSON)
					.content(pessoas))
				.andExpect(status().isOk());
	}
}
