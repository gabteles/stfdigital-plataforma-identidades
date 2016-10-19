package br.jus.stf.plataforma.identidades.interfaces.dto;

import org.springframework.stereotype.Component;

import br.jus.stf.plataforma.identidades.domain.model.Setor;

@Component
public class SetorDtoAssembler {

    public SetorDto toDto(Setor setor) {
        if (setor == null) {
            return null;
        }

        return new SetorDto(
                setor.codigo(),
                setor.nome(),
                setor.sigla());
    }
}
