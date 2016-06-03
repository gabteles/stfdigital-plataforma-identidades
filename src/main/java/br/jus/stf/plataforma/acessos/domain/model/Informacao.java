package br.jus.stf.plataforma.acessos.domain.model;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

import org.apache.commons.lang3.Validate;

import br.jus.stf.core.framework.domaindrivendesign.EntitySupport;
import br.jus.stf.core.shared.userauthentication.InformacaoId;

/**
 * @author Rafael Alencar
 * 
 * @since 1.0.0
 * @since 03.06.2016
 */
@Entity
@Table(name = "INFORMACAO", schema = "PLATAFORMA", uniqueConstraints = @UniqueConstraint(columnNames = {"SEQ_TIPO_INFORMACAO", "SEQ_SEGMENTO", "COD_IDENTIDADE"}))
public class Informacao extends EntitySupport<Informacao, InformacaoId> {
	
	@EmbeddedId
	private InformacaoId id;
	
	@ManyToOne
	@JoinColumn(name = "SEQ_TIPO_INFORMACAO", nullable = false)
	private TipoInformacao tipo;
	
	@ManyToOne
	@JoinColumn(name = "SEQ_SEGMENTO")
	private Segmento segmento;
	
	@Column(name = "COD_IDENTIDADE", nullable = false)
	private String identidade;
	
	public Informacao() {
		// Deve ser usado apenas pelo Hibernate, que sempre usa o construtor default antes de popular uma nova instância.
	}
	
	private Informacao(InformacaoId id, String identidade) {
		Validate.notNull(id, "Identificador requerido.");
		Validate.notBlank(identidade, "Identidade requerida.");
		
		this.id = id;
		this.identidade = identidade;
	}
	
	public Informacao(InformacaoId id, TipoInformacao tipo, String identidade) {
		this(id, identidade);
		Validate.notNull(tipo, "Tipo de informação requerido.");
		
		this.tipo = tipo;
	}
	
	public Informacao(InformacaoId id, Segmento segmento, String identidade) {
		this(id, identidade);
		
		Validate.notNull(segmento, "Segmento requerido.");
		
		this.segmento = segmento;
		this.tipo = segmento.tipo();
	}
	
	public TipoInformacao tipo() {
		return tipo;
	}
	
	public Segmento segmento() {
		return segmento;
	}
	
	public String identidade() {
		return identidade;
	}
	
	@Override
	public InformacaoId identity() {
		return id;
	}

}
