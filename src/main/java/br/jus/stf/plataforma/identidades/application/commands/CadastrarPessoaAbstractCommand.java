package br.jus.stf.plataforma.identidades.application.commands;

import org.hibernate.validator.constraints.NotBlank;

import com.wordnik.swagger.annotations.ApiModel;
import com.wordnik.swagger.annotations.ApiModelProperty;

/**
 * @author Rafael Alencar
 * @since 19.10.2016
 */
@ApiModel("Command que com dados básicos de cadastro de pessoa.")
public abstract class CadastrarPessoaAbstractCommand {

    @NotBlank
    @ApiModelProperty(value = "Nome", required = true)
    private String nome;

    @ApiModelProperty(value = "E-mail")
    private String email;

    @ApiModelProperty(value = "CPF")
    private String cpf;

    @ApiModelProperty(value = "Número da OAB")
    private String oab;

    @ApiModelProperty(value = "Telefone")
    private String telefone;

    public CadastrarPessoaAbstractCommand() {
        // Construtor default.
    }

    public CadastrarPessoaAbstractCommand(String nome) {
        this.nome = nome;
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
