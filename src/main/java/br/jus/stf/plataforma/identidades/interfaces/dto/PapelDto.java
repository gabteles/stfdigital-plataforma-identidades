package br.jus.stf.plataforma.identidades.interfaces.dto;

import com.wordnik.swagger.annotations.ApiModel;
import com.wordnik.swagger.annotations.ApiModelProperty;

/**
 * Classe responsável por transportar dados de um papél de usuário do back-end para o front-end.
 * 
 * @author Anderson.Araujo
 * 
 * @since 27.11.2015
 *
 */
@ApiModel(value = "Objeto responsável por transportar dados de um papel do back-end para o front-end.")
public class PapelDto {

    @ApiModelProperty(value = "Id do papel.")
    private Long id;

    @ApiModelProperty(value = "Nome do papel.")
    private String nome;

    @ApiModelProperty(value = "Nome do setor associado.")
    private String setor;

    /**
     * @param id Id do papel.
     * @param nome Nome do papel.
     * @param setor Nome do setor associado.
     */
    public PapelDto(Long id, String nome, String setor) {
        this.id = id;
        this.nome = nome;
        this.setor = setor;
    }

    public Long getId() {
        return this.id;
    }

    public String getNome() {
        return this.nome;
    }

    public String getSetor() {
        return this.setor;
    }

}
