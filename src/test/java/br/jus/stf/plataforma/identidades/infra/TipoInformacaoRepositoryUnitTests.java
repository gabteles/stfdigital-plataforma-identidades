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

import br.jus.stf.core.shared.identidades.TipoInformacaoId;
import br.jus.stf.plataforma.ApplicationContextInitializer;
import br.jus.stf.plataforma.identidades.domain.model.TipoInformacao;
import br.jus.stf.plataforma.identidades.domain.model.TipoInformacaoRepository;

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
public class TipoInformacaoRepositoryUnitTests {

    @Autowired
    private TestEntityManager entityManager;

    private TipoInformacaoRepository repository;

    private Long id = -1L;

    @Before
    public void setUp() {
        repository = new TipoInformacaoRepositoryImpl(entityManager.getEntityManager());
    }

    @Test
    public void recuperarTipoInformacaoPeloId() {
        TipoInformacao salvo = repository.save(entity());
        TipoInformacao recuperado = repository.findOne(salvo.identity());

        assertNotNull("Tipo de informação recuperado não pode ser nulo.", recuperado);
        assertEquals(String.format("Id deve ser igual a %d.", salvo.identity().toLong()),
                salvo.identity(), recuperado.identity());
        assertEquals(String.format("Nome do tipo deve ser %s.", salvo.nome()), salvo.nome(),
                recuperado.nome());
    }

    @Test
    public void listarTiposInformacao() {
        int quantidadePreCarga = repository.findAll().size();

        repository.save(entity());
        repository.save(entity());

        List<TipoInformacao> lista = repository.findAll();

        assertNotNull("Lista de tipos não pode ser nula.", lista);
        assertEquals(String.format("Lista de tipos deve ter %d elementos.", quantidadePreCarga + 2),
                quantidadePreCarga + 2, lista.size());
    }

    public void recuperarIdsConsecutivos() {
        TipoInformacaoId id = repository.nextId();

        assertEquals("Primeira execução id deve ser igual a 1.", new Long(1), id.toLong());

        id = repository.nextId();

        assertEquals("Segunda execução id deve ser igual a 2.", new Long(2), id.toLong());
    }

    @Test
    public void salvarTipoInformacao() {
        TipoInformacao entity = entity();
        TipoInformacao salvo = repository.save(entity);

        assertNotNull("Tipo de informação salvo não pode ser nulo.", salvo);
        assertEquals(String.format("Id deve ser igual a %d.", entity.identity().toLong()),
                entity.identity(), salvo.identity());
        assertEquals(String.format("Nome deve ser igual a %s.", entity.nome()), entity.nome(),
                salvo.nome());
    }

    @Test
    public void atualizarTipoInformacao() {
        TipoInformacao entity = entity();
        TipoInformacao salvo = repository.save(entity);

        assertNotNull("Tipo de informação salvo não pode ser nulo.", salvo);
        assertEquals(String.format("Nome deve ser igual a %s.", entity.nome()), entity.nome(),
                salvo.nome());

        salvo = new TipoInformacao(salvo.identity(), "Tipo alterado");
        repository.save(salvo);

        assertEquals("Nome deve ser igual a Tipo alterado.", "Tipo alterado",
                salvo.nome());
    }

    private TipoInformacao entity() {
        TipoInformacaoId entityId = new TipoInformacaoId(id--);
        TipoInformacao entity =
                new TipoInformacao(entityId, "Tipo de informação " + entityId.toString());

        return entity;
    }

}
