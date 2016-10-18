package br.jus.stf.plataforma.identidades.interfaces.dto;

import org.springframework.stereotype.Component;

import br.jus.stf.plataforma.identidades.domain.model.Grupo;

/**
 * Transforma um Grupo num GrupoDto.
 * 
 * @author Anderson.Araujo
 * 
 * @since 02.12.2015
 *
 */
@Component
public class GrupoDtoAssembler {
	public GrupoDto toDto(Grupo grupo){
		return new GrupoDto(grupo.identity().toLong(), grupo.nome());
	}
}
