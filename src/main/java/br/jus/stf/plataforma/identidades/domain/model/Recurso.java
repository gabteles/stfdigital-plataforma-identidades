package br.jus.stf.plataforma.identidades.domain.model;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

import org.apache.commons.lang3.Validate;

import br.jus.stf.core.framework.domaindrivendesign.EntitySupport;
import br.jus.stf.core.shared.identidades.RecursoId;

/**
 * @author Rafael Alencar
 * 
 * @since 1.0.0
 * @since 03.06.2016
 */
@javax.persistence.Entity
@Table(name = "RECURSO", schema = "IDENTIDADES",
        uniqueConstraints = @UniqueConstraint(columnNames = { "NOM_RECURSO", "TIP_RECURSO" }))
public class Recurso extends EntitySupport<Recurso, RecursoId> {

    @EmbeddedId
    private RecursoId id;

    @Column(name = "NOM_RECURSO", nullable = false)
    private String nome;

    @Column(name = "TIP_RECURSO", nullable = false)
    @Enumerated(EnumType.STRING)
    private ResourceType tipo;

    Recurso() {
        // Deve ser usado apenas pelo Hibernate, que sempre usa o construtor default antes de popular uma nova
        // instância.
    }

    /**
     * @param id
     * @param nome
     * @param tipo
     */
    public Recurso(RecursoId id, String nome, ResourceType tipo) {
        Validate.notNull(id, "Identificador requerido.");
        Validate.notBlank(nome, "Nome requerido.");
        Validate.notNull(tipo, "Tipo de recurso requerido.");

        this.id = id;
        this.nome = nome;
        this.tipo = tipo;
    }

    /**
     * @return
     */
    public String nome() {
        return nome;
    }

    /**
     * @return
     */
    public ResourceType tipo() {
        return tipo;
    }

    @Override
    public RecursoId identity() {
        return id;
    }

}
