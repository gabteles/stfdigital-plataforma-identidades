package br.jus.stf.plataforma.userauthentication.domain.model;

import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

import org.apache.commons.lang3.Validate;

import br.jus.stf.core.framework.domaindrivendesign.EntitySupport;
import br.jus.stf.core.shared.userauthentication.GrupoId;

/**
 * @author Rafael Alencar
 * 
 * @since 1.0.0
 * @since 03.06.2016
 */
@Entity
@Table(name = "GRUPO", schema = "PLATAFORMA", uniqueConstraints = @UniqueConstraint(columnNames = {"NOM_GRUPO", "TIP_GRUPO"}))
public class Grupo extends EntitySupport<Grupo, GrupoId> implements Principal {
	
	@EmbeddedId
	private GrupoId id;
	
	@Column(name = "NOM_GRUPO", nullable = false)
	private String nome;
	
	@Column(name = "TIP_GRUPO", nullable = false)
	@Enumerated(EnumType.STRING)
	private TipoGrupo tipo;
	
	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	@JoinTable(name = "GRUPO_RECURSO", schema = "PLATAFORMA",
		joinColumns = @JoinColumn(name = "SEQ_GRUPO", nullable = false),
		inverseJoinColumns = @JoinColumn(name = "SEQ_RECURSO", nullable = false))
	private Set<Recurso> recursos = new HashSet<Recurso>(0);
	
	public Grupo() {
		// Deve ser usado apenas pelo Hibernate, que sempre usa o construtor default antes de popular uma nova instância.
	}
	
	public Grupo(GrupoId id, String nome, TipoGrupo tipo) {
		Validate.notNull(id, "Identificador requerido.");
		Validate.notBlank(nome, "Nome requerido.");
		Validate.notNull(tipo, "Tipo de grupo requerido.");
		
		this.id = id;
		this.nome = nome;
		this.tipo = tipo;
	}

	public String nome() {
		return nome;
	}
	
	public TipoGrupo tipo() {
		return tipo;
	}
	
	@Override
	public GrupoId identity() {
		return id;
	}
	
	@Override
	public Set<Recurso> recursos() {
		return Collections.unmodifiableSet(recursos);
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
