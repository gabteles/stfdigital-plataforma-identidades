package br.jus.stf.plataforma.identidades.domain.model;

import java.util.List;

import br.jus.stf.core.shared.identidades.PapelId;
import br.jus.stf.core.shared.identidades.RecursoId;

/**
 * @author Rafael Alencar
 * 
 * @since 1.0.0
 * @since 03.06.2016
 */
public interface PapelRepository {

    /**
     * @param id
     * @return
     */
    Papel findOne(PapelId id);

    /**
     * @param nome
     * @return
     */
    Papel findOne(String nome);

    /**
     * @return
     */
    List<Papel> findAll();

    /**
     * @param id
     * @return
     */
    List<Recurso> findRecursoByPapel(PapelId id);

    /**
     * @param papel
     * @return
     */
    <P extends Papel> P save(P papel);

    /**
     * @return
     */
    PapelId nextId();

    /**
     * @param id
     * @return
     */
    List<Papel> findPapelByRecurso(RecursoId id);

}