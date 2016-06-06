package br.jus.stf.plataforma.userauthentication.domain.model;

import java.util.List;

import br.jus.stf.core.shared.userauthentication.SegmentoId;
import br.jus.stf.core.shared.userauthentication.TipoInformacaoId;

/**
 * @author Rafael Alencar
 * 
 * @since 1.0.0
 * @since 03.06.2016
 */
public interface SegmentoRepository {
	
	/** Segmento **/
	public Segmento findOne(SegmentoId id);
	
	public Segmento findOne(String nome, TipoInformacaoId tipo);
	
	public SegmentoId nextId();
	
	public List<Segmento> findAll();
	
	public List<Segmento> findByTipoInformacao(TipoInformacaoId tipo);
	
	public <S extends Segmento> S save(S segmento);
	
	/** Tipo de informação **/
	public TipoInformacao findOneTipoInformacao(TipoInformacaoId tipo);
	
	public List<TipoInformacao> findAllTipoInformacao();	
	
}