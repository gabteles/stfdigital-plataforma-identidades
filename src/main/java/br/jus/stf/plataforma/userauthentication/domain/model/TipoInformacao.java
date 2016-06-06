package br.jus.stf.plataforma.userauthentication.domain.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
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
@Table(name = "TIPO_INFORMACAO", schema = "UAA", uniqueConstraints = @UniqueConstraint(columnNames = {"NOM_TIPO_INFORMACAO"}))
public class TipoInformacao extends ValueObjectSupport<TipoInformacao> {
	
	@Id
	@Column(name = "SEQ_TIPO_INFORMACAO")
	@SequenceGenerator(name = "TIPOINFORMACAOID", sequenceName = "UAA.SEQ_TIPO_INFORMACAO", allocationSize = 1)
	@GeneratedValue(generator = "TIPOINFORMACAOID", strategy=GenerationType.SEQUENCE)
	private Long id;
	
	@Column(name = "NOM_TIPO_INFORMACAO", nullable = false)
	private String nome;
	
	public TipoInformacao() {
		// Deve ser usado apenas pelo Hibernate, que sempre usa o construtor default antes de popular uma nova instância.
	}
	
	public TipoInformacao(Long id, String nome) {
		Validate.notNull(id, "Identificador requerido.");
		Validate.notBlank(nome, "Nome requerido.");
		
		this.id = id;
		this.nome = nome;
	}
	
	public Long toLong() {
		return id;
	}
	
	public String nome() {
		return nome;
	}

}
