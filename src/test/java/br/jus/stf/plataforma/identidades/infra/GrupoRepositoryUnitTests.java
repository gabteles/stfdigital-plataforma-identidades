package br.jus.stf.plataforma.identidades.infra;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.TestExecutionListeners;
import org.springframework.test.context.jdbc.SqlScriptsTestExecutionListener;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.support.DependencyInjectionTestExecutionListener;
import org.springframework.test.context.transaction.TransactionalTestExecutionListener;

import br.jus.stf.core.shared.identidades.GrupoId;
import br.jus.stf.core.shared.identidades.RecursoId;
import br.jus.stf.plataforma.ApplicationContextInitializer;
import br.jus.stf.plataforma.identidades.domain.model.Grupo;
import br.jus.stf.plataforma.identidades.domain.model.GrupoRepository;
import br.jus.stf.plataforma.identidades.domain.model.Recurso;
import br.jus.stf.plataforma.identidades.domain.model.ResourceType;
import br.jus.stf.plataforma.identidades.domain.model.TipoGrupo;

/**
 * @author rafael.alencar
 * @since 20.10.2016
 */
@SpringBootTest(value = { "server.port:0", "eureka.client.enabled:false", "spring.cloud.discovery.enabled:false",
        "spring.cloud.config.enabled:false" }, classes = { ApplicationContextInitializer.class })
@TestExecutionListeners({ DependencyInjectionTestExecutionListener.class, TransactionalTestExecutionListener.class,
        SqlScriptsTestExecutionListener.class })
@RunWith(SpringRunner.class)
@DataJpaTest(showSql = false)
@ActiveProfiles("test")
public class GrupoRepositoryUnitTests {

    @Autowired
    private TestEntityManager entityManager;

    private GrupoRepository repository;

    private Long id = -1L;

    @Before
    public void setUp() {
        repository = new GrupoRepositoryImpl(entityManager.getEntityManager());
    }

    @Test
    public void recuperarGrupoPeloId() {
        Grupo salvo = repository.save(entity(false));
        Grupo recuperado = repository.findOne(salvo.identity());

        assertNotNull("Grupo recuperado não pode ser nulo.", recuperado);
        assertEquals(String.format("Tipo do grupo deve ser %s.", salvo.tipo()),
                salvo.tipo(), recuperado.tipo());
        assertEquals(String.format("Id deve ser igual a %d.", salvo.identity().toLong()),
                salvo.identity(), recuperado.identity());
        assertEquals(String.format("Nome do grupo deve ser %s.", salvo.nome()), salvo.nome(),
                recuperado.nome());
        assertTrue("Grupo não deve possuir recursos.", recuperado.recursos().isEmpty());
    }

    @Test
    public void recuperarGrupoPeloNomeETipo() {
        Grupo salvo = repository.save(entity(true));
        Grupo recuperado = repository.findOne(salvo.nome(), salvo.tipo());

        assertNotNull("Grupo recuperado não pode ser nulo.", recuperado);
        assertEquals(String.format("Tipo do grupo deve ser %s.", salvo.tipo()),
                salvo.tipo(), recuperado.tipo());
        assertEquals(String.format("Id deve ser igual a %d.", salvo.identity().toLong()),
                salvo.identity(), recuperado.identity());
        assertEquals(String.format("Nome do grupo deve ser %s.", salvo.nome()), salvo.nome(),
                recuperado.nome());
        assertEquals(String.format("Grupo deve possuir %d recurso(s).", salvo.recursos().size()),
                salvo.recursos().size(), recuperado.recursos().size());
    }

    @Test
    public void listarGrupos() {
        int quantidadePreCarga = repository.findAll().size();

        repository.save(entity(false));
        repository.save(entity(false));

        List<Grupo> lista = repository.findAll();

        assertNotNull("Lista de grupos não pode ser nula.", lista);
        assertEquals(String.format("Lista de grupos deve ter %d elementos.", quantidadePreCarga + 2),
                quantidadePreCarga + 2, lista.size());
    }

    @Test
    public void recuperarRecursosDoGrupo() {
        Grupo entity = entity(true);
        Grupo salvo = repository.save(entity);
        List<Recurso> recursos = repository.findRecursoByGrupo(entity.identity());

        assertNotNull("Recursos não pode ser nulo.", recursos);
        assertEquals("Recursos salvos e recuperados devem ser iguais", salvo.recursos(),
                new HashSet<Recurso>(recursos));
    }

    @Test
    public void recuperarIdsConsecutivos() {
        GrupoId id = repository.nextId();

        assertEquals("Primeira execução id deve ser igual a 1.", new Long(1), id.toLong());

        id = repository.nextId();

        assertEquals("Segunda execução id deve ser igual a 2.", new Long(2), id.toLong());
    }

    @Test
    public void salvarGrupoSemRecursos() {
        Grupo entity = entity(false);
        Grupo salvo = repository.save(entity);

        assertNotNull("Grupo salvo não pode ser nulo.", salvo);
        assertEquals(String.format("Id deve ser igual a %d.", entity.identity().toLong()),
                entity.identity(), salvo.identity());
        assertEquals(String.format("Nome deve ser igual a %s.", entity.nome()), entity.nome(),
                salvo.nome());
        assertEquals(String.format("Tipo do grupo deve ser %s.", entity.tipo()),
                entity.tipo(), salvo.tipo());
        assertTrue("Grupo não deve possuir recursos.", salvo.recursos().isEmpty());
    }

    @Test
    public void salvarGrupoComRecursos() {
        Grupo entity = entity(true);
        Grupo salvo = repository.save(entity);

        assertNotNull("Grupo salvo não pode ser nulo.", salvo);
        assertEquals(String.format("Id deve ser igual a %d.", entity.identity().toLong()),
                entity.identity(), salvo.identity());
        assertEquals(String.format("Nome deve ser igual a %s.", entity.nome()), entity.nome(),
                salvo.nome());
        assertEquals(String.format("Tipo do grupo deve ser %s.", entity.tipo()),
                entity.tipo(), salvo.tipo());
        assertEquals("Grupo deve possuir um recurso.", 1, salvo.recursos().size());
    }

    @Test
    public void atualizarGrupo() {
        Grupo entity = entity(true);
        Grupo salvo = repository.save(entity);

        assertNotNull("Grupo salvo não pode ser nulo.", salvo);
        assertEquals(String.format("Nome deve ser igual a %s.", entity.nome()), entity.nome(),
                salvo.nome());
        assertEquals("Grupo não deve possuir um recurso.", 1, salvo.recursos().size());

        salvo = new Grupo(salvo.identity(), "Grupo alterado", TipoGrupo.COMISSAO);
        repository.save(salvo);

        assertEquals("Nome deve ser igual a Grupo alterado.", "Grupo alterado",
                salvo.nome());
        assertEquals("Tipo do grupo deve ser COMISSAO.", TipoGrupo.COMISSAO, salvo.tipo());
        assertTrue("Grupo não deve possuir recursos.", salvo.recursos().isEmpty());
    }

    private Grupo entity(boolean hasRecursos) {
        GrupoId entityId = new GrupoId(id--);
        Grupo entity = new Grupo(entityId, "Grupo " + entityId.toString(), TipoGrupo.CONFIGURACAO);

        if (hasRecursos) {
            Recurso recurso = new Recurso(new RecursoId(-1L), "Recurso fake", ResourceType.ACAO);

            entity.atribuirRecursos(new HashSet<Recurso>(Arrays.asList(recurso)));
        }

        return entity;
    }

}
