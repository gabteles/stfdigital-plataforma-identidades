package br.jus.stf.plataforma.identidades.application.commands;

import org.hibernate.validator.constraints.NotBlank;

import com.wordnik.swagger.annotations.ApiModel;
import com.wordnik.swagger.annotations.ApiModelProperty;

/**
 * @author Gabriel Teles
 * 
 * @since 1.0.0
 * @since 03.12.2015
 */
@ApiModel(value = "Contem as informações necessárias para cadastrar um usuário")
public class CadastrarUsuarioCommand {
	@ApiModelProperty(value = "Login de acesso", required = true)
	@NotBlank
	private String login;

	@ApiModelProperty(value = "Nome do usuário", required = true)
	@NotBlank
	private String nome;

	@ApiModelProperty(value = "Email")
	private String email;

	@ApiModelProperty(value = "CPF do usuário")
	private String cpf;

	@ApiModelProperty(value = "Número da OAB")
	private String oab;

	@ApiModelProperty(value = "Telefone")
	private String telefone;
	
	@ApiModelProperty(value = "Id da pessoa no contexto de identidades")
	private Long pessoaId;
	
	public CadastrarUsuarioCommand() {
		// Construtor default
	}
	
	/**
	 * @param login
	 * @param nome
	 * @param email
	 * @param cpf
	 * @param oab
	 * @param telefone
	 * @param pessoaId
	 */
	public CadastrarUsuarioCommand(String login, String nome, String email, String cpf, String oab, String telefone, Long pessoaId) {
		this.login = login;
		this.nome = nome;
		this.email = email;
		this.cpf = cpf;
		this.oab = oab;
		this.telefone = telefone;
		this.pessoaId = pessoaId;
	}

	/**
	 * @return the login
	 */
	public String getLogin() {
		return login;
	}

	/**
	 * @param login
	 *            the login to set
	 */
	public void setLogin(String login) {
		this.login = login;
	}

	/**
	 * @return the nome
	 */
	public String getNome() {
		return nome;
	}

	/**
	 * @param nome
	 *            the nome to set
	 */
	public void setNome(String nome) {
		this.nome = nome;
	}

	/**
	 * @return the email
	 */
	public String getEmail() {
		return email;
	}

	/**
	 * @param email
	 *            the email to set
	 */
	public void setEmail(String email) {
		this.email = email;
	}

	/**
	 * @return the cpf
	 */
	public String getCpf() {
		return cpf;
	}

	/**
	 * @param cpf
	 *            the cpf to set
	 */
	public void setCpf(String cpf) {
		this.cpf = cpf;
	}

	/**
	 * @return the oab
	 */
	public String getOab() {
		return oab;
	}

	/**
	 * @param oab
	 *            the oab to set
	 */
	public void setOab(String oab) {
		this.oab = oab;
	}

	/**
	 * @return the telefone
	 */
	public String getTelefone() {
		return telefone;
	}

	/**
	 * @param telefone
	 *            the telefone to set
	 */
	public void setTelefone(String telefone) {
		this.telefone = telefone;
	}
	
	/**
	 * @return the pessoaId
	 */
	public Long getPessoaId() {
		return pessoaId;
	}
	
	/**
	 * @param pessoaId
	 * 			the pessoaId to set
	 */
	public void setPessoaId(Long pessoaId) {
		this.pessoaId = pessoaId;
	}

}
