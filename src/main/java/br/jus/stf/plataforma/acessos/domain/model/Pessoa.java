package br.jus.stf.plataforma.acessos.domain.model;

import javax.persistence.Column;
import javax.persistence.Embeddable;

import org.apache.commons.lang3.Validate;

import br.jus.stf.core.framework.domaindrivendesign.ValueObjectSupport;
import br.jus.stf.core.shared.identidade.PessoaId;

/**
 * @author Rafael Alencar
 * 
 * @since 1.0.0
 * @since 03.06.2016
 */
@Embeddable
public class Pessoa extends ValueObjectSupport<Pessoa> {
	
	@Column(nullable = false)
	private PessoaId id;
	
	@Column(name = "NOM_PESSOA", nullable = false)
	private String nome;
	
	public Pessoa() {
		// Deve ser usado apenas pelo Hibernate, que sempre usa o construtor default antes de popular uma nova instância.
	}
	
	public Pessoa(PessoaId id, String nome) {
		Validate.notNull(id, "Identificador requerido.");
		Validate.notBlank(nome, "Nome requerido.");
		
		this.id = id;
		this.nome = nome;
	}

	public String nome() {
		return nome;
	}
	
	public PessoaId id() {
		return id;
	}

}
