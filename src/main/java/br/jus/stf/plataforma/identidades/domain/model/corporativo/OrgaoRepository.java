package br.jus.stf.plataforma.identidades.domain.model.corporativo;

import java.util.List;

import br.jus.stf.core.shared.identidade.PessoaId;

/**
 * @author Rafael Alencar
 * 
 * @since 1.0.0
 * @since 01.07.2016
 */
public interface OrgaoRepository {

    /**
     * @return
     */
    List<Orgao> findAll();

    /**
     * @param id
     * @return
     */
    Orgao findOne(PessoaId id);

    /**
     * @param orgao
     * @return
     */
    <O extends Orgao> O save(O orgao);

}
