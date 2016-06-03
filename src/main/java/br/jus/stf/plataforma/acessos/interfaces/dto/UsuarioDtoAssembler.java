package br.jus.stf.plataforma.acessos.interfaces.dto;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import br.jus.stf.plataforma.acessos.domain.model.Usuario;

/**
 * Classe responsável por criar um objeto UsuarioDto.
 * 
 * @author Anderson.Araujo
 * 
 * @since 30.11.2015
 *
 */
@Component
public class UsuarioDtoAssembler {
	
	@Autowired
	private SetorDtoAssembler setorDtoAssembler;
	
	public UsuarioDto toDto(Usuario usuario) {
		if (usuario == null) {
			return null;
		}
		
		SetorDto setor = setorDtoAssembler.toDto(usuario.setor());
		
		return new UsuarioDto(usuario.identity().toLong(), usuario.login(),
				usuario.pessoa().nome(), usuario.pessoa().id().toLong(), setor);
	}
}
