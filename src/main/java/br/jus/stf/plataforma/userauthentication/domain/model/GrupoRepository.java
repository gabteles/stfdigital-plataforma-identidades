package br.jus.stf.plataforma.userauthentication.domain.model;

import java.util.List;

import br.jus.stf.core.shared.userauthentication.GrupoId;

/**
 * @author Rafael Alencar
 * 
 * @since 1.0.0
 * @since 03.06.2016
 */
public interface GrupoRepository {
	
	public Grupo findOne(GrupoId id);
	
	public Grupo findOne(String nome, TipoGrupo tipo);
	
	public List<Grupo> findAll();
	
	public List<Recurso> findRecursoByGrupo(GrupoId id);
	
	public GrupoId nextId();
	
	public <G extends Grupo> G save(G grupo);

}