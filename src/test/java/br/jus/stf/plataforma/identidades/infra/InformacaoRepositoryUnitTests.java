package br.jus.stf.plataforma.identidades.infra;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;

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

import br.jus.stf.core.shared.identidades.InformacaoId;
import br.jus.stf.core.shared.identidades.SegmentoId;
import br.jus.stf.core.shared.identidades.TipoInformacaoId;
import br.jus.stf.plataforma.ApplicationContextInitializer;
import br.jus.stf.plataforma.identidades.domain.model.Informacao;
import br.jus.stf.plataforma.identidades.domain.model.InformacaoRepository;
import br.jus.stf.plataforma.identidades.domain.model.Segmento;
import br.jus.stf.plataforma.identidades.domain.model.TipoInformacao;

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
public class InformacaoRepositoryUnitTests {

    @Autowired
    private TestEntityManager entityManager;

    private InformacaoRepository repository;

    private Long id = -1L;

    @Before
    public void setUp() {
        repository = new InformacaoRepositoryImpl(entityManager.getEntityManager());
    }

    @Test
    public void recuperarInformacaoPeloId() {
        Informacao salva = repository.save(entity(false));
        Informacao recuperado = repository.findOne(salva.identity());

        assertNotNull("Informação recuperada não pode ser nula.", recuperado);
        assertEquals(String.format("Id deve ser igual a %d.", salva.identity().toLong()),
                salva.identity(), recuperado.identity());
        assertEquals(String.format("Tipo deve ser %s.", salva.tipo().nome()), salva.tipo(),
                recuperado.tipo());
        assertNull("Segmento deve ser nulo.", recuperado.segmento());
        assertEquals(String.format("Identidade deve ser igual a %s.", salva.identity().toString()),
                salva.identidade(), recuperado.identidade());
    }

    @Test
    public void recuperarInformacaoPeloTipoESegmentoEIdentidade() {
        Informacao salva = repository.save(entity(true));
        Informacao recuperado =
                repository.findOne(salva.tipo().identity(), salva.segmento().identity(), salva.identidade());

        assertNotNull("Informação recuperada não pode ser nula.", recuperado);
        assertEquals(String.format("Id deve ser igual a %d.", salva.identity().toLong()),
                salva.identity(), recuperado.identity());
        assertEquals(String.format("Tipo deve ser %s.", salva.tipo().nome()), salva.tipo(),
                recuperado.tipo());
        assertEquals(String.format("Segmento deve ser %s.", salva.segmento().nome()),
                salva.segmento(), recuperado.segmento());
        assertEquals(String.format("Identidade deve ser igual a %s.", salva.identity().toString()),
                salva.identidade(), recuperado.identidade());
    }

    @Test
    public void recuperarInformacaoPeloTipoEIdentidade() {
        Informacao salva = repository.save(entity(false));
        Informacao recuperado =
                repository.findOne(salva.tipo().identity(), null, salva.identidade());

        assertNotNull("Informação recuperada não pode ser nula.", recuperado);
        assertEquals(String.format("Id deve ser igual a %d.", salva.identity().toLong()),
                salva.identity(), recuperado.identity());
        assertEquals(String.format("Tipo deve ser %s.", salva.tipo().nome()), salva.tipo(),
                recuperado.tipo());
        assertNull("Segmento deve ser nulo.", recuperado.segmento());
        assertEquals(String.format("Identidade deve ser igual a %s.", salva.identity().toString()),
                salva.identidade(), recuperado.identidade());
    }

    @Test
    public void recuperarIdsConsecutivos() {
        InformacaoId id = repository.nextId();

        assertEquals("Primeira execução id deve ser igual a 1.", new Long(1), id.toLong());

        id = repository.nextId();

        assertEquals("Segunda execução id deve ser igual a 2.", new Long(2), id.toLong());
    }

    @Test
    public void salvarInformacaoComSegmento() {
        Informacao entity = entity(true);
        Informacao salva = repository.save(entity);

        assertNotNull("Informação salva não pode ser nula.", salva);
        assertEquals(String.format("Id deve ser igual a %d.", entity.identity().toLong()),
                entity.identity(), salva.identity());
        assertEquals(String.format("Tipo deve ser %s.", entity.tipo().nome()), entity.tipo(),
                salva.tipo());
        assertEquals(String.format("Segmento deve ser %s.", entity.segmento().nome()),
                entity.segmento(), salva.segmento());
        assertEquals(String.format("Identidade deve ser igual a %s.", entity.identity().toString()),
                entity.identidade(), salva.identidade());
    }

    @Test
    public void salvarInformacaoSemSegmento() {
        Informacao entity = entity(false);
        Informacao salva = repository.save(entity);

        assertNotNull("Informação salva não pode ser nula.", salva);
        assertEquals(String.format("Id deve ser igual a %d.", entity.identity().toLong()),
                entity.identity(), salva.identity());
        assertEquals(String.format("Tipo deve ser %s.", entity.tipo().nome()), entity.tipo(),
                salva.tipo());
        assertNull("Segmento deve ser nulo.", salva.segmento());
        assertEquals(String.format("Identidade deve ser igual a %s.", entity.identity().toString()),
                entity.identidade(), salva.identidade());
    }

    @Test
    public void atualizarInformacao() {
        Informacao entity = entity(true);
        Informacao salva = repository.save(entity);

        assertNotNull("Informação salva não pode ser nula.", salva);
        assertEquals(String.format("Tipo deve ser %s.", entity.tipo().nome()), entity.tipo(),
                salva.tipo());
        assertEquals(String.format("Segmento deve ser %s.", entity.segmento().nome()),
                entity.segmento(), salva.segmento());
        assertEquals(String.format("Identidade deve ser igual a %s.", entity.identity().toString()),
                entity.identidade(), salva.identidade());

        TipoInformacao tipo = entityManager.getEntityManager().find(TipoInformacao.class, new TipoInformacaoId(1L));
        salva = new Informacao(salva.identity(), tipo, "123");
        repository.save(salva);

        assertEquals(String.format("Tipo deve ser %s.", tipo.nome()), tipo, salva.tipo());
        assertNull("Segmento deve ser nulo.", salva.segmento());
        assertEquals("Identidade deve ser igual a 123.", "123", salva.identidade());
    }

    @Test
    public void excluirInformacao() {
        Informacao salva = repository.save(entity(false));

        assertNotNull("Informação salva não pode ser nula.", salva);

        repository.delete(salva.identity());

        salva = repository.findOne(salva.identity());

        assertNull("Informação excluída deve ser nula.", salva);
    }

    private Informacao entity(boolean isSegmentada) {
        InformacaoId entityId = new InformacaoId(id--);
        Informacao entity;

        if (isSegmentada) {
            Segmento segmento = entityManager.getEntityManager().find(Segmento.class, new SegmentoId(1L));

            entity = new Informacao(entityId, segmento, entityId.toString());
        } else {
            TipoInformacao tipo = entityManager.getEntityManager().find(TipoInformacao.class, new TipoInformacaoId(1L));

            entity = new Informacao(entityId, tipo, entityId.toString());
        }

        return entity;
    }

}
