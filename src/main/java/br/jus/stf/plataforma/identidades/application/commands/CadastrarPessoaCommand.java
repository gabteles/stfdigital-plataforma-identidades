package br.jus.stf.plataforma.identidades.application.commands;

import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.NotBlank;

import com.wordnik.swagger.annotations.ApiModelProperty;

/**
 * Comando para cadastrar uma pessoa
 * 
 * @author Rafael Alencar
 * @since 1.0.0
 * @since 10.06.2016
 */
/**
 * @author rafael.alencar
 *
 */
public class CadastrarPessoaCommand {

    @ApiModelProperty(value = "Identificador", required = true)
    @NotNull
    private Long id;

    @ApiModelProperty(value = "Nome", required = true)
    @NotBlank
    private String nome;

    @ApiModelProperty(value = "E-mail")
    private String email;

    @ApiModelProperty(value = "CPF")
    private String cpf;

    @ApiModelProperty(value = "Número da OAB")
    private String oab;

    @ApiModelProperty(value = "Telefone")
    private String telefone;

    public CadastrarPessoaCommand() {
        // Construtor default
    }

    /**
     * @param id
     * @param nome
     * @param cpf
     * @param oab
     * @param email
     * @param telefone
     */
    public CadastrarPessoaCommand(Long id, String nome, String cpf, String oab, String email, String telefone) {
        this.id = id;
        this.nome = nome;
        this.cpf = cpf;
        this.oab = oab;
        this.email = email;
        this.telefone = telefone;
    }

    /**
     * @return the id
     */
    public Long getId() {
        return id;
    }

    /**
     * @param id
     *        the id to set
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * @return the nome
     */
    public String getNome() {
        return nome;
    }

    /**
     * @param nome
     *        the nome to set
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
     *        the email to set
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
     *        the cpf to set
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
     *        the oab to set
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
     *        the telefone to set
     */
    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

}
