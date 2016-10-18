package br.jus.stf.plataforma.identidades.domain.model;

import java.util.List;

import br.jus.stf.core.shared.identidades.RecursoId;

/**
 * @author Rafael Alencar
 * 
 * @since 1.0.0
 * @since 03.06.2016
 */
public interface RecursoRepository {

    /**
     * @param id
     * @return
     */
    Recurso findOne(RecursoId id);

    /**
     * @param nome
     * @param tipoRecurso
     * @return
     */
    Recurso findOne(String nome, ResourceType tipoRecurso);

    /**
     * @return
     */
    List<Recurso> findAll();

    /**
     * @param recurso
     * @return
     */
    <R extends Recurso> R save(R recurso);

    /**
     * @return
     */
    RecursoId nextId();

}