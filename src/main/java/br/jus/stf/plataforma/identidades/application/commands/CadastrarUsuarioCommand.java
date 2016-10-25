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
@ApiModel(value = "Contém as informações necessárias para cadastrar um usuário.")
public class CadastrarUsuarioCommand extends CadastrarPessoaAbstractCommand {

    @NotBlank
    @ApiModelProperty(value = "Login de acesso.", required = true)
    private String login;

    @ApiModelProperty(value = "Id da pessoa no contexto de identidades.")
    private Long pessoaId;

    public CadastrarUsuarioCommand() {
        // Construtor default.
    }

    /**
     * @return O login
     */
    public String getLogin() {
        return login;
    }

    /**
     * @return O pessoaId.
     */
    public Long getPessoaId() {
        return pessoaId;
    }

}
