package br.jus.stf.plataforma.userauthentication.domain.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

import org.apache.commons.lang3.Validate;

import br.jus.stf.core.framework.domaindrivendesign.ValueObjectSupport;

/**
 * @author Rafael Alencar
 * 
 * @since 1.0.0
 * @since 03.06.2016
 */
@Entity
@Table(name = "PERMISSAO", schema = "UAA", uniqueConstraints = @UniqueConstraint(columnNames = {"SEQ_TIPO_INFORMACAO", "SEQ_SEGMENTO", "TIP_PERMISSAO"}))
public class Permissao extends ValueObjectSupport<Permissao> {
	
	@Id
	@Column(name = "SEQ_PERMISSAO")
	@SequenceGenerator(name = "PERMISSAOID", sequenceName = "UAA.SEQ_PERMISSAO", allocationSize = 1)
	@GeneratedValue(generator = "PERMISSAOID", strategy=GenerationType.SEQUENCE)
	private Long id;
	
	@Column(name = "TIP_PERMISSAO", nullable = false)
	@Enumerated(EnumType.STRING)
	private TipoPermissao tipo;
	
	@ManyToOne
	@JoinColumn(name = "SEQ_SEGMENTO", referencedColumnName = "SEQ_SEGMENTO")
	private Segmento segmento;
	
	@ManyToOne
	@JoinColumn(name = "SEQ_TIPO_INFORMACAO", referencedColumnName = "SEQ_TIPO_INFORMACAO", nullable = false)
	private TipoInformacao tipoInformacao;
	
	public Permissao() {
		// Deve ser usado apenas pelo Hibernate, que sempre usa o construtor default antes de popular uma nova instância.
	}

	private Permissao(Long id, TipoPermissao tipo) {
		Validate.notNull(id, "Identificador requerido.");
		Validate.notNull(tipo, "Tipo de permissão requerido.");
		
		this.id = id;
		this.tipo = tipo;
	}
	
	public Permissao(Long id, TipoPermissao tipoPermissao, TipoInformacao tipoInformacao) {
		this(id, tipoPermissao);
		
		Validate.notNull(tipoInformacao, "Tipo de informação requerido.");
		
		this.tipoInformacao = tipoInformacao;
	}
	
	public Permissao(Long id, TipoPermissao tipoPermissao, Segmento segmento) {
		this(id, tipoPermissao);
		
		Validate.notNull(segmento, "Segmento requerido.");
		
		this.segmento = segmento;
		this.tipoInformacao = segmento.tipo();
	}
	
	public Long toLong() {
		return id;
	}
	
	public TipoInformacao tipoInformacao() {
		return tipoInformacao;
	}
	
	public Segmento segmento() {
		return segmento;
	}
	
	public TipoPermissao tipo() {
		return tipo;
	}

}
