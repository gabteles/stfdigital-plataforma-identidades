package br.jus.stf.plataforma.identidades.interfaces.dto;

import com.wordnik.swagger.annotations.ApiModel;
import com.wordnik.swagger.annotations.ApiModelProperty;

/**
 * @author Lucas.Rodrigues
 * @since 09.06.2016
 */
@ApiModel(value = "Representa uma pessoa.")
public class PessoaDto {

    @ApiModelProperty(value = "O identificador da pessoa.")
    private Long id;

    @ApiModelProperty(value = "O nome da pessoa.")
    private String nome;

    /**
     * @param id O identificador da pessoa.
     * @param nome O nome da pessoa.
     */
    public PessoaDto(Long id, String nome) {
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
