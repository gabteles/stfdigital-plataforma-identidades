package br.jus.stf.plataforma.identidades.domain.model.corporativo;

import java.util.Optional;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import org.apache.commons.lang3.Validate;

import br.jus.stf.core.framework.domaindrivendesign.ValueObjectSupport;

/**
 * @author Rafael Alencar
 * 
 * @since 1.0.0
 * @since 01.07.2016
 */
@Entity
@Table(name = "ASSOCIADO", schema = "CORPORATIVO")
public class Associado extends ValueObjectSupport<Associado> {
	
	@Id
	@Column(name = "SEQ_ASSOCIADO")
	@SequenceGenerator(name = "ASSOCIADO_ID", sequenceName = "CORPORATIVO.SEQ_ASSOCIADO", allocationSize = 1)
	@GeneratedValue(generator = "ASSOCIADO_ID", strategy=GenerationType.SEQUENCE)
	private Long id;
	
	@ManyToOne
	@JoinColumn(name = "SEQ_PESSOA", referencedColumnName = "SEQ_PESSOA", nullable = false)
	private Pessoa pessoa;
	
	@Column(name = "TIP_ASSOCIADO", nullable = false)
	@Enumerated(EnumType.STRING)
	private TipoAssociado tipo;
	
	@Column(name = "DSC_CARGO_FUNCAO")
	private String cargoFuncao;
	
	public Associado() {
		// Deve ser usado apenas pelo Hibernate, que sempre usa o construtor default antes de popular uma nova instância.
	}
	
	/**
	 * @param pessoa
	 * @param nome
	 * @param tipo
	 * @param cargoFuncao
	 */
	public Associado(Pessoa pessoa, TipoAssociado tipo, String cargoFuncao) {
		Validate.notNull(pessoa, "Pessoa requerida.");
		Validate.notNull(tipo, "Tipo requerido.");
		
		this.pessoa = pessoa;
		this.tipo = tipo;
		this.cargoFuncao = cargoFuncao;
	}
	
	/**
	 * @return
	 */
	public Long toLong() {
		return id;
	}
	
	/**
	 * @return
	 */
	public Pessoa pessoa() {
		return pessoa;
	}
	
	/**
	 * @return
	 */
	public TipoAssociado tipo() {
		return tipo;
	}
	
	/**
	 * @return
	 */
	public String cargoFuncao() {
		return cargoFuncao;
	}
	
	/**
	 * @param pessoa
	 * @return
	 */
	public boolean isRepresentante(Pessoa pessoa) {
		return Optional.ofNullable(pessoa).isPresent() && TipoAssociado.REPRESENTANTE.equals(tipo)
				&& pessoa.sameIdentityAs(this.pessoa);
	}
	
	@Override
	public String toString() {
		return String.format("%s - %s", pessoa.nome(), tipo.descricao());
	}

}
