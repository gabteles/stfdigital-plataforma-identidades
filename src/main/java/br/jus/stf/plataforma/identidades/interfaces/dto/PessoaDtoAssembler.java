package br.jus.stf.plataforma.identidades.interfaces.dto;

import org.springframework.stereotype.Component;

import br.jus.stf.plataforma.identidades.domain.model.corporativo.Pessoa;

/**
 * @author anderson.araujo
 * 
 * @since 1.0.0
 * @since 23.07.2015
 */
@Component
public class PessoaDtoAssembler {

    public PessoaDto toDto(Pessoa pessoa) {
        if (pessoa == null) {
            return null;
        }

        return new PessoaDto(pessoa.identity().toLong(), pessoa.nome());
    }
}
