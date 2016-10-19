package br.jus.stf.plataforma.configuracao.application.commands;

import javax.validation.constraints.NotNull;

import com.wordnik.swagger.annotations.ApiModel;
import com.wordnik.swagger.annotations.ApiModelProperty;

/**
 * @author lucas.rodrigues
 * @since 05.10.2016
 */
@ApiModel(description = "Command que realiza a ação de exclusão de uma pesquisa avançada.")
public class ExcluirPesquisaAvancadaCommand {

    @ApiModelProperty(value = "Identificador da pesquisa.", required = true)
    @NotNull
    private Long id;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

}
