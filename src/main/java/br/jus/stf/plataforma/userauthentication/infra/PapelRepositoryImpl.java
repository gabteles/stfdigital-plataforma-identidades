package br.jus.stf.plataforma.userauthentication.infra;

import java.math.BigInteger;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.persistence.TypedQuery;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.support.SimpleJpaRepository;
import org.springframework.stereotype.Repository;

import br.jus.stf.core.shared.userauthentication.PapelId;
import br.jus.stf.core.shared.userauthentication.RecursoId;
import br.jus.stf.plataforma.userauthentication.domain.model.Papel;
import br.jus.stf.plataforma.userauthentication.domain.model.PapelRepository;
import br.jus.stf.plataforma.userauthentication.domain.model.Recurso;

/**
 * @author Rafael Alencar
 * 
 * @since 1.0.0
 * @since 03.06.2016
 */
@Repository
public class PapelRepositoryImpl extends SimpleJpaRepository<Papel, PapelId> implements PapelRepository {
	
	private EntityManager entityManager;
	
	@Autowired
	public PapelRepositoryImpl(EntityManager entityManager) {
		super(Papel.class, entityManager);
		this.entityManager = entityManager;
	}
	
	@Override
	public Papel findOne(String nome) {
		TypedQuery<Papel> query = entityManager.createQuery("FROM Papel papel WHERE papel.nome = :nome", Papel.class);
		
		query.setParameter("nome", nome);
		
		return query.getSingleResult();
	}
			
	@Override
	public List<Recurso> findRecursoByPapel(PapelId id) {
		TypedQuery<Recurso> query = entityManager.createQuery("SELECT recu FROM Papel papel INNER JOIN papel.recursos recu WITH papel.id = :id", Recurso.class);
		
		query.setParameter("id", id);
		
		return query.getResultList();
	}
	
	@Override
	public PapelId nextId() {
		Query query = entityManager.createNativeQuery("SELECT uaa.seq_papel.NEXTVAL FROM DUAL");
		Long sequencial = ((BigInteger) query.getSingleResult()).longValue();
		
		return new PapelId(sequencial);
	}
	
	@Override
	public List<Papel> findPapelByRecurso(RecursoId id) {
		StringBuilder sql = new StringBuilder();
		
		sql.append("SELECT papel FROM Papel papel INNER JOIN papel.recursos recu WHERE recu.id = :id");
		
		TypedQuery<Papel> query = entityManager.createQuery(sql.toString(), Papel.class);
		
		query.setParameter("id", id);
		
		return query.getResultList();
	}

}