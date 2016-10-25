package br.jus.stf.plataforma.identidades.application.commands;

import javax.validation.constraints.NotNull;

import com.wordnik.swagger.annotations.ApiModel;
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
@ApiModel("Classe usada para transportar os dados de pessoa do front-end para o back-end.")
public class CadastrarPessoaCommand extends CadastrarPessoaAbstractCommand {

    @NotNull
    @ApiModelProperty(value = "Identificador.", required = true)
    private Long id;

    public CadastrarPessoaCommand() {
        // Construtor default.
    }

    /**
     * @param id Identificador.
     * @param nome Nome.
     */
    public CadastrarPessoaCommand(Long id, String nome) {
        super(nome);

        this.id = id;
    }

    /**
     * @return O identificador.
     */
    public Long getId() {
        return id;
    }

}
