package br.jus.stf.plataforma.acessos.domain.model;

import java.util.Collections;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

import org.apache.commons.lang3.Validate;

import br.jus.stf.core.framework.domaindrivendesign.EntitySupport;
import br.jus.stf.core.shared.userauthentication.PapelId;

@Entity
@Table(name = "PAPEL", schema = "PLATAFORMA", uniqueConstraints = @UniqueConstraint(columnNames = {"NOM_PAPEL"}))
public class Papel extends EntitySupport<Papel, PapelId> implements Principal {
	
	@EmbeddedId
	private PapelId id;
	
	@Column(name = "NOM_PAPEL", nullable = false)
	private String nome;
	
	@ManyToOne
	@JoinColumn(name = "SEQ_GRUPO")
	private Grupo grupo;
	
	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	@JoinTable(name = "PAPEL_RECURSO", schema = "PLATAFORMA",
		joinColumns = @JoinColumn(name = "SEQ_PAPEL", nullable = false),
		inverseJoinColumns = @JoinColumn(name = "SEQ_RECURSO", nullable = false))
	private Set<Recurso> recursos = new HashSet<Recurso>(0);
	
	public Papel() {
		// Deve ser usado apenas pelo Hibernate, que sempre usa o construtor default antes de popular uma nova instância.
	}
	
	public Papel(PapelId id, String nome) {
		Validate.notNull(id, "Identificador requerido.");
		Validate.notBlank(nome, "Nome requerido.");
		
		this.id = id;
		this.nome = nome;
	}
	
	public Papel(PapelId id, String nome, Grupo grupo) {
		this(id, nome);
		
		Validate.notNull(grupo, "Grupo requerido.");
		
		this.grupo = grupo;
	}
	
	public String nome() {
		return nome;
	}
	
	public Grupo grupo() {
		return grupo;
	}
	
	@Override
	public PapelId identity() {
		return id;
	}

	@Override
	public Set<Recurso> recursos() {
		Set<Recurso> recursosCompletos = new HashSet<Recurso>();
		
		recursosCompletos.addAll(recursos);
		Optional.ofNullable(grupo).ifPresent(g -> recursosCompletos.addAll(g.recursos()));
		
		return Collections.unmodifiableSet(recursosCompletos);
	}

	@Override
	public void atribuirRecursos(Set<Recurso> recursos) {
		Validate.notEmpty(recursos, "Recursos requeridos.");
		
		this.recursos.addAll(recursos);
	}
	
	@Override
	public void removerRecursos(Set<Recurso> recursos) {
		Validate.notEmpty(recursos, "Recursos requeridos.");
		
		this.recursos.removeAll(recursos);
	}

}
