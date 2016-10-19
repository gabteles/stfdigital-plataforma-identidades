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
public class CadastrarUsuarioCommand extends CadastrarPessoaAbstractCommand {

    @NotBlank
    @ApiModelProperty(value = "Login de acesso", required = true)
    private String login;

    @ApiModelProperty(value = "Id da pessoa no contexto de identidades")
    private Long pessoaId;

    public CadastrarUsuarioCommand() {
        // Construtor default.
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
    public CadastrarUsuarioCommand(String login, String nome) {
        super(nome);

        this.login = login;
    }

    /**
     * @return the login
     */
    public String getLogin() {
        return login;
    }

    /**
     * @param login
     *        the login to set
     */
    public void setLogin(String login) {
        this.login = login;
    }

    /**
     * @return the pessoaId
     */
    public Long getPessoaId() {
        return pessoaId;
    }

    /**
     * @param pessoaId
     *        the pessoaId to set
     */
    public void setPessoaId(Long pessoaId) {
        this.pessoaId = pessoaId;
    }

}
