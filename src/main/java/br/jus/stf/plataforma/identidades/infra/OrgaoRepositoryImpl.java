package br.jus.stf.plataforma.identidades.infra;

import javax.persistence.EntityManager;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.support.SimpleJpaRepository;
import org.springframework.stereotype.Repository;

import br.jus.stf.core.shared.identidade.PessoaId;
import br.jus.stf.plataforma.identidades.domain.model.corporativo.Orgao;
import br.jus.stf.plataforma.identidades.domain.model.corporativo.OrgaoRepository;

/**
 * @author Rafael Alencar
 * 
 * @since 1.0.0
 * @since 01.07.2016
 */
@Repository
public class OrgaoRepositoryImpl extends SimpleJpaRepository<Orgao, PessoaId> implements OrgaoRepository {

    /**
     * @param entityManager
     */
    @Autowired
    public OrgaoRepositoryImpl(EntityManager entityManager) {
        super(Orgao.class, entityManager);
    }

}
