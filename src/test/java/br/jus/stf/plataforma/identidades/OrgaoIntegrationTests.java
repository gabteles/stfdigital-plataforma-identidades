package br.jus.stf.plataforma.identidades;

import static com.github.jsonj.tools.JsonBuilder.field;
import static com.github.jsonj.tools.JsonBuilder.object;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.sql.SQLException;

import org.junit.Before;
import org.junit.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;

import com.github.jsonj.JsonObject;

import br.jus.stf.core.framework.testing.IntegrationTestsSupport;
import br.jus.stf.core.framework.testing.oauth2.WithMockOauth2User;
import br.jus.stf.plataforma.ApplicationContextInitializer;

/**
 * Classe responsável pelos testes de integração da API de órgãos da Plataforma.
 * 
 * @author Rafael Alencar
 * 
 * @since 26.10.2016
 *
 */
@SpringBootTest(value = { "server.port:0", "eureka.client.enabled:false", "spring.cloud.config.enabled:false" },
        classes = ApplicationContextInitializer.class)
public class OrgaoIntegrationTests extends IntegrationTestsSupport {

    @Before
    public void limparAmbiente() throws SQLException {
        loadDataTests("limparAmbiente.sql");
    }

    @Test
    @WithMockOauth2User(value = "gestor-cadastro", components = "cadastrar-associado")
    public void cadastrarAssociadoComCargo() throws Exception {
        loadDataTests("cadastrarAssociado.sql");

        JsonObject associado = object(
                field("nome", "Paula"),
                field("cpf", "17602433530"),
                field("tipo", "REPRESENTANTE"),
                field("cargoFuncao", "Advogado"),
                field("orgao", 9003));

        mockMvc.perform(
                post("/api/orgaos/" + associado.get("orgao") + "/associados").contentType(MediaType.APPLICATION_JSON)
                        .content(associado.toString()))
                .andExpect(status().isOk());
    }

    @Test
    @WithMockOauth2User(value = "gestor-cadastro", components = "cadastrar-associado")
    public void cadastrarAssociadoSemCargo() throws Exception {
        loadDataTests("cadastrarAssociado.sql");

        JsonObject associado = object(
                field("nome", "Paula"),
                field("cpf", "17602433530"),
                field("tipo", "REPRESENTANTE"),
                field("orgao", 9003));

        mockMvc.perform(
                post("/api/orgaos/" + associado.get("orgao") + "/associados").contentType(MediaType.APPLICATION_JSON)
                        .content(associado.toString()))
                .andExpect(status().isOk());
    }

    @Test
    @WithMockOauth2User(value = "gestor-cadastro", components = "cadastrar-associado")
    public void naoDeveCadastrarAssociadoInvalido() throws Exception {
        JsonObject associado = object(
                field("cpf", "17602433530"),
                field("tipo", "REPRESENTANTE"),
                field("cargoFuncao", "Advogado"),
                field("orgao", 9003));

        mockMvc.perform(
                post("/api/orgaos/" + associado.get("orgao") + "/associados").contentType(MediaType.APPLICATION_JSON)
                        .content(associado.toString()))
                .andExpect(status().is4xxClientError());
    }

    @Test
    @WithMockOauth2User(value = "gestor-cadastro", components = "cadastrar-associado")
    public void naoDeveCadastrarAssociadoComIdsIncompativeis() throws Exception {
        JsonObject associado = object(
                field("nome", "Paula"),
                field("cpf", "17602433530"),
                field("tipo", "REPRESENTANTE"),
                field("orgao", 9003));

        mockMvc.perform(
                post("/api/orgaos/2/associados").contentType(MediaType.APPLICATION_JSON)
                        .content(associado.toString()))
                .andExpect(status().is4xxClientError());
    }

}