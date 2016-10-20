package br.jus.stf.plataforma.identidades.domain.model;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

import org.apache.commons.lang3.Validate;

import br.jus.stf.core.framework.domaindrivendesign.EntitySupport;
import br.jus.stf.core.shared.identidades.TipoInformacaoId;

/**
 * @author Rafael Alencar
 * 
 * @since 1.0.0
 * @since 03.06.2016
 */
@Entity
@Table(name = "TIPO_INFORMACAO", schema = "IDENTIDADES",
        uniqueConstraints = @UniqueConstraint(columnNames = { "NOM_TIPO_INFORMACAO" }))
public class TipoInformacao extends EntitySupport<TipoInformacao, TipoInformacaoId> {

    @EmbeddedId
    private TipoInformacaoId id;

    @Column(name = "NOM_TIPO_INFORMACAO", nullable = false)
    private String nome;

    TipoInformacao() {
        // Deve ser usado apenas pelo Hibernate, que sempre usa o construtor default antes de popular uma nova
        // instância.
    }

    /**
     * @param id
     * @param nome
     */
    public TipoInformacao(TipoInformacaoId id, String nome) {
        Validate.notNull(id, "Identificador requerido.");
        Validate.notBlank(nome, "Nome requerido.");

        this.id = id;
        this.nome = nome;
    }

    /**
     * @return
     */
    public String nome() {
        return nome;
    }

    @Override
    public TipoInformacaoId identity() {
        return id;
    }

}
