package br.jus.stf.plataforma.acessos.domain.model;

import br.jus.stf.core.shared.userauthentication.InformacaoId;

/**
 * @author Rafael Alencar
 * 
 * @since 1.0.0
 * @since 03.06.2016
 */
public interface InformacaoRepository {
	
	public Informacao findOne(InformacaoId id);
	
	public Informacao findOne(TipoInformacao tipo, Segmento segmento, String identidade);
	
	public InformacaoId nextId();
	
	public <I extends Informacao> I save(I informacao);
	
	public void delete(InformacaoId id);

}