package br.jus.stf.plataforma.userauthentication.domain.model.identidade;

/**
 * @author Rafael Alencar
 * 
 * @since 1.0.0
 * @since 01.07.2016
 */
public enum TipoAssociado {
	
	ASSOCIADO("Associado"),
	GESTOR("Gestor"),
	REPRESENTANTE("Representante");
	
	private String descricao;
	
	private TipoAssociado(String descricao) {
		this.descricao = descricao;
	}
	
	/**
	 * @return
	 */
	public String descricao() {
		return descricao;
	}

}
