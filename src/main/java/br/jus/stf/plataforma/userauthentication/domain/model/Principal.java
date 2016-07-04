package br.jus.stf.plataforma.userauthentication.domain.model;

import java.util.Set;

/**
 * @author Rafael Alencar
 * 
 * @since 1.0.0
 * @since 03.06.2016
 */
public interface Principal {

	/**
	 * @return
	 */
	Set<Recurso> recursos();
	
	/**
	 * @param recursos
	 */
	void atribuirRecursos(final Set<Recurso> recursos);
	
	/**
	 * @param recursos
	 */
	void removerRecursos(final Set<Recurso> recursos);
	
	/**
	 * @param recurso
	 * @return
	 */
	public default boolean possuiAcessoNo(Recurso recurso) {
		return recursos().contains(recurso);
	}
	
}
