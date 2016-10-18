package br.jus.stf.plataforma.identidades.domain.model;

import java.util.List;

import br.jus.stf.core.shared.identidades.GrupoId;

/**
 * @author Rafael Alencar
 * 
 * @since 1.0.0
 * @since 03.06.2016
 */
public interface GrupoRepository {

    /**
     * @param id
     * @return
     */
    Grupo findOne(GrupoId id);

    /**
     * @param nome
     * @param tipo
     * @return
     */
    Grupo findOne(String nome, TipoGrupo tipo);

    /**
     * @return
     */
    List<Grupo> findAll();

    /**
     * @param id
     * @return
     */
    List<Recurso> findRecursoByGrupo(GrupoId id);

    /**
     * @return
     */
    GrupoId nextId();

    /**
     * @param grupo
     * @return
     */
    <G extends Grupo> G save(G grupo);

}