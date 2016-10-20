package br.jus.stf.plataforma.identidades.infra;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.support.SimpleJpaRepository;
import org.springframework.stereotype.Repository;

import br.jus.stf.core.shared.identidades.TipoInformacaoId;
import br.jus.stf.plataforma.identidades.domain.model.TipoInformacao;
import br.jus.stf.plataforma.identidades.domain.model.TipoInformacaoRepository;

/**
 * @author Rafael Alencar
 * 
 * @since 1.0.0
 * @since 20.10.2016
 */
@Repository
public class TipoInformacaoRepositoryImpl extends SimpleJpaRepository<TipoInformacao, TipoInformacaoId>
        implements TipoInformacaoRepository {

    private EntityManager entityManager;

    /**
     * @param entityManager
     */
    @Autowired
    public TipoInformacaoRepositoryImpl(EntityManager entityManager) {
        super(TipoInformacao.class, entityManager);
        this.entityManager = entityManager;
    }

    @Override
    public TipoInformacaoId nextId() {
        Query query = entityManager.createNativeQuery("SELECT identidades.seq_tipo_informacao.NEXTVAL FROM DUAL");
        Long sequencial = ((Number) query.getSingleResult()).longValue();
        return new TipoInformacaoId(sequencial);
    }

}