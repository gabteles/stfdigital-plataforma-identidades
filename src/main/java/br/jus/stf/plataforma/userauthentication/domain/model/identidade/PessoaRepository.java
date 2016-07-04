package br.jus.stf.plataforma.userauthentication.domain.model.identidade;

import java.util.List;

import br.jus.stf.core.shared.identidade.PessoaId;

/**
 * @author Rafael Alencar
 * 
 * @since 1.0.0
 * @since 07.06.2016
 */
public interface PessoaRepository {

	/**
	 * @param pessoaId
	 * @return
	 */
	Pessoa findOne(PessoaId pessoaId);

	/**
	 * @param pessoa
	 * @return
	 */
	<P extends Pessoa> P save(P pessoa);
	
	/**
	 * @return
	 */
	PessoaId nextId();
	
	/**
	 * @param nome
	 * @return
	 */
	List<Pessoa> findByNomeContaining(String nome);
	
	/**
	 * @param cpf
	 * @return
	 */
	Pessoa findByCpf(String cpf);
	
	/**
	 * @return
	 */
	List<Pessoa> findAll();
	
}