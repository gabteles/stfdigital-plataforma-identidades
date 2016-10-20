package br.jus.stf.plataforma.identidades.domain.model;

import java.util.List;

import br.jus.stf.core.shared.identidades.TipoInformacaoId;

/**
 * @author Rafael Alencar
 * 
 * @since 1.0.0
 * @since 20.10.2016
 */
public interface TipoInformacaoRepository {

    /**
     * @return
     */
    TipoInformacaoId nextId();

    /**
     * @param tipo
     * @return
     */
    <T extends TipoInformacao> T save(T tipo);

    /**
     * @param tipo
     * @return
     */
    TipoInformacao findOne(TipoInformacaoId tipo);

    /**
     * @return
     */
    List<TipoInformacao> findAll();

}