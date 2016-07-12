package br.jus.stf.plataforma.userauthentication.application.commands;

import java.util.Set;

import javax.validation.constraints.NotNull;

import com.wordnik.swagger.annotations.ApiModel;
import com.wordnik.swagger.annotations.ApiModelProperty;

/**
 * Classe usada para transportar os dados de configuração de permissões de usuário do front-end para o o back-end.
 * 
 * @author Anderson.Araujo
 * 
 * @since 03.12.2015
 *
 */
@ApiModel("Classe usada para transportar os dados de configuração de permissões de usuário do front-end para o o back-end.")
public class ConfigurarPermissoesUsuarioCommand {
	
	@ApiModelProperty(value = "Id do usuário.", required = true)
	@NotNull
	private Long idUsuario;
	
	@ApiModelProperty(value = "Papéis do usuário.")
	private Set<Long> papeisAdicionados;
	
	@ApiModelProperty(value = "Papéis do usuário.")
	private Set<Long> gruposAdicionados;
	
	@ApiModelProperty(value = "Papéis do usuário.")
	private Set<Long> papeisRemovidos;
	
	@ApiModelProperty(value = "Papéis do usuário.")
	private Set<Long> gruposRemovidos;
	
	public ConfigurarPermissoesUsuarioCommand() {
		// Construtor default
	}
	
	/**
	 * @param idUsuario
	 * @param papeisAdicionados
	 * @param gruposAdicionados
	 * @param papeisRemovidos
	 * @param gruposRemovidos
	 */
	public ConfigurarPermissoesUsuarioCommand(Long idUsuario, Set<Long> papeisAdicionados, Set<Long> gruposAdicionados,
			Set<Long> papeisRemovidos, Set<Long> gruposRemovidos) {
		this.idUsuario = idUsuario;
		this.papeisAdicionados = papeisAdicionados;
		this.gruposAdicionados = gruposAdicionados;
		this.papeisRemovidos = papeisRemovidos;
		this.gruposRemovidos = gruposRemovidos;
	}

	public Long getIdUsuario() {
		return idUsuario;
	}

	public void setIdUsuario(Long idUsuario) {
		this.idUsuario = idUsuario;
	}

	public Set<Long> getPapeisAdicionados() {
		return papeisAdicionados;
	}

	public void setPapeisAdicionados(Set<Long> papeisAdicionados) {
		this.papeisAdicionados = papeisAdicionados;
	}

	public Set<Long> getGruposAdicionados() {
		return gruposAdicionados;
	}

	public void setGruposAdicionados(Set<Long> gruposAdicionados) {
		this.gruposAdicionados = gruposAdicionados;
	}
	
	public Set<Long> getPapeisRemovidos() {
		return papeisRemovidos;
	}

	public void setPapeisRemovidos(Set<Long> papeisRemovidos) {
		this.papeisRemovidos = papeisRemovidos;
	}

	public Set<Long> getGruposRemovidos() {
		return gruposRemovidos;
	}

	public void setGruposRemovidos(Set<Long> gruposRemovidos) {
		this.gruposRemovidos = gruposRemovidos;
	}
}
