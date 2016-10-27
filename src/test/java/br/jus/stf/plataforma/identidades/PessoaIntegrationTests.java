package br.jus.stf.plataforma.identidades;

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
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;

import br.jus.stf.core.framework.testing.IntegrationTestsSupport;
import br.jus.stf.core.framework.testing.oauth2.WithMockOauth2User;
import br.jus.stf.plataforma.ApplicationContextInitializer;

/**
 * Classe responsável pelos testes de integração da API de pessoas da Plataforma.
 * 
 * @author Rafael Alencar
 * 
 * @since 09.06.2016
 *
 */
@SpringBootTest(value = { "server.port:0", "eureka.client.enabled:false", "spring.cloud.config.enabled:false" },
        classes = ApplicationContextInitializer.class)
@WithMockOauth2User(value = "gestor-cadastro")
public class PessoaIntegrationTests extends IntegrationTestsSupport {

    @Before
    public void limparAmbiente() throws SQLException {
        loadDataTests("limparAmbiente.sql");
    }

    @Test
    @WithMockOauth2User(value = "gestor-cadastro", components = "cadastrar-pessoas")
    public void cadastrarPessoas() throws Exception {
        loadDataTests("cadastrarPessoas.sql");

        String pessoas = "{\"nomes\":[\"Maria\",\"João\"]}";

        mockMvc.perform(post("/api/pessoas").contentType(MediaType.APPLICATION_JSON).content(pessoas))
                .andExpect(status().isOk()).andExpect(jsonPath("$", hasSize(2)))
                .andExpect(jsonPath("$[0].id", equalTo(9000))).andExpect(jsonPath("$[1].id", lessThanOrEqualTo(3)));
    }

    @Test
    @WithMockOauth2User(value = "gestor-cadastro", components = "cadastrar-pessoas")
    public void naoDeveCadastrarPessoasSemNomes() throws Exception {
        String pessoas = "{\"nomes\":[]}";

        mockMvc.perform(post("/api/pessoas").contentType(MediaType.APPLICATION_JSON).content(pessoas))
                .andExpect(status().is4xxClientError());
    }

    @Test
    @WithMockOauth2User(value = "gestor-cadastro", components = "cadastrar-pessoa")
    public void cadastrarPessoa() throws Exception {
        String pessoa =
                "{\"id\":9002,\"nome\":\"Joana\",\"cpf\":\"84548465146\",\"oab\":\"0123/DF\",\"email\":\"joana@stf.jus.br\",\"telefone\":\"(61)3217-1477\"}";

        mockMvc.perform(post("/api/pessoas/cadastro").contentType(MediaType.APPLICATION_JSON).content(pessoa))
                .andExpect(status().isOk()).andExpect(jsonPath("$.id", equalTo(9002)))
                .andExpect(jsonPath("$.nome", equalTo("Joana")));
    }

    @Test
    @WithMockOauth2User(value = "gestor-cadastro", components = "cadastrar-pessoas")
    public void alocarPessoas() throws Exception {
        loadDataTests("cadastrarPessoas.sql");

        String pessoas = "{\"nomes\":[\"Maria\",\"Carla\",\"Lara\"]}";

        mockMvc.perform(post("/api/pessoas/alocacoes-id").contentType(MediaType.APPLICATION_JSON).content(pessoas))
                .andExpect(status().isOk()).andExpect(jsonPath("$", hasSize(3)))
                .andExpect(jsonPath("$[0]", equalTo(9000)))
                .andExpect(jsonPath("$[2]", equalTo(9001)));
    }

    @Test
    @WithMockOauth2User(value = "gestor-cadastro", components = "cadastrar-pessoas")
    public void naoDeveAlocarPessoasSemNomes() throws Exception {
        String pessoas = "{\"nomes\":[]}";

        mockMvc.perform(post("/api/pessoas/alocacoes-id").contentType(MediaType.APPLICATION_JSON).content(pessoas))
                .andExpect(status().is4xxClientError());
    }

    @Test
    public void pesquisarPessoaPorId() throws Exception {
        loadDataTests("pesquisar.sql");

        mockMvc.perform(get("/api/pessoas/9005")).andExpect(status().isOk())
                .andExpect(jsonPath("$.nome", equalTo("Luiza")));
    }

    @Test
    public void consultarPessoasPorNumeroValidoNaBaseSTF() throws Exception {
        loadDataTests("consultarPessoasPorNumero.sql");

        mockMvc.perform(get("/api/pessoas").param("documento", "10213124955")).andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(1))).andExpect(jsonPath("$[0].nome", equalTo("Marta")))
                .andExpect(jsonPath("$[0].id", equalTo(9004)));
    }

    @Test
    public void consultarPessoasPorNumeroInvalido() throws Exception {
        mockMvc.perform(get("/api/pessoas").param("documento", "52213124954")).andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(0)));
    }

    @Test
    public void consultarPessoasPorNumeroValidoViaWSRF() throws Exception {
        mockMvc.perform(get("/api/pessoas").param("documento", "44777343600")).andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(0)));
    }

    @Test
    public void listarPessoas() throws Exception {
        loadDataTests("listar.sql");

        mockMvc.perform(get("/api/pessoas")).andExpect(status().isOk()).andExpect(jsonPath("$", hasSize(5)));
    }

}