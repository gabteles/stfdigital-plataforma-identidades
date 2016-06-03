package br.jus.stf.plataforma.acessos.domain.model;

import java.util.Set;

/**
 * @author Rafael Alencar
 * 
 * @since 1.0.0
 * @since 03.06.2016
 */
public interface Principal {

	public Set<Recurso> recursos();
	
	public void atribuirRecursos(final Set<Recurso> recursos);
	
	public void removerRecursos(final Set<Recurso> recursos);
	
	public default boolean possuiAcessoNo(Recurso recurso) {
		return recursos().contains(recurso);
	}
	
}
