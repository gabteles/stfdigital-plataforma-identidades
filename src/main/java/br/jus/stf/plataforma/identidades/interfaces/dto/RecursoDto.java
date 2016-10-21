package br.jus.stf.plataforma.identidades.interfaces.dto;

import com.wordnik.swagger.annotations.ApiModel;
import com.wordnik.swagger.annotations.ApiModelProperty;

/**
 * @author Rafael Alencar
 * @since 03.06.2016
 */
@ApiModel(value = "Representa um recurso do sistema.")
public class RecursoDto {

    @ApiModelProperty(value = "Identificador do recurso.")
    private Long id;

    @ApiModelProperty(value = "Nome do recurso.")
    private String nome;

    @ApiModelProperty(value = "Tipo do recurso.")
    private String tipo;

    /**
     * @param id Identificador do recurso.
     * @param nome Nome do recurso.
     * @param tipo Tipo do recurso.
     */
    public RecursoDto(Long id, String nome, String tipo) {
        this.id = id;
        this.nome = nome;
        this.tipo = tipo;
    }

    public Long getId() {
        return id;
    }

    public String getNome() {
        return nome;
    }

    public String getTipo() {
        return tipo;
    }

}
