package br.jus.stf.plataforma.userauthentication.domain.model;

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
	
	public String descricao() {
		return descricao;
	}

}
