package br.jus.stf.plataforma.identidades.application.commands;

import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.NotBlank;

import com.wordnik.swagger.annotations.ApiModel;
import com.wordnik.swagger.annotations.ApiModelProperty;

/**
 * @author Rafael Alencar
 *
 * @since 1.0.0
 * @since 01.07.2016
 */
@ApiModel("Command utilizado para cadastro de um associado.")
public class CadastrarAssociadoCommand {

    @ApiModelProperty(value = "Nome do associado.", required = true)
    @NotBlank
    private String nome;

    @ApiModelProperty(value = "CPF do associado.", required = true)
    @NotBlank
    private String cpf;

    @ApiModelProperty(value = "Tipo de associação.", required = true)
    @NotBlank
    private String tipo;

    @ApiModelProperty(value = "Cargo/função do associado.")
    private String cargoFuncao;

    @ApiModelProperty(value = "Órgão da associação.", required = true)
    @NotNull
    private Long orgao;

    public CadastrarAssociadoCommand() {
        // Construtor default
    }

    public String getNome() {
        return nome;
    }

    public String getCpf() {
        return cpf;
    }

    public String getTipo() {
        return tipo;
    }

    public String getCargoFuncao() {
        return cargoFuncao;
    }

    public Long getOrgao() {
        return orgao;
    }

}
