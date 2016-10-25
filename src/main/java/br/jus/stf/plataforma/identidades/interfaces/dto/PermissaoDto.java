package br.jus.stf.plataforma.identidades.interfaces.dto;

import com.wordnik.swagger.annotations.ApiModel;
import com.wordnik.swagger.annotations.ApiModelProperty;

/**
 * @author Lucas.Rodrigues
 * @since 03.06.2016
 */
@ApiModel(value = "Classe responsável por transportar descrição da permissão para o front-end.")
public class PermissaoDto {

    @ApiModelProperty("Descrição da permissão, formada junção do tipo da informação, tipo da permissão e segmento.")
    private String descricao;

    /**
     * @param descricao Descrição da permissão, formada junção do tipo da informação, tipo da permissão e segmento.
     */
    public PermissaoDto(String descricao) {
        this.descricao = descricao;
    }

    public String getDescricao() {
        return descricao;
    }

}
