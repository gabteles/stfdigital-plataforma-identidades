package br.jus.stf.plataforma.userauthentication.infra;

import java.math.BigInteger;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.persistence.TypedQuery;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.support.SimpleJpaRepository;
import org.springframework.stereotype.Repository;

import br.jus.stf.core.shared.userauthentication.GrupoId;
import br.jus.stf.plataforma.userauthentication.domain.model.Grupo;
import br.jus.stf.plataforma.userauthentication.domain.model.GrupoRepository;
import br.jus.stf.plataforma.userauthentication.domain.model.Recurso;
import br.jus.stf.plataforma.userauthentication.domain.model.TipoGrupo;

/**
 * @author Rafael Alencar
 * 
 * @since 1.0.0
 * @since 03.06.2016
 */
@Repository
public class GrupoRepositoryImpl extends SimpleJpaRepository<Grupo, GrupoId> implements GrupoRepository {
	
	private EntityManager entityManager;
	
	@Autowired
	public GrupoRepositoryImpl(EntityManager entityManager) {
		super(Grupo.class, entityManager);
		this.entityManager = entityManager;
	}
	
	@Override
	public Grupo findOne(String nome, TipoGrupo tipo) {
		TypedQuery<Grupo> query = entityManager.createQuery("FROM Grupo grupo WHERE grupo.nome = :nome AND grupo.tipo = :tipo", Grupo.class);
		
		query.setParameter("nome", nome);
		query.setParameter("tipo", tipo);
		
		return query.getSingleResult();
	}
	
	@Override
	public List<Recurso> findRecursoByGrupo(GrupoId id) {
		TypedQuery<Recurso> query = entityManager.createQuery("SELECT recu FROM Grupo grupo INNER JOIN grupo.recursos recu WITH grupo.id = :id", Recurso.class);
		
		query.setParameter("id", id);
		
		return query.getResultList();
	}
	
	@Override
	public GrupoId nextId() {
		Query query = entityManager.createNativeQuery("SELECT uaa.seq_grupo.NEXTVAL FROM DUAL");
		Long sequencial = ((BigInteger) query.getSingleResult()).longValue();
		
		return new GrupoId(sequencial);
	}

}