package br.jus.stf.plataforma.identidades.interfaces.dto;

import org.springframework.stereotype.Component;

import br.jus.stf.plataforma.identidades.domain.model.Recurso;

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
