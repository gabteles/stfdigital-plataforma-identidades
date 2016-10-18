package br.jus.stf.plataforma.identidades.domain.model.corporativo;

import static javax.persistence.CascadeType.ALL;

import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.apache.commons.lang3.Validate;

import br.jus.stf.core.framework.domaindrivendesign.EntitySupport;
import br.jus.stf.core.shared.identidade.PessoaId;

/**
 * @author Rafael Alencar
 * 
 * @since 1.0.0
 * @since 01.07.2016
 */
@Entity
@Table(name = "ORGAO", schema = "CORPORATIVO")
public class Orgao extends EntitySupport<Orgao, PessoaId> {
	
	@EmbeddedId
	private PessoaId id;
	
	@Column(name = "NOM_PESSOA", nullable = false)
	private String nome;
	
	@OneToMany(cascade = ALL)
    @JoinColumn(name = "SEQ_PESSOA_ORGAO", referencedColumnName = "SEQ_PESSOA", nullable = false)
	private Set<Associado> associados = new HashSet<>();
	
	public Orgao() {
		// Deve ser usado apenas pelo Hibernate, que sempre usa o construtor default antes de popular uma nova instância.
	}
	
	/**
	 * @param id
	 * @param nome
	 */
	public Orgao(PessoaId id, String nome) {
		Validate.notNull(id, "Identificador requerido.");
		Validate.notBlank(nome, "Nome requerido.");
		
		this.id = id;
		this.nome = nome;
	}
	
	/**
	 * @param id
	 * @param nome
	 * @param associados
	 */
	public Orgao(PessoaId id, String nome, Set<Associado> associados) {
		this(id, nome);
		
		Validate.notEmpty(associados, "Lista de associados requerida.");
		
		this.associados = associados;
	}
	
	/**
	 * @return
	 */
	public String nome() {
		return nome;
	}
	
	/**
	 * @return
	 */
	public Set<Associado> associados() {
		return Collections.unmodifiableSet(associados);
	}
	
	/**
	 * @param associado
	 */
	public void adicionarAssociado(Associado associado) {
		Validate.notNull(associado, "Associado requerido.");
		
		this.associados.add(associado);
	}
	
	/**
	 * @param pessoa
	 * @return
	 */
	public boolean isRepresentadoPor(Pessoa pessoa) {
		return associados.stream().anyMatch(associado -> associado.isRepresentante(pessoa));
	}
	
	@Override
	public String toString() {
		return String.format("%d - %s", id.toLong(), nome);
	}
	
	@Override
	public PessoaId identity() {
		return id;
	}

}
