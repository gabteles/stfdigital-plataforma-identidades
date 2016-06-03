package br.jus.stf.plataforma.acessos.infra;

import java.math.BigInteger;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.persistence.TypedQuery;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.support.SimpleJpaRepository;
import org.springframework.stereotype.Repository;

import br.jus.stf.core.shared.userauthentication.InformacaoId;
import br.jus.stf.plataforma.acessos.domain.model.Informacao;
import br.jus.stf.plataforma.acessos.domain.model.InformacaoRepository;
import br.jus.stf.plataforma.acessos.domain.model.Segmento;
import br.jus.stf.plataforma.acessos.domain.model.TipoInformacao;

/**
 * @author Rafael Alencar
 * 
 * @since 1.0.0
 * @since 03.06.2016
 */
@Repository
public class InformacaoRepositoryImpl extends SimpleJpaRepository<Informacao, InformacaoId> implements InformacaoRepository {
	
	private EntityManager entityManager;
	
	@Autowired
	public InformacaoRepositoryImpl(EntityManager entityManager) {
		super(Informacao.class, entityManager);
		this.entityManager = entityManager;
	}
	
	@Override
	public Informacao findOne(TipoInformacao tipo, Segmento segmento, String identidade) {
		String sql = "FROM Informacao info WHERE info.tipo = :tipo AND info.identidade = :identidade";
		
		if (segmento != null) {
			sql += " AND info.segmento = :segmento";
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
		Query query = entityManager.createNativeQuery("SELECT plataforma.seq_informacao.NEXTVAL FROM DUAL");
		Long sequencial = ((BigInteger) query.getSingleResult()).longValue();
		return new InformacaoId(sequencial);
	}
	
}