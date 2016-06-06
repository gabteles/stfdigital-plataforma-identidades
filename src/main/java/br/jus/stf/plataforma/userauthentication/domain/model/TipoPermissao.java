package br.jus.stf.plataforma.userauthentication.domain.model;

/**
 * @author Rafael Alencar
 * 
 * @since 1.0.0
 * @since 03.06.2016
 */
public enum TipoPermissao {
	
	ALTERAR("Alterar"),
	CRIAR("Criar"),
	EXCLUIR("Excluir"),
	EXECUTAR("Executar"),
	IMPRIMIR("Imprimir"),
	PESQUISAR("Pesquisar"),
	VISUALIZAR("Visualizar");
	
	private String descricao;
	
	private TipoPermissao(String descricao) {
		this.descricao = descricao;
	}
	
	public String descricao() {
		return descricao;
	}

}
