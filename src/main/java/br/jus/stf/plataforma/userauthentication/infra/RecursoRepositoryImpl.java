package br.jus.stf.plataforma.userauthentication.infra;

import java.math.BigInteger;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.persistence.TypedQuery;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.support.SimpleJpaRepository;
import org.springframework.stereotype.Repository;

import br.jus.stf.core.shared.userauthentication.RecursoId;
import br.jus.stf.plataforma.userauthentication.domain.model.Recurso;
import br.jus.stf.plataforma.userauthentication.domain.model.RecursoRepository;
import br.jus.stf.plataforma.userauthentication.domain.model.ResourceType;

/**
 * @author Rafael Alencar
 * 
 * @since 1.0.0
 * @since 03.06.2016
 */
@Repository
public class RecursoRepositoryImpl extends SimpleJpaRepository<Recurso, RecursoId> implements RecursoRepository {
	
	private EntityManager entityManager;
	
	/**
	 * @param entityManager
	 */
	@Autowired
	public RecursoRepositoryImpl(EntityManager entityManager) {
		super(Recurso.class, entityManager);
		this.entityManager = entityManager;
	}
	
	@Override
	public Recurso findOne(String nome, ResourceType tipo) {
		TypedQuery<Recurso> query = entityManager.createQuery("FROM Recurso recurso WHERE lower(recurso.nome) = lower(:nome) AND recurso.tipo = :tipo", Recurso.class);
		
		query.setParameter("nome", nome);
		query.setParameter("tipo", tipo);

		return query.getSingleResult();
	}
	
	@Override
	public RecursoId nextId() {
		Query query = entityManager.createNativeQuery("SELECT uaa.seq_recurso.NEXTVAL FROM DUAL");
		Long sequencial = ((BigInteger) query.getSingleResult()).longValue();
		
		return new RecursoId(sequencial);
	}

}
