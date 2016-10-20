package br.jus.stf.plataforma.identidades.infra;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

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

import br.jus.stf.core.shared.identidades.RecursoId;
import br.jus.stf.plataforma.ApplicationContextInitializer;
import br.jus.stf.plataforma.identidades.domain.model.Recurso;
import br.jus.stf.plataforma.identidades.domain.model.RecursoRepository;
import br.jus.stf.plataforma.identidades.domain.model.ResourceType;

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
public class RecursoRepositoryUnitTests {

    @Autowired
    private TestEntityManager entityManager;

    private RecursoRepository repository;

    private Long id = -1L;

    @Before
    public void setUp() {
        repository = new RecursoRepositoryImpl(entityManager.getEntityManager());
    }

    @Test
    public void recuperarRecursoPeloId() {
        Recurso salvo = repository.save(entity());
        Recurso recuperado = repository.findOne(salvo.identity());

        assertNotNull("Recurso recuperado não pode ser nulo.", recuperado);
        assertEquals(String.format("Id deve ser igual a %d.", salvo.identity().toLong()),
                salvo.identity(), recuperado.identity());
        assertEquals(String.format("Nome do recurso deve ser %s.", salvo.nome()), salvo.nome(),
                recuperado.nome());
        assertEquals(String.format("Tipo deve ser %s.", salvo.tipo()), salvo.tipo(),
                recuperado.tipo());
    }

    @Test
    public void listarRecursos() {
        int quantidadePreCarga = repository.findAll().size();

        repository.save(entity());
        repository.save(entity());

        List<Recurso> lista = repository.findAll();

        assertNotNull("Lista de recursos não pode ser nula.", lista);
        assertEquals(String.format("Lista de recursos deve ter %d elementos.", quantidadePreCarga + 2),
                quantidadePreCarga + 2, lista.size());
    }

    @Test
    public void recuperarIdsConsecutivos() {
        RecursoId id = repository.nextId();

        assertEquals("Primeira execução id deve ser igual a 1.", new Long(1), id.toLong());

        id = repository.nextId();

        assertEquals("Segunda execução id deve ser igual a 2.", new Long(2), id.toLong());
    }

    @Test
    public void salvarRecurso() {
        Recurso entity = entity();
        Recurso salvo = repository.save(entity);

        assertNotNull("Recurso salvo não pode ser nulo.", salvo);
        assertEquals(String.format("Id deve ser igual a %d.", entity.identity().toLong()),
                entity.identity(), salvo.identity());
        assertEquals(String.format("Nome deve ser igual a %s.", entity.nome()), entity.nome(),
                salvo.nome());
        assertEquals(String.format("Tipo deve ser %s.", entity.tipo()), entity.tipo(),
                salvo.tipo());
    }

    @Test
    public void atualizarRecurso() {
        Recurso entity = entity();
        Recurso salvo = repository.save(entity);

        assertNotNull("Recurso salvo não pode ser nulo.", salvo);
        assertEquals(String.format("Nome deve ser igual a %s.", entity.nome()), entity.nome(),
                salvo.nome());
        assertEquals(String.format("Tipo deve ser %s.", entity.tipo()), entity.tipo(),
                salvo.tipo());

        salvo = new Recurso(salvo.identity(), "Recurso alterado", ResourceType.DASHBOARD);
        repository.save(salvo);

        assertEquals("Nome deve ser igual a Recurso alterado.", "Recurso alterado",
                salvo.nome());
        assertEquals("Tipo deve ser DASHBOARD.", ResourceType.DASHBOARD, salvo.tipo());
    }

    private Recurso entity() {
        RecursoId entityId = new RecursoId(id--);
        Recurso entity =
                new Recurso(entityId, "Recurso " + entityId.toString(), ResourceType.ACAO);

        return entity;
    }

}
