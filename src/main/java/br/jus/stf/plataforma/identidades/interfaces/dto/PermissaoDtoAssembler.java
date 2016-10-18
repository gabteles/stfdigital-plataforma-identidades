package br.jus.stf.plataforma.identidades.interfaces.dto;

import java.util.Optional;

import org.springframework.stereotype.Component;

import br.jus.stf.plataforma.identidades.domain.model.Permissao;

/**
 * @author Lucas.Rodrigues
 *
 */
@Component
public class PermissaoDtoAssembler {

	public PermissaoDto toDto(Permissao permissao) {
		StringBuilder sb = new StringBuilder()
			.append(permissao.tipo().name())
			.append("_")
			.append(permissao.tipoInformacao().nome());
		Optional.ofNullable(permissao.segmento())
			.ifPresent(segmento -> sb.append("_")
									 .append(segmento.nome()));
		
		return new PermissaoDto(sb.toString());
	}
	
}
