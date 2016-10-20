package br.jus.stf.plataforma.identidades.infra;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
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
import br.jus.stf.core.shared.identidades.PapelId;
import br.jus.stf.core.shared.identidades.RecursoId;
import br.jus.stf.plataforma.ApplicationContextInitializer;
import br.jus.stf.plataforma.identidades.domain.model.Grupo;
import br.jus.stf.plataforma.identidades.domain.model.Papel;
import br.jus.stf.plataforma.identidades.domain.model.PapelRepository;
import br.jus.stf.plataforma.identidades.domain.model.Recurso;
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
public class PapelRepositoryUnitTests {

    @Autowired
    private TestEntityManager entityManager;

    private PapelRepository repository;

    private Long id = -1L;

    @Before
    public void setUp() {
        repository = new PapelRepositoryImpl(entityManager.getEntityManager());
    }

    @Test
    public void recuperarPapelPeloId() {
        Papel salvo = repository.save(entity(false, false));
        Papel recuperado = repository.findOne(salvo.identity());

        assertNotNull("Papel recuperado não pode ser nulo.", recuperado);
        assertEquals(String.format("Id deve ser igual a %d.", salvo.identity().toLong()),
                salvo.identity(), recuperado.identity());
        assertEquals(String.format("Nome do papel deve ser %s.", salvo.nome()), salvo.nome(),
                recuperado.nome());
        assertNull("Grupo deve ser nulo.", recuperado.grupo());
        assertTrue("Papel não deve possuir recursos.", recuperado.recursos().isEmpty());
    }

    @Test
    public void recuperarPapelPeloNome() {
        Papel salvo = repository.save(entity(true, true));
        Papel recuperado = repository.findOne(salvo.nome());

        assertNotNull("Papel recuperado não pode ser nulo.", recuperado);
        assertEquals(String.format("Id deve ser igual a %d.", salvo.identity().toLong()),
                salvo.identity(), recuperado.identity());
        assertEquals(String.format("Nome do papel deve ser %s.", salvo.nome()), salvo.nome(),
                recuperado.nome());
        assertEquals(String.format("Grupo deve ser %s.", salvo.grupo().nome()),
                salvo.grupo(), recuperado.grupo());
        assertEquals(String.format("Papel deve possuir %d recurso(s).", salvo.recursos().size()),
                salvo.recursos().size(), recuperado.recursos().size());
    }

    @Test
    public void listarPapeis() {
        int quantidadePreCarga = repository.findAll().size();

        repository.save(entity(false, false));
        repository.save(entity(false, false));

        List<Papel> lista = repository.findAll();

        assertNotNull("Lista de papeis não pode ser nula.", lista);
        assertEquals(String.format("Lista de papeis deve ter %d elementos.", quantidadePreCarga + 2),
                quantidadePreCarga + 2, lista.size());
    }

    @Test
    public void listarPapeisPeloRecurso() {
        RecursoId recurso = new RecursoId(1L);

        repository.save(entity(false, false));
        repository.save(entity(false, false));

        List<Papel> lista = repository.findPapelByRecurso(recurso);

        assertNotNull("Lista de papeis não pode ser nula.", lista);
        assertEquals("Lista de papeis deve ter dois elementos.", 2, lista.size());
    }

    @Test
    public void recuperarRecursosDoPapel() {
        Papel entity = entity(false, true);
        Papel salvo = repository.save(entity);
        List<Recurso> recursos = repository.findRecursoByPapel(entity.identity());

        assertNotNull("Recursos não pode ser nulo.", recursos);
        assertEquals("Recursos salvos e recuperados devem ser iguais", salvo.recursos(),
                new HashSet<Recurso>(recursos));
    }

    @Test
    public void recuperarIdsConsecutivos() {
        PapelId id = repository.nextId();

        assertEquals("Primeira execução id deve ser igual a 1.", new Long(1), id.toLong());

        id = repository.nextId();

        assertEquals("Segunda execução id deve ser igual a 2.", new Long(2), id.toLong());
    }

    @Test
    public void salvarPapelComGrupoESemRecursos() {
        Papel entity = entity(true, false);
        Papel salvo = repository.save(entity);

        assertNotNull("Papel salvo não pode ser nulo.", salvo);
        assertEquals(String.format("Id deve ser igual a %d.", entity.identity().toLong()),
                entity.identity(), salvo.identity());
        assertEquals(String.format("Nome deve ser igual a %s.", entity.nome()), entity.nome(),
                salvo.nome());
        assertEquals(String.format("Grupo deve ser %s.", entity.grupo().nome()),
                entity.grupo(), salvo.grupo());
        assertEquals("Recursos do papel devem ser apenas os do grupo.", salvo.grupo().recursos(), salvo.recursos());
    }

    @Test
    public void salvarPapelSemGrupoEComRecursos() {
        Papel entity = entity(false, true);
        Papel salvo = repository.save(entity);

        assertNotNull("Papel salvo não pode ser nulo.", salvo);
        assertEquals(String.format("Id deve ser igual a %d.", entity.identity().toLong()),
                entity.identity(), salvo.identity());
        assertEquals(String.format("Nome deve ser igual a %s.", entity.nome()), entity.nome(),
                salvo.nome());
        assertNull("Grupo deve ser nulo.", entity.grupo());
        assertEquals("Papel deve possuir um recurso.", 1, salvo.recursos().size());
    }

    @Test
    public void atualizarPapel() {
        Papel entity = entity(true, true);
        Papel salvo = repository.save(entity);

        assertNotNull("Papel salvo não pode ser nulo.", salvo);
        assertEquals(String.format("Nome deve ser igual a %s.", entity.nome()), entity.nome(),
                salvo.nome());
        assertEquals(String.format("Grupo deve ser %s.", entity.grupo().nome()),
                entity.grupo(), salvo.grupo());
        assertEquals(String.format("Papel não deve possuir %d recurso.", salvo.grupo().recursos().size() + 1),
                salvo.grupo().recursos().size() + 1, salvo.recursos().size());

        salvo = new Papel(salvo.identity(), "Papel alterado");
        repository.save(salvo);

        assertEquals("Nome deve ser igual a Papel alterado.", "Papel alterado",
                salvo.nome());
        assertNull("Grupo deve ser nulo.", salvo.grupo());
        assertTrue("Papel não deve possuir recursos.", salvo.recursos().isEmpty());
    }

    private Papel entity(boolean hasGrupo, boolean hasRecursos) {
        PapelId entityId = new PapelId(id--);
        Papel entity;

        if (hasGrupo) {
            Grupo grupo = entityManager.getEntityManager().find(Grupo.class, new GrupoId(1L));

            entity = new Papel(entityId, "Papel " + entityId.toString(), grupo);
        } else {
            entity = new Papel(entityId, "Papel " + entityId.toString());
        }

        if (hasRecursos) {
            Recurso recurso = new Recurso(new RecursoId(-1L), "Recurso fake", ResourceType.ACAO);

            entity.atribuirRecursos(new HashSet<Recurso>(Arrays.asList(recurso)));
        }

        return entity;
    }

}
