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

import br.jus.stf.core.shared.identidades.SegmentoId;
import br.jus.stf.core.shared.identidades.TipoInformacaoId;
import br.jus.stf.plataforma.ApplicationContextInitializer;
import br.jus.stf.plataforma.identidades.domain.model.Segmento;
import br.jus.stf.plataforma.identidades.domain.model.SegmentoRepository;
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
public class SegmentoRepositoryUnitTests {

    @Autowired
    private TestEntityManager entityManager;

    private SegmentoRepository repository;

    private Long id = -1L;

    @Before
    public void setUp() {
        repository = new SegmentoRepositoryImpl(entityManager.getEntityManager());
    }

    @Test
    public void recuperarSegmentoPeloId() {
        Segmento salvo = repository.save(entity());
        Segmento recuperado = repository.findOne(salvo.identity());

        assertNotNull("Segmento recuperado não pode ser nulo.", recuperado);
        assertEquals(String.format("Tipo de informação deve ser %s.", salvo.tipo().nome()),
                recuperado.tipo(), recuperado.tipo());
        assertEquals(String.format("Id deve ser igual a %d.", salvo.identity().toLong()),
                salvo.identity(), recuperado.identity());
        assertEquals(String.format("Nome do segmento deve ser %s.", salvo.nome()), salvo.nome(),
                recuperado.nome());
    }

    @Test
    public void recuperarSegmentoPeloNomeETipo() {
        Segmento salvo = repository.save(entity());
        Segmento recuperado = repository.findOne(salvo.nome(), salvo.tipo());

        assertNotNull("Segmento recuperado não pode ser nulo.", recuperado);
        assertEquals(String.format("Tipo de informação deve ser %s.", salvo.tipo().nome()),
                salvo.tipo(), recuperado.tipo());
        assertEquals(String.format("Id deve ser igual a %d.", salvo.identity().toLong()),
                salvo.identity(), recuperado.identity());
        assertEquals(String.format("Nome do segmento deve ser %s.", salvo.nome()), salvo.nome(),
                recuperado.nome());
    }

    @Test
    public void listarSegmentos() {
        int quantidadePreCarga = repository.findAll().size();

        repository.save(entity());
        repository.save(entity());

        List<Segmento> lista = repository.findAll();

        assertNotNull("Lista de segmentos não pode ser nula.", lista);
        assertEquals(String.format("Lista de segmentos deve ter %d elementos.", quantidadePreCarga + 2),
                quantidadePreCarga + 2, lista.size());
    }

    public void recuperarIdsConsecutivos() {
        SegmentoId id = repository.nextId();

        assertEquals("Primeira execução id deve ser igual a 1.", new Long(1), id.toLong());

        id = repository.nextId();

        assertEquals("Segunda execução id deve ser igual a 2.", new Long(2), id.toLong());
    }

    @Test
    public void salvarSegmento() {
        Segmento entity = entity();
        Segmento salvo = repository.save(entity);

        assertNotNull("Segmento salvo não pode ser nulo.", salvo);
        assertEquals(String.format("Id deve ser igual a %d.", entity.identity().toLong()),
                entity.identity(), salvo.identity());
        assertEquals(String.format("Nome deve ser igual a %s.", entity.nome()), entity.nome(),
                salvo.nome());
        assertEquals(String.format("Tipo de informação deve ser %s.", entity.tipo().nome()),
                entity.tipo(), salvo.tipo());
    }

    @Test
    public void atualizarSegmento() {
        Segmento entity = entity();
        Segmento salvo = repository.save(entity);

        assertNotNull("Segmento salvo não pode ser nulo.", salvo);
        assertEquals(String.format("Nome deve ser igual a %s.", entity.nome()), entity.nome(),
                salvo.nome());
        assertEquals(String.format("Tipo de informação deve ser %s.", entity.tipo().nome()),
                entity.tipo(), salvo.tipo());

        TipoInformacao tipo = entityManager.getEntityManager().find(TipoInformacao.class, new TipoInformacaoId(2L));
        salvo = new Segmento(salvo.identity(), "Segmento alterado", tipo);
        repository.save(salvo);

        assertEquals("Nome deve ser igual a Segmento alterado.", "Segmento alterado",
                salvo.nome());
        assertEquals(String.format("Tipo de informação deve ser %s.", tipo.nome()), tipo, salvo.tipo());
    }

    private Segmento entity() {
        TipoInformacao tipo = entityManager.getEntityManager().find(TipoInformacao.class, new TipoInformacaoId(1L));
        SegmentoId entityId = new SegmentoId(id--);
        Segmento entity =
                new Segmento(entityId, "Segmento " + entityId.toString(), tipo);

        return entity;
    }

}
