package br.jus.stf.plataforma.userauthentication.domain.model;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

import org.apache.commons.lang3.Validate;

import br.jus.stf.core.framework.domaindrivendesign.EntitySupport;
import br.jus.stf.core.shared.userauthentication.SegmentoId;

/**
 * @author Rafael Alencar
 * 
 * @since 1.0.0
 * @since 03.06.2016
 */
@Entity
@Table(name = "SEGMENTO", schema = "PLATAFORMA", uniqueConstraints = @UniqueConstraint(columnNames = {"NOM_SEGMENTO", "SEQ_TIPO_INFORMACAO"}))
public class Segmento extends EntitySupport<Segmento, SegmentoId> {
	
	@EmbeddedId
	private SegmentoId id;
	
	@Column(name = "NOM_SEGMENTO", nullable = false)
	private String nome;
	
	@ManyToOne
	@JoinColumn(name = "SEQ_TIPO_INFORMACAO", referencedColumnName = "SEQ_TIPO_INFORMACAO", nullable = false)
	private TipoInformacao tipo;
	
	public Segmento() {
		// Deve ser usado apenas pelo Hibernate, que sempre usa o construtor default antes de popular uma nova instância.
	}
	
	public Segmento(final SegmentoId id, final String nome, final TipoInformacao tipo) {
		Validate.notNull(id, "Identificador requerido.");
		Validate.notBlank(nome, "Nome requerido");
		Validate.notNull(tipo, "Tipo de informação requerido.");
		
		this.id = id;
		this.nome = nome;
		this.tipo = tipo;
	}
	
	public String nome() {
		return nome;
	}
	
	public TipoInformacao tipo() {
		return tipo;
	}
	
	@Override
	public SegmentoId identity() {
		return id;
	}	

}
