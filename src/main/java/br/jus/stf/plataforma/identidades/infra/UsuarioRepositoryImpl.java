package br.jus.stf.plataforma.identidades.infra;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.support.SimpleJpaRepository;
import org.springframework.stereotype.Repository;

import br.jus.stf.core.shared.identidades.UsuarioId;
import br.jus.stf.plataforma.identidades.domain.model.Recurso;
import br.jus.stf.plataforma.identidades.domain.model.Usuario;
import br.jus.stf.plataforma.identidades.domain.model.UsuarioRepository;

/**
 * @author Rafael Alencar
 * 
 * @since 1.0.0
 * @since 03.06.2016
 */
@Repository
public class UsuarioRepositoryImpl extends SimpleJpaRepository<Usuario, UsuarioId> implements UsuarioRepository {

    private EntityManager entityManager;

    /**
     * @param entityManager
     */
    @Autowired
    public UsuarioRepositoryImpl(EntityManager entityManager) {
        super(Usuario.class, entityManager);
        this.entityManager = entityManager;
    }

    @Override
    public Usuario findOne(String login) {
        return super.findOne(
                (Root<Usuario> root, CriteriaQuery<?> query, CriteriaBuilder cb) -> cb.equal(root.get("login"), login));
    }

    @Override
    public List<Recurso> findRecursoByUsuario(UsuarioId id) {
        TypedQuery<Recurso> query = entityManager.createQuery(
                "SELECT recu FROM Usuario usua INNER JOIN usua.recursos recu WITH usua.id = :id", Recurso.class);

        query.setParameter("id", id);

        return query.getResultList();
    }

    @Override
    public UsuarioId nextId() {
        Query query = entityManager.createNativeQuery("SELECT identidades.seq_usuario.NEXTVAL FROM DUAL");
        Long sequencial = ((Number) query.getSingleResult()).longValue();

        return new UsuarioId(sequencial);
    }

}
