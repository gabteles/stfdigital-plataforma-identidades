package br.jus.stf.plataforma.identidades.domain.model;

/**
 * @author Rafael Alencar
 * 
 * @since 1.0.0
 * @since 03.06.2016
 */
public enum TipoGrupo {

    COMISSAO("Comissão"),
    CONFIGURACAO("Configuração"),
    GRUPO_TRABALHO("Grupo de trabalho"),
    ORGAO_CONVENIADO("Órgão conveniado"),
    SETOR("Setor"),
    TRIBUNAL("Tribunal");

    private String descricao;

    private TipoGrupo(String descricao) {
        this.descricao = descricao;
    }

    /**
     * @return
     */
    public String descricao() {
        return descricao;
    }

}
