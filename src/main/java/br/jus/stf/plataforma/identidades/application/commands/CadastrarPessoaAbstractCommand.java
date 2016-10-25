package br.jus.stf.plataforma.identidades.application.commands;

import org.hibernate.validator.constraints.NotBlank;

import com.wordnik.swagger.annotations.ApiModel;
import com.wordnik.swagger.annotations.ApiModelProperty;

/**
 * @author Rafael Alencar
 * @since 19.10.2016
 */
@ApiModel("Command com dados básicos de cadastro de pessoa.")
public abstract class CadastrarPessoaAbstractCommand {

    @NotBlank
    @ApiModelProperty(value = "Nome.", required = true)
    private String nome;

    @ApiModelProperty(value = "E-mail.")
    private String email;

    @ApiModelProperty(value = "CPF.")
    private String cpf;

    @ApiModelProperty(value = "Número da OAB.")
    private String oab;

    @ApiModelProperty(value = "Telefone.")
    private String telefone;

    public CadastrarPessoaAbstractCommand() {
        // Construtor default.
    }

    public CadastrarPessoaAbstractCommand(String nome) {
        this.nome = nome;
    }

    /**
     * @return O nome.
     */
    public String getNome() {
        return nome;
    }

    /**
     * @return O e-mail.
     */
    public String getEmail() {
        return email;
    }

    /**
     * @return O CPF.
     */
    public String getCpf() {
        return cpf;
    }

    /**
     * @return A OAB.
     */
    public String getOab() {
        return oab;
    }

    /**
     * @return O telefone.
     */
    public String getTelefone() {
        return telefone;
    }

}
