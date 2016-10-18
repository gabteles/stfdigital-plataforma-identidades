package br.jus.stf.plataforma.configuracao.infra;

import java.util.stream.Stream;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.support.SimpleJpaRepository;
import org.springframework.stereotype.Repository;

import br.jus.stf.core.shared.identidades.UsuarioId;
import br.jus.stf.plataforma.configuracao.domain.model.Pesquisa;
import br.jus.stf.plataforma.configuracao.domain.model.PesquisaId;
import br.jus.stf.plataforma.configuracao.domain.model.PesquisaRepository;

/**
 * @author Lucas Rodrigues
 * 
 * @since 1.0.0
 * @since 22.09.2016
 */
@Repository
public class PesquisaRepositoryImpl extends SimpleJpaRepository<Pesquisa, PesquisaId> implements PesquisaRepository {

    private EntityManager entityManager;

    public PesquisaRepositoryImpl(EntityManager entityManager) {
        super(Pesquisa.class, entityManager);
        this.entityManager = entityManager;
    }

    @Override
    public Stream<Pesquisa> findByUsuarioAutenticado() {
        return findByUsuario(Pesquisa.recuperarUsuarioId());
    }

    public Stream<Pesquisa> findByUsuario(UsuarioId usuario) {
        return findAll(new Specification<Pesquisa>() {

            @Override
            public Predicate toPredicate(Root<Pesquisa> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
                return cb.equal(root.get("usuario"), usuario);
            }
        }).stream();
    }

    public PesquisaId nextId() {
        Query query =
                entityManager.createNativeQuery("SELECT configuracao.seq_pesquisa.NEXTVAL as seq_pesquisa FROM DUAL");
        Long sequencial = ((Number) query.getSingleResult()).longValue();
        return new PesquisaId(sequencial);
    }

}