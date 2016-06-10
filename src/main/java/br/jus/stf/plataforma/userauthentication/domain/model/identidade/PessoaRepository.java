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

	public Pessoa findOne(PessoaId pessoaId);

	public <P extends Pessoa> P save(P pessoa);
	
	public PessoaId nextId();
	
	public List<Pessoa> findByNomeContaining(String nome);
	
	public Pessoa findByCpf(String cpf);
	
	public List<Pessoa> findAll();
	
}