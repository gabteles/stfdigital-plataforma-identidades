package br.jus.stf.plataforma.acessos.domain.model;

import java.util.List;

import br.jus.stf.core.shared.userauthentication.PapelId;
import br.jus.stf.core.shared.userauthentication.RecursoId;

/**
 * @author Rafael Alencar
 * 
 * @since 1.0.0
 * @since 03.06.2016
 */
public interface PapelRepository {
	
	public Papel findOne(PapelId id);
	
	public Papel findOne(String nome);
	
	public List<Papel> findAll();
	
	public List<Recurso> findRecursoByPapel(PapelId id);
	
	public <P extends Papel> P save(P papel);

	public PapelId nextId();

	public List<Papel> findPapelByRecurso(RecursoId id);

}