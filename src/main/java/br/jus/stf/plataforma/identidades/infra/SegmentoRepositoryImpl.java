package br.jus.stf.plataforma.identidades.infra;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.persistence.TypedQuery;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.support.SimpleJpaRepository;
import org.springframework.stereotype.Repository;

import br.jus.stf.core.shared.identidades.SegmentoId;
import br.jus.stf.core.shared.identidades.TipoInformacaoId;
import br.jus.stf.plataforma.identidades.domain.model.Segmento;
import br.jus.stf.plataforma.identidades.domain.model.SegmentoRepository;
import br.jus.stf.plataforma.identidades.domain.model.TipoInformacao;

/**
 * @author Rafael Alencar
 * 
 * @since 1.0.0
 * @since 03.06.2016
 */
@Repository
public class SegmentoRepositoryImpl extends SimpleJpaRepository<Segmento, SegmentoId> implements SegmentoRepository {

    private EntityManager entityManager;

    /**
     * @param entityManager
     */
    @Autowired
    public SegmentoRepositoryImpl(EntityManager entityManager) {
        super(Segmento.class, entityManager);
        this.entityManager = entityManager;
    }

    @Override
    public Segmento findOne(String nome, TipoInformacao tipo) {
        TypedQuery<Segmento> query = entityManager.createQuery(
                "FROM Segmento segmento WHERE segmento.nome = :nome AND segmento.tipo = :tipo", Segmento.class);

        query.setParameter("nome", nome);
        query.setParameter("tipo", tipo);

        return query.getSingleResult();
    }

    @Override
    public List<Segmento> findByTipoInformacao(TipoInformacaoId tipo) {
        TypedQuery<Segmento> query = entityManager.createQuery(
                "FROM Segmento segmento WHERE segmento.tipo = :tipo ORDER BY segmento.nome", Segmento.class);

        query.setParameter("tipo", tipo);

        return query.getResultList();
    }

    @Override
    public SegmentoId nextId() {
        Query query = entityManager.createNativeQuery("SELECT identidades.seq_segmento.NEXTVAL FROM DUAL");
        Long sequencial = ((Number) query.getSingleResult()).longValue();
        return new SegmentoId(sequencial);
    }

}