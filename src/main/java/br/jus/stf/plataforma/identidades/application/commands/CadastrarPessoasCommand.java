package br.jus.stf.plataforma.identidades.application.commands;

import java.util.List;

import org.hibernate.validator.constraints.NotEmpty;

import com.wordnik.swagger.annotations.ApiModel;
import com.wordnik.swagger.annotations.ApiModelProperty;

/**
 * Comando para cadastro de pessoas
 * 
 * @author Lucas.Rodrigues
 *
 */
@ApiModel("Command que com os nomes das pessoas para cadastro.")
public class CadastrarPessoasCommand {

    @NotEmpty
    @ApiModelProperty(value = "Nomes das pessoas.", required = true)
    private List<String> nomes;

    /**
     * @return Os nomes das pessoas.
     */
    public List<String> getNomes() {
        return nomes;
    }

}
