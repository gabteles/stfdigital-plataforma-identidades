package br.jus.stf.plataforma.userauthentication.application;

import static org.springframework.transaction.annotation.Propagation.REQUIRES_NEW;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import br.jus.stf.core.shared.identidade.PessoaId;
import br.jus.stf.plataforma.userauthentication.application.commands.CadastrarPessoaCommand;
import br.jus.stf.plataforma.userauthentication.application.commands.CadastrarPessoasCommand;
import br.jus.stf.plataforma.userauthentication.domain.model.identidade.Pessoa;
import br.jus.stf.plataforma.userauthentication.domain.model.identidade.PessoaFactory;
import br.jus.stf.plataforma.userauthentication.domain.model.identidade.PessoaRepository;

/**
 * @author Rodrigo Barreiros
 * 
 * @since 1.0.0
 * @since 25.09.2015
 */
@Component
public class PessoaApplicationService {

	@Autowired
	private PessoaRepository pessoaRepository;
	
	@Autowired
	private PessoaFactory pessoaFactory;
	
	// TODO: Verificar como será a integração com o Elastic
//	@Autowired
//	private PessoaApplicationEvent pessoaApplicationEvent;

	@Transactional
	public List<PessoaId> alocarIdPessoas(CadastrarPessoasCommand command) {
		return command.getNomes().stream().map(nome -> {
			List<Pessoa> pessoas = pessoaRepository.findByNomeContaining(nome);

			if (pessoas.isEmpty()) {
				return pessoaRepository.nextId();
			}

			return pessoas.get(0).identity();
		}).collect(Collectors.toList());
	}
	
	@Transactional(propagation = REQUIRES_NEW)
	public void handle(CadastrarPessoaCommand command) {
		Pessoa pessoa = pessoaFactory.novaPessoa(new PessoaId(command.getId()), command.getNome(), command.getCpf(),
				command.getOab(), command.getEmail(), command.getTelefone());
		
		pessoaRepository.save(pessoa);
	}
	
	@Transactional
	public List<Pessoa> handle(CadastrarPessoasCommand command) {
		return command.getNomes().stream().map(nome -> carregarPessoa(nome)).collect(Collectors.toList());
	}
	
	private Pessoa carregarPessoa(String nome) {
		List<Pessoa> pessoas = pessoaRepository.findByNomeContaining(nome);
		
		if (pessoas.isEmpty()) {
			Pessoa pessoa = new Pessoa(pessoaRepository.nextId(), nome);

			pessoaRepository.save(pessoa);
//			pessoaApplicationEvent.pessoaCadastrada(pessoa);
			return pessoa;
		}
		
		return pessoas.get(0);
	}

}
