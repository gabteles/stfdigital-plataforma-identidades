package br.jus.stf.plataforma.identidades.domain.model;

import br.jus.stf.core.shared.identidades.InformacaoId;
import br.jus.stf.core.shared.identidades.SegmentoId;
import br.jus.stf.core.shared.identidades.TipoInformacaoId;

/**
 * @author Rafael Alencar
 * 
 * @since 1.0.0
 * @since 03.06.2016
 */
public interface InformacaoRepository {

    /**
     * @param id
     * @return
     */
    Informacao findOne(InformacaoId id);

    /**
     * @param tipo
     * @param segmento
     * @param identidade
     * @return
     */
    Informacao findOne(TipoInformacaoId tipo, SegmentoId segmento, String identidade);

    /**
     * @return
     */
    InformacaoId nextId();

    /**
     * @param informacao
     * @return
     */
    <I extends Informacao> I save(I informacao);

    /**
     * @param id
     */
    void delete(InformacaoId id);

}