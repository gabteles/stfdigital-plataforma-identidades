package br.jus.stf.plataforma.userauthentication.infra;

import java.math.BigInteger;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.persistence.TypedQuery;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.support.SimpleJpaRepository;
import org.springframework.stereotype.Repository;

import br.jus.stf.core.shared.userauthentication.SegmentoId;
import br.jus.stf.core.shared.userauthentication.TipoInformacaoId;
import br.jus.stf.plataforma.userauthentication.domain.model.Segmento;
import br.jus.stf.plataforma.userauthentication.domain.model.SegmentoRepository;
import br.jus.stf.plataforma.userauthentication.domain.model.TipoInformacao;

/**
 * @author Rafael Alencar
 * 
 * @since 1.0.0
 * @since 03.06.2016
 */
@Repository
public class SegmentoRepositoryImpl extends SimpleJpaRepository<Segmento, SegmentoId> implements SegmentoRepository {
	
	private EntityManager entityManager;
	
	@Autowired
	public SegmentoRepositoryImpl(EntityManager entityManager) {
		super(Segmento.class, entityManager);
		this.entityManager = entityManager;
	}
	
	@Override
	public Segmento findOne(String nome, TipoInformacaoId tipo) {
		TypedQuery<Segmento> query = entityManager.createQuery("FROM Segmento segmento WHERE segmento.nome = :nome AND segmento.tipo = :tipo", Segmento.class);

		query.setParameter("nome", nome);
		query.setParameter("tipo", tipo);
		
		return query.getSingleResult();
	}
	
	@Override
	public List<Segmento> findByTipoInformacao(TipoInformacaoId tipo) {
		TypedQuery<Segmento> query = entityManager.createQuery("FROM Segmento segmento WHERE segmento.tipo = :tipo ORDER BY segmento.nome", Segmento.class);
		
		query.setParameter("tipo", tipo);
		
		return query.getResultList();
	}
	
	@Override
	public SegmentoId nextId() {
		Query query = entityManager.createNativeQuery("SELECT uaa.seq_segmento.NEXTVAL FROM DUAL");
		Long sequencial = ((BigInteger) query.getSingleResult()).longValue();
		return new SegmentoId(sequencial);
	}
	
	/** Tipo de informação **/
	@Override
	public TipoInformacao findOneTipoInformacao(TipoInformacaoId tipo) {
		TypedQuery<TipoInformacao> query = entityManager.createQuery("FROM TipoInformacao tipo WHERE tipo.id = :id", TipoInformacao.class);
		query.setParameter("id", tipo);
		
		return query.getSingleResult();
	}
	
	@Override
	public List<TipoInformacao> findAllTipoInformacao() {
		TypedQuery<TipoInformacao> query = entityManager.createQuery("FROM TipoInformacao tipo ORDER BY tipo.nome", TipoInformacao.class);
		
		return query.getResultList();
	}

}