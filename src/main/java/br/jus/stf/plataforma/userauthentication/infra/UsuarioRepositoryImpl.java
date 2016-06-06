package br.jus.stf.plataforma.userauthentication.infra;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.support.SimpleJpaRepository;
import org.springframework.stereotype.Repository;

import br.jus.stf.core.shared.userauthentication.UsuarioId;
import br.jus.stf.plataforma.userauthentication.domain.model.Recurso;
import br.jus.stf.plataforma.userauthentication.domain.model.Usuario;
import br.jus.stf.plataforma.userauthentication.domain.model.UsuarioRepository;

/**
 * @author Rafael Alencar
 * 
 * @since 1.0.0
 * @since 03.06.2016
 */
@Repository
public class UsuarioRepositoryImpl extends SimpleJpaRepository<Usuario, UsuarioId> implements UsuarioRepository {
	
	private EntityManager entityManager;
	
	@Autowired
	public UsuarioRepositoryImpl(EntityManager entityManager) {
		super(Usuario.class, entityManager);
		this.entityManager = entityManager;
	}
	
	@Override
	public Usuario findOne(String login) {
		return super.findOne(new Specification<Usuario>(){
			@Override
			public Predicate toPredicate(Root<Usuario> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
				return cb.equal(root.get("login"), login);
			}
		});
	}
	
	@Override
	public List<Recurso> findRecursoByUsuario(String login) {
		TypedQuery<Recurso> query = entityManager.createQuery("SELECT recu FROM Usuario usua INNER JOIN usua.recursos recu WITH usua.login = :login", Recurso.class);
		
		query.setParameter("login", login);
		
		return query.getResultList();
	}

}
