package br.jus.stf.plataforma.identidades.interfaces.dto;

import com.wordnik.swagger.annotations.ApiModel;
import com.wordnik.swagger.annotations.ApiModelProperty;

/**
 * Classe responsável por transportar os dados de usuário do back-end para o front-end.
 * 
 * @author Anderson.Araujo
 * 
 * @since 27.11.2015
 *
 */
@ApiModel(value = "Classe responsável por transportar os dados de usuário do back-end para o front-end.")
public class UsuarioDto {

    @ApiModelProperty("Id do usuário.")
    private Long id;

    @ApiModelProperty("Login do usuário.")
    private String login;

    @ApiModelProperty("Nome do usuário.")
    private String nome;

    @ApiModelProperty("Identificador da pessoa.")
    private Long pessoaId;

    @ApiModelProperty("Setor de lotação do usuário.")
    private SetorDto setorLotacao;

    public UsuarioDto() {
        // Construtor default
    }

    /**
     * @param id Id do usuário.
     * @param login Login do usuário.
     * @param nome Nome do usuário.
     * @param pessoaId Identificador da pessoa.
     * @param setorLotacao Setor de lotação do usuário.
     */
    public UsuarioDto(Long id, String login, String nome, Long pessoaId, SetorDto setorLotacao) {
        this.id = id;
        this.login = login;
        this.nome = nome;
        this.pessoaId = pessoaId;
        this.setorLotacao = setorLotacao;
    }

    public Long getId() {
        return this.id;
    }

    public String getLogin() {
        return this.login;
    }

    public String getNome() {
        return this.nome;
    }

    public Long getPessoaId() {
        return this.pessoaId;
    }

    public SetorDto getSetorLotacao() {
        return this.setorLotacao;
    }

}
