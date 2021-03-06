package br.jus.stf.plataforma.identidades;

import static com.github.jsonj.tools.JsonBuilder.array;
import static com.github.jsonj.tools.JsonBuilder.field;
import static com.github.jsonj.tools.JsonBuilder.object;
import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.instanceOf;
import static org.hamcrest.Matchers.is;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;

import com.github.jsonj.JsonObject;

import br.jus.stf.core.framework.testing.IntegrationTestsSupport;
import br.jus.stf.core.framework.testing.oauth2.WithMockOauth2User;
import br.jus.stf.plataforma.ApplicationContextInitializer;
import net.minidev.json.JSONArray;

/**
 * Classe responsável pelos testes de integração da API de acessos da Plataforma.
 * 
 * @author Anderson.Araujo
 * 
 * @since 30.11.2015
 *
 */
@SpringBootTest(value = { "server.port:0", "eureka.client.enabled:false", "spring.cloud.config.enabled:false" },
        classes = ApplicationContextInitializer.class)
@WithMockOauth2User("usuario-teste")
public class AcessoIntegrationTests extends IntegrationTestsSupport {

    @Test
    public void recuperaInformacoesUsuarioLogado() throws Exception {
        mockMvc.perform(get("/api/acessos/usuario")).andExpect(status().isOk())
                .andExpect(jsonPath("$.id", is(9))).andExpect(jsonPath("$.nome", is("Usuario Teste")))
                .andExpect(jsonPath("$.setorLotacao.codigo", is(600000627)))
                .andExpect(jsonPath("$.setorLotacao.sigla", is("SEJ")))
                .andExpect(jsonPath("$.setorLotacao.nome", is("SECRETARIA JUDICIÁRIA")));
    }

    @Test
    public void listaGruposUsuario() throws Exception {
        Long gestorAutuacaoId = 8L;

        mockMvc.perform(get("/api/acessos/usuarios/" + gestorAutuacaoId + "/grupos"))
                .andExpect(status().isOk()).andExpect(jsonPath("$", hasSize(2)));
    }

    @Test
    public void listaPapeisUsuario() throws Exception {
        Long gestorAutuacaoId = 8L;

        mockMvc.perform(get("/api/acessos/usuarios/" + gestorAutuacaoId + "/papeis"))
                .andExpect(status().isOk()).andExpect(jsonPath("$", hasSize(2)));
    }

    @Test
    @WithMockOauth2User(value = "usuario-teste", components = "configurar-permissoes-usuario")
    public void configuraPermissoesUsuario() throws Exception {
        JsonObject permissoesUsuarioJson = object(
                field("idUsuario", 9),
                field("papeisAdicionados", array(1, 2, 3, 4)),
                field("gruposAdicionados", array(2)),
                field("recursosAdicionados", array(45)),
                field("papeisRemovidos", array(5)),
                field("gruposRemovidos", array(1, 2)),
                field("recursosRemovidos", array(51)));

        mockMvc.perform(
                post("/api/acessos/usuarios/" + permissoesUsuarioJson.get("idUsuario") + "/configuracoes-permissao")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(permissoesUsuarioJson.toString()))
                .andExpect(status().isOk());
    }

    @Test
    @WithMockOauth2User(value = "usuario-teste", components = "configurar-permissoes-usuario")
    public void naoDeveConfigurarPermissoesUsuarioComIdsIncompativeis() throws Exception {
        JsonObject permissoesUsuarioJson = object(
                field("idUsuario", 9),
                field("papeisAdicionados", array(1, 2, 3, 4)),
                field("gruposAdicionados", array(2)),
                field("recursosAdicionados", array(45)),
                field("papeisRemovidos", array(5)),
                field("gruposRemovidos", array(1, 2)),
                field("recursosRemovidos", array(51)));

        mockMvc.perform(
                post("/api/acessos/usuarios/2/configuracoes-permissao")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(permissoesUsuarioJson.toString()))
                .andExpect(status().is4xxClientError());
    }

    @Test
    @WithMockOauth2User(value = "usuario-teste", components = "configurar-permissoes-usuario")
    public void adicionaPermissoesUsuario() throws Exception {
        JsonObject permissoesUsuarioJson = object(
                field("idUsuario", 9),
                field("papeisAdicionados", array(1, 2, 3, 4)),
                field("gruposAdicionados", array(2)),
                field("recursosAdicionados", array(45)));

        mockMvc.perform(
                post("/api/acessos/usuarios/" + permissoesUsuarioJson.get("idUsuario") + "/configuracoes-permissao")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(permissoesUsuarioJson.toString()))
                .andExpect(status().isOk());
    }

    @Test
    public void tentaConfigurarPermissoesSemUsuario() throws Exception {
        JsonObject permissoesUsuarioJson = object(
                field("papeisAdicionados", array(1, 2, 3, 4)),
                field("gruposAdicionados", array(2)),
                field("recursosAdicionados", array(45)),
                field("papeisRemovidos", array(5)),
                field("gruposRemovidos", array(1, 2)),
                field("recursosRemovidos", array(51)));

        mockMvc.perform(post("/api/acessos/usuarios/9/configuracoes-permissao").contentType(MediaType.APPLICATION_JSON)
                .content(permissoesUsuarioJson.toString())).andExpect(status().is4xxClientError());
    }

    @Test
    @WithMockOauth2User(value = "usuario-teste", components = "cadastrar-usuario")
    public void cadastraNovoUsuario() throws Exception {
        JsonObject userJson = object(
                field("login", "joao.silva"),
                field("nome", "João da Silva"),
                field("pessoaId", 9000));

        mockMvc.perform(
                post("/api/acessos/usuarios").contentType(MediaType.APPLICATION_JSON).content(userJson.toString()))
                .andExpect(status().isOk()).andExpect(jsonPath("$.id", instanceOf(Integer.class)))
                .andExpect(jsonPath("$.login", is("joao.silva"))).andExpect(jsonPath("$.nome", is("João da Silva")))
                .andExpect(jsonPath("$.pessoaId", is(9000)));
    }

    @Test
    public void tentaCadastrarNovoUsuarioSemInformacoesObrigatorias() throws Exception {
        JsonObject userJson = object(
                field("login", "joao.silva"));

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
        Long gestorAutuacaoId = 8L;

        mockMvc.perform(get("/api/acessos/usuarios/" + gestorAutuacaoId + "/recursos"))
                .andExpect(status().isOk()).andExpect(jsonPath("$", hasSize(13)));
    }

    @Test
    public void listaPapeisRecurso() throws Exception {
        mockMvc.perform(get("/api/acessos/recursos/papeis").param("nome", "preautuar-originario").param("tipo", "ACAO"))
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

    @Test
    public void recuperaPermissoesPorUsuarioId() throws Exception {
        mockMvc.perform(get("/api/acessos/usuarios/1/permissoes"))
                .andExpect(status().isOk()).andExpect(jsonPath("$", hasSize(0)));
    }

}