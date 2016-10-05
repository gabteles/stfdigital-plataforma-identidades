package br.jus.stf.plataforma.configuracao.application.commands;

import javax.validation.constraints.NotNull;

/**
 * @author lucas.rodrigues
 *
 */
public class ExcluirPesquisaAvancadaCommand {

	@NotNull
	private Long id;
	
	public Long getId() {
		return id;
	}
	
	public void setId(Long id) {
		this.id = id;
	}
	
}
