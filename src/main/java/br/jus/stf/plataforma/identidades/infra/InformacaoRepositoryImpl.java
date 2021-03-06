package br.jus.stf.plataforma.identidades.infra;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.persistence.TypedQuery;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.support.SimpleJpaRepository;
import org.springframework.stereotype.Repository;

import br.jus.stf.core.shared.identidades.InformacaoId;
import br.jus.stf.core.shared.identidades.SegmentoId;
import br.jus.stf.core.shared.identidades.TipoInformacaoId;
import br.jus.stf.plataforma.identidades.domain.model.Informacao;
import br.jus.stf.plataforma.identidades.domain.model.InformacaoRepository;

/**
 * @author Rafael Alencar
 * 
 * @since 1.0.0
 * @since 03.06.2016
 */
@Repository
public class InformacaoRepositoryImpl extends SimpleJpaRepository<Informacao, InformacaoId>
        implements InformacaoRepository {

    private EntityManager entityManager;

    /**
     * @param entityManager
     */
    @Autowired
    public InformacaoRepositoryImpl(EntityManager entityManager) {
        super(Informacao.class, entityManager);
        this.entityManager = entityManager;
    }

    @Override
    public Informacao findOne(TipoInformacaoId tipo, SegmentoId segmento, String identidade) {
        String sql = "FROM Informacao info WHERE info.tipo.id = :tipo AND info.identidade = :identidade";

        if (segmento != null) {
            sql += " AND info.segmento.id = :segmento";
        }

        TypedQuery<Informacao> query = entityManager.createQuery(sql, Informacao.class);

        query.setParameter("tipo", tipo);
        query.setParameter("identidade", identidade);

        if (segmento != null) {
            query.setParameter("segmento", segmento);
        }

        return query.getSingleResult();
    }

    @Override
    public InformacaoId nextId() {
        Query query = entityManager.createNativeQuery("SELECT identidades.seq_informacao.NEXTVAL FROM DUAL");
        Long sequencial = ((Number) query.getSingleResult()).longValue();

        return new InformacaoId(sequencial);
    }

}