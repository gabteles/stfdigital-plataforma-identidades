package br.jus.stf.plataforma.configuracao.domain.model;

import java.util.stream.Stream;

import br.jus.stf.core.shared.userauthentication.UsuarioId;

/**
 * @author Lucas Rodrigues
 * 
 * @since 1.0.0
 * @since 22.09.2016
 */
public interface PesquisaRepository {
	
	Pesquisa findOne(PesquisaId id);
	
	Stream<Pesquisa> findByUsuarioAutenticado();
	
	Stream<Pesquisa> findByUsuario(UsuarioId usuario);
	
	<P extends Pesquisa> P save(P pesquisa);
	
	void delete(Pesquisa pesquisa);

	PesquisaId nextId();

}