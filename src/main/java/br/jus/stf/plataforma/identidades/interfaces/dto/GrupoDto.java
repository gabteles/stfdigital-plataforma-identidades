package br.jus.stf.plataforma.identidades.interfaces.dto;

import com.wordnik.swagger.annotations.ApiModel;
import com.wordnik.swagger.annotations.ApiModelProperty;

/**
 * Classe responsável por transportar dados de grupos de usuários para o front-end.
 * 
 * @author Anderson.Araujo
 * 
 * @since 02.12.2015
 *
 */
@ApiModel(value = "Classe responsável por transportar dados de grupos para o front-end.")
public class GrupoDto {

    @ApiModelProperty("Id do grupo.")
    private Long id;

    @ApiModelProperty("Nome do grupo.")
    private String nome;

    /**
     * @param id Id do grupo.
     * @param nome Nome do grupo.
     */
    public GrupoDto(Long id, String nome) {
        this.id = id;
        this.nome = nome;
    }

    public Long getId() {
        return id;
    }

    public String getNome() {
        return nome;
    }
}
