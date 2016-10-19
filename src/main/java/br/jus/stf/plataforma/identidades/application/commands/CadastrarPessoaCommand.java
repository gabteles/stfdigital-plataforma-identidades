package br.jus.stf.plataforma.identidades.application.commands;

import javax.validation.constraints.NotNull;

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
public class CadastrarPessoaCommand extends CadastrarPessoaAbstractCommand {

    @NotNull
    @ApiModelProperty(value = "Identificador", required = true)
    private Long id;
    
    public CadastrarPessoaCommand() {
        // Construtor default.
    }

    /**
     * @param id
     * @param nome
     */
    public CadastrarPessoaCommand(Long id, String nome) {
        super(nome);

        this.id = id;
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

}
