package br.jus.stf.plataforma.userauthentication.domain.model;

import javax.persistence.Column;
import javax.persistence.Embeddable;

import org.apache.commons.lang3.Validate;
import org.elasticsearch.common.lang3.StringUtils;

import br.jus.stf.core.framework.domaindrivendesign.ValueObjectSupport;
import br.jus.stf.core.shared.identidade.PessoaId;
import br.jus.stf.plataforma.userauthentication.domain.model.validation.CPFUtils;

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
	
	@Column(name = "COD_CPF_PESSOA")
	private String cpf;
	
	@Column(name = "COD_OAB_PESSOA")
	private String oab;
	
	@Column(name = "DSC_EMAIL_PESSOA")
	private String email;
	
	@Column(name = "DSC_TELEFONE_PESSOA")
	private String telefone;
	
	public Pessoa() {
		// Deve ser usado apenas pelo Hibernate, que sempre usa o construtor default antes de popular uma nova instância.
	}
	
	public Pessoa(PessoaId id, String nome) {
		Validate.notNull(id, "Identificador requerido.");
		Validate.notBlank(nome, "Nome requerido.");
		
		this.id = id;
		this.nome = nome;
	}
	
	public Pessoa(PessoaId id, String nome, String cpf, String oab, String email, String telefone) {
		Validate.notNull(id, "Identificador requerido.");
		Validate.notBlank(nome, "Nome requerido.");
		Validate.isTrue(StringUtils.isBlank(cpf) || CPFUtils.isValido(cpf), "CPF inválido.");

		this.id = id;
		this.nome = nome;
		this.cpf = cpf;
		this.oab = oab;
		this.email = email;
		this.telefone = telefone;
	}

	public String nome() {
		return nome;
	}
	
	public PessoaId id() {
		return id;
	}
	
	public String cpf() {
		return cpf;
	}
	
	public String oab() {
		return oab;
	}
	
	public String email() {
		return email;
	}
	
	public String telefone() {
		return telefone;
	}

}
