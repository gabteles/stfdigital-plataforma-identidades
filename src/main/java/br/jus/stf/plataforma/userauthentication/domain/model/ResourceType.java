package br.jus.stf.plataforma.userauthentication.domain.model;

/**
 * @author Rafael Alencar
 * 
 * @since 1.0.0
 * @since 03.06.2016
 */
public enum ResourceType {
	
	ACAO("Ação"),
	DASHLET("Dashlet"),
	DASHBOARD("Dashboard"),
	NOTIFICACAO("Notificação"),
	PESQUISA("Pesquisa"),
	TAREFA("Tarefa");
	
	private String descricao;
	
	private ResourceType(String descricao) {
		this.descricao = descricao;
	}
	
	/**
	 * @return
	 */
	public String descricao() {
		return descricao;
	}

}
