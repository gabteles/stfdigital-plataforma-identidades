package br.jus.stf.plataforma.identidades.domain.model;

import java.util.List;

import br.jus.stf.core.shared.identidades.UsuarioId;

/**
 * @author Rafael Alencar
 * 
 * @since 1.0.0
 * @since 03.06.2016
 */
public interface UsuarioRepository {

    /**
     * @return
     */
    List<Usuario> findAll();

    /**
     * @param id
     * @return
     */
    Usuario findOne(UsuarioId id);

    /**
     * @param login
     * @return
     */
    Usuario findOne(String login);

    /**
     * @param principal
     * @return
     */
    <U extends Usuario> U save(U principal);

    /**
     * @param id
     * @return
     */
    List<Recurso> findRecursoByUsuario(UsuarioId id);

    /**
     * @return
     */
    UsuarioId nextId();

}
