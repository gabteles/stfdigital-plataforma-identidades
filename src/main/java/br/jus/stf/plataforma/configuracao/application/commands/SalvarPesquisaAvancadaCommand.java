package br.jus.stf.plataforma.configuracao.application.commands;

import org.hibernate.validator.constraints.NotBlank;

import com.wordnik.swagger.annotations.ApiModelProperty;

/**
 * @author Lucas Rodrigues
 *
 * @since 1.0.0
 * @since 22.09.2016
 */
public class SalvarPesquisaAvancadaCommand {
	
	@ApiModelProperty(value = "Identificador da pesquisa.")
	private Long pesquisaId;	
	
	@ApiModelProperty(value = "Descrição da pesquisa.", required = true)
	@NotBlank
	private String descricao;
	
	@ApiModelProperty(value = "Contexto da pesquisa.", required = true)
	@NotBlank
	private String contexto;
	
	@ApiModelProperty(value = "Critério de pesquisa.", required = true)
	@NotBlank
	private String criterio;
	
	@ApiModelProperty(value = "Pesquisa é executada automaticamente.")
	private Boolean execucaoAutomatica;
	
	public Long getPesquisaId() {
		return pesquisaId;
	}
	
	public String getDescricao() {
		return descricao;
	}
	
	public String getContexto() {
		return contexto;
	}
	
	public String getCriterio() {
		return criterio;
	}
	
	public Boolean isExecucaoAutomatica() {
		return Boolean.TRUE.equals(execucaoAutomatica);
	}

}
