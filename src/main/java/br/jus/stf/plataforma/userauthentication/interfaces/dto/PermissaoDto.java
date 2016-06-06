package br.jus.stf.plataforma.userauthentication.interfaces.dto;

/**
 * @author Lucas.Rodrigues
 *
 */
public class PermissaoDto {

	private String descricao;
	
	public PermissaoDto(String descricao) {
		this.descricao = descricao;
	}
	
	public String getDescricao() {
		return descricao;
	}
	
}
