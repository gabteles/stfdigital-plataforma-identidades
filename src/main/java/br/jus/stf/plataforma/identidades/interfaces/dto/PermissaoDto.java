package br.jus.stf.plataforma.identidades.interfaces.dto;

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
