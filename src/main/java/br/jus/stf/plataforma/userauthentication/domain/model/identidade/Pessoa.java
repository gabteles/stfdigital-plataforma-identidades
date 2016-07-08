package br.jus.stf.plataforma.userauthentication.domain.model.identidade;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Table;

import org.apache.commons.lang3.Validate;
import org.hibernate.validator.constraints.Email;
import org.springframework.util.StringUtils;

import br.jus.stf.core.framework.domaindrivendesign.EntitySupport;
import br.jus.stf.core.shared.identidade.PessoaId;
import br.jus.stf.plataforma.userauthentication.domain.model.validation.CPFUtils;

/**
 * @author Rafael Alencar
 * 
 * @since 1.0.0
 * @since 07.06.2016
 */
@javax.persistence.Entity
@Table(name = "PESSOA", schema = "CORPORATIVO")
public class Pessoa extends EntitySupport<Pessoa, PessoaId> {

	@EmbeddedId
	private PessoaId id;
	
	@Column(name = "NOM_PESSOA", nullable = false)
	private String nome;
	
	@Column(name = "COD_CPF")
	private String cpf;
	
	@Column(name = "COD_OAB")
	private String oab;
	
	@Email
	@Column(name = "DSC_EMAIL")
	private String email;
	
	@Column(name = "DSC_TELEFONE")
	private String telefone;

	public Pessoa() {
		// Deve ser usado apenas pelo Hibernate, que sempre usa o construtor default antes de popular uma nova instância.
	}
	
	/**
	 * @param id
	 * @param nome
	 */
	public Pessoa(PessoaId id, String nome){
		Validate.notNull(id, "Identificador requerido.");
		Validate.notBlank(nome, "Nome requerido.");
		
		this.id = id;
		this.nome = nome;
	}
	
	/**
	 * @param id
	 * @param nome
	 * @param cpf
	 */
	public Pessoa(PessoaId id, String nome, String cpf){
		this(id, nome);
		
		Validate.notBlank(cpf, "CPF requerido.");
		Validate.isTrue(CPFUtils.isValido(cpf), "CPF inválido.");
				
		this.cpf = cpf;
	}
	
	/**
	 * @param id
	 * @param nome
	 * @param cpf
	 * @param email
	 * @param telefone
	 */
	public Pessoa(PessoaId id, String nome, String cpf, String email, String telefone){
		this(id, nome, cpf);
		
		Validate.notBlank(email, "E-mail requerido.");
		Validate.notBlank(telefone, "Telefone requerido.");
		
		this.email = email;
		this.telefone = telefone;
	}
	
	/**
	 * @param id
	 * @param nome
	 * @param cpf
	 * @param oab
	 * @param email
	 * @param telefone
	 */
	public Pessoa(PessoaId id, String nome, String cpf, String oab, String email, String telefone){
		this(id, nome, cpf, email, telefone);
		
		Validate.notBlank(oab, "OAB requerida.");
		
		this.oab = oab;
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
	public String cpf() {
		return cpf;
	}
	
	/**
	 * @return
	 */
	public String oab() {
		return oab;
	}
	
	/**
	 * @return
	 */
	public boolean ehAdvogado() {
		return !StringUtils.isEmpty(oab);
	}
	
	@Override
	public String toString() {
		return String.format("%d - %s", id, nome);
	}
	
	@Override
	public PessoaId identity() {
		return id;
	}
	
}