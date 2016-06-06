package br.jus.stf.plataforma.userauthentication.domain.model;

import java.util.List;

import br.jus.stf.core.shared.userauthentication.UsuarioId;

/**
 * @author Rafael Alencar
 * 
 * @since 1.0.0
 * @since 03.06.2016
 */
public interface UsuarioRepository {
	
	public List<Usuario> findAll();
	
	public Usuario findOne(UsuarioId id);
	
	public Usuario findOne(String login);
	
	public <U extends Usuario> U save(U principal);
	
	public List<Recurso> findRecursoByUsuario(String login);

}
