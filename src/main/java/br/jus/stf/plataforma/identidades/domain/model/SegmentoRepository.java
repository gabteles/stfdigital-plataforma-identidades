package br.jus.stf.plataforma.identidades.domain.model;

import java.util.List;

import br.jus.stf.core.shared.identidades.SegmentoId;
import br.jus.stf.core.shared.identidades.TipoInformacaoId;

/**
 * @author Rafael Alencar
 * 
 * @since 1.0.0
 * @since 03.06.2016
 */
public interface SegmentoRepository {

    /**
     * @param id
     * @return
     */
    Segmento findOne(SegmentoId id);

    /**
     * @param nome
     * @param tipo
     * @return
     */
    Segmento findOne(String nome, TipoInformacaoId tipo);

    /**
     * @return
     */
    SegmentoId nextId();

    /**
     * @return
     */
    List<Segmento> findAll();

    /**
     * @param tipo
     * @return
     */
    List<Segmento> findByTipoInformacao(TipoInformacaoId tipo);

    /**
     * @param segmento
     * @return
     */
    <S extends Segmento> S save(S segmento);

}