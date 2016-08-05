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
	
	@ApiModelProperty(value = "Papéis adicionados ao usuário.")
	private Set<Long> papeisAdicionados;
	
	@ApiModelProperty(value = "Grupos adicionados ao usuário.")
	private Set<Long> gruposAdicionados;
	
	@ApiModelProperty(value = "Recursos adicionados ao usuário.")
	private Set<Long> recursosAdicionados;
	
	@ApiModelProperty(value = "Papéis removidos do usuário.")
	private Set<Long> papeisRemovidos;
	
	@ApiModelProperty(value = "Grupos removidos do usuário.")
	private Set<Long> gruposRemovidos;
	
	@ApiModelProperty(value = "Recursos removidos do usuário.")
	private Set<Long> recursosRemovidos;
	
	public ConfigurarPermissoesUsuarioCommand() {
		// Construtor default
	}
	
	/**
	 * @param idUsuario
	 * @param papeisAdicionados
	 * @param gruposAdicionados
	 * @param recursosAdicionados
	 * @param papeisRemovidos
	 * @param gruposRemovidos
	 * @param recursosRemovidos
	 */
	public ConfigurarPermissoesUsuarioCommand(Long idUsuario, Set<Long> papeisAdicionados, Set<Long> gruposAdicionados,
			Set<Long> recursosAdicionados, Set<Long> papeisRemovidos, Set<Long> gruposRemovidos,
			Set<Long> recursosRemovidos) {
		this.idUsuario = idUsuario;
		this.papeisAdicionados = papeisAdicionados;
		this.gruposAdicionados = gruposAdicionados;
		this.recursosAdicionados = recursosAdicionados;
		this.papeisRemovidos = papeisRemovidos;
		this.gruposRemovidos = gruposRemovidos;
		this.recursosRemovidos = recursosRemovidos;
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
	
	public Set<Long> getRecursosAdicionados() {
		return recursosAdicionados;
	}

	public void setRecursosAdicionados(Set<Long> recursosAdicionados) {
		this.recursosAdicionados = recursosAdicionados;
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
	
	public Set<Long> getRecursosRemovidos() {
		return recursosRemovidos;
	}

	public void setRecursosRemovidos(Set<Long> recursosRemovidos) {
		this.recursosRemovidos = recursosRemovidos;
	}
}
