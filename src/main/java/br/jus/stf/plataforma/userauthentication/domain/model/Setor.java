package br.jus.stf.plataforma.userauthentication.domain.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
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
@Table(name = "SETOR", schema = "UAA", uniqueConstraints = {@UniqueConstraint(columnNames = {"NOM_SETOR"}),
		@UniqueConstraint(columnNames = {"SIG_SETOR"})})
public class Setor extends ValueObjectSupport<Setor> {
	
	@Id
	@Column(name = "COD_SETOR")
	private Long codigo;
	
	@Column(name = "NOM_SETOR", nullable = false)
	private String nome;
	
	@Column(name = "SIG_SETOR", nullable = false)
	private String sigla;
	
	public Setor() {
		// Deve ser usado apenas pelo Hibernate, que sempre usa o construtor default antes de popular uma nova instância.
	}
	
	public Setor(Long codigo, String nome, String sigla) {
		Validate.notNull(codigo, "Código requerido.");
		Validate.notBlank(nome, "Nome requerido.");
		Validate.notBlank(sigla, "Sigla requerida.");
		
		this.codigo = codigo;
		this.nome = nome;
		this.sigla = sigla;
	}
	
	public Long codigo() {
		return codigo;
	}

	public String nome() {
		return nome;
	}
	
	public String sigla() {
		return sigla;
	}

}
