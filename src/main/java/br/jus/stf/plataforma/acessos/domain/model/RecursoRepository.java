package br.jus.stf.plataforma.acessos.domain.model;

import java.util.List;

/**
 * @author Rafael Alencar
 * 
 * @since 1.0.0
 * @since 03.06.2016
 */
public interface RecursoRepository {
	
	public Recurso findOne(Long id);
	
	public Recurso findOne(String nome, ResourceType tipoRecurso);
	
	public List<Recurso> findAll();
	
	public <R extends Recurso> R save(R recurso);

}