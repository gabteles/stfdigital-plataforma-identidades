package br.jus.stf.plataforma.configuracao.domain.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Embeddable;

import br.jus.stf.core.framework.domaindrivendesign.ValueObjectSupport;

/**
 * @author Lucas Rodrigues
 * 
 * @since 1.0.0
 * @since 22.09.2016
 */
@Embeddable
public class PesquisaId extends ValueObjectSupport<PesquisaId> implements Serializable {
    
    private static final long serialVersionUID = 1L;
    
    @Column(name = "SEQ_PESQUISA")
    private Long sequencial;
    
    PesquisaId() {
    	// Deve ser usado apenas pelo Hibernate, que sempre usa o construtor default antes de popular uma nova instância.
    }
    
    public PesquisaId(Long sequencial) {
        this.sequencial = sequencial;
    }
    
    public Long toLong() {
        return sequencial;
    }

     @Override
    public String toString(){
        return sequencial.toString();
    }
    
}
