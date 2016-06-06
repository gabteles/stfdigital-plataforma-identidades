package br.jus.stf.plataforma.userauthentication.interfaces.dto;

import org.springframework.stereotype.Component;

import br.jus.stf.plataforma.userauthentication.domain.model.Recurso;

/**
 * @author Lucas.Rodrigues
 *
 */
@Component
public class RecursoDtoAssembler {

	public RecursoDto toDto(Recurso recurso) {
		return new RecursoDto(recurso.identity().toLong(), recurso.nome(), recurso.tipo().name());
	}
	
}
