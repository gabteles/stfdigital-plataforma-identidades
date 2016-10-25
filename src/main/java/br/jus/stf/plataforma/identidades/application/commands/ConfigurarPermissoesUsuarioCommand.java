package br.jus.stf.plataforma.identidades.application.commands;

import java.util.Set;

import javax.validation.constraints.NotNull;

import com.wordnik.swagger.annotations.ApiModel;
import com.wordnik.swagger.annotations.ApiModelProperty;

/**
 * Classe usada para transportar os dados de configuração de permissões de usuário do front-end para o back-end.
 * 
 * @author Anderson.Araujo
 * 
 * @since 03.12.2015
 *
 */
@ApiModel("Classe usada para transportar os dados de configuração de permissões do front-end para o back-end.")
public class ConfigurarPermissoesUsuarioCommand {

    @ApiModelProperty(value = "Id do usuário.", required = true)
    @NotNull
    private Long idUsuario;

    @ApiModelProperty(value = "Papeis adicionados ao usuário.")
    private Set<Long> papeisAdicionados;

    @ApiModelProperty(value = "Grupos adicionados ao usuário.")
    private Set<Long> gruposAdicionados;

    @ApiModelProperty(value = "Recursos adicionados ao usuário.")
    private Set<Long> recursosAdicionados;

    @ApiModelProperty(value = "Papeis removidos do usuário.")
    private Set<Long> papeisRemovidos;

    @ApiModelProperty(value = "Grupos removidos do usuário.")
    private Set<Long> gruposRemovidos;

    @ApiModelProperty(value = "Recursos removidos do usuário.")
    private Set<Long> recursosRemovidos;

    public ConfigurarPermissoesUsuarioCommand() {
        // Construtor default
    }

    public Long getIdUsuario() {
        return idUsuario;
    }

    public Set<Long> getPapeisAdicionados() {
        return papeisAdicionados;
    }

    public Set<Long> getGruposAdicionados() {
        return gruposAdicionados;
    }

    public Set<Long> getRecursosAdicionados() {
        return recursosAdicionados;
    }

    public Set<Long> getPapeisRemovidos() {
        return papeisRemovidos;
    }

    public Set<Long> getGruposRemovidos() {
        return gruposRemovidos;
    }

    public Set<Long> getRecursosRemovidos() {
        return recursosRemovidos;
    }
}
