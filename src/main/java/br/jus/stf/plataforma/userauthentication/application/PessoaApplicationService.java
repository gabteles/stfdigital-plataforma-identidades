package br.jus.stf.plataforma.userauthentication.application;

import static org.springframework.transaction.annotation.Propagation.REQUIRES_NEW;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import br.jus.stf.core.framework.component.command.Command;
import br.jus.stf.core.framework.domaindrivendesign.ApplicationService;
import br.jus.stf.core.shared.identidade.PessoaId;
import br.jus.stf.plataforma.userauthentication.application.commands.CadastrarAssociadoCommand;
import br.jus.stf.plataforma.userauthentication.application.commands.CadastrarPessoaCommand;
import br.jus.stf.plataforma.userauthentication.application.commands.CadastrarPessoasCommand;
import br.jus.stf.plataforma.userauthentication.domain.model.identidade.Associado;
import br.jus.stf.plataforma.userauthentication.domain.model.identidade.Orgao;
import br.jus.stf.plataforma.userauthentication.domain.model.identidade.OrgaoRepository;
import br.jus.stf.plataforma.userauthentication.domain.model.identidade.Pessoa;
import br.jus.stf.plataforma.userauthentication.domain.model.identidade.PessoaFactory;
import br.jus.stf.plataforma.userauthentication.domain.model.identidade.PessoaRepository;
import br.jus.stf.plataforma.userauthentication.domain.model.identidade.TipoAssociado;

/**
 * @author Rodrigo Barreiros
 * 
 * @since 1.0.0
 * @since 25.09.2015
 */
@ApplicationService
@Transactional
public class PessoaApplicationService {

	@Autowired
	private PessoaRepository pessoaRepository;
	
	@Autowired
	private PessoaFactory pessoaFactory;
	
	@Autowired
	private OrgaoRepository orgaoRepository;

	/**
	 * @param command
	 * @return
	 */
	@Command(description = "Aloca identificador de pessoas na ordem da lista passada para integração via eventos", value = "alocar-id-pessoas")
	public List<PessoaId> alocarIdPessoas(CadastrarPessoasCommand command) {
		return command.getNomes().stream().map(nome -> {
			List<Pessoa> pessoas = pessoaRepository.findByNomeContaining(nome);

			if (pessoas.isEmpty()) {
				return pessoaRepository.nextId();
			}

			return pessoas.get(0).identity();
		}).collect(Collectors.toList());
	}
	
	/**
	 * @param command
	 * @return
	 */
	@Command("Cadastra uma pessoa")
	@Transactional(propagation = REQUIRES_NEW)
	public Pessoa handle(CadastrarPessoaCommand command) {
		Pessoa pessoa = pessoaFactory.novaPessoa(new PessoaId(command.getId()), command.getNome(), command.getCpf(),
				command.getOab(), command.getEmail(), command.getTelefone());
		
		pessoaRepository.save(pessoa);
		
		return pessoa;
	}
	
	/**
	 * @param command
	 * @return
	 */
	@Command(description = "Recupera uma lista de pessoas, sejam estas novas ou existentes na base", value = "cadastrar-pessoas")
	public List<Pessoa> handle(CadastrarPessoasCommand command) {
		return command.getNomes().stream().map(this::carregarPessoa).collect(Collectors.toList());
	}
	
	/**
	 * @param command
	 * @return
	 */
	@Command(description = "Cadastra associado")
	public void handle(CadastrarAssociadoCommand command) {
		Orgao orgao = orgaoRepository.findOne(new PessoaId(command.getOrgao()));
		TipoAssociado tipo = TipoAssociado.valueOf(command.getTipo());
		Associado associado = new Associado(criarPessoa(command.getNome(), command.getCpf()), tipo,
				command.getCargoFuncao());
		
		orgao.adicionarAssociado(associado);
		orgaoRepository.save(orgao);
	}
	
	private Pessoa criarPessoa(String nome, String cpf) {
		Pessoa pessoa = pessoaRepository.findByCpf(cpf);

		if (!Optional.ofNullable(pessoa).isPresent()) {
			pessoa = pessoaFactory.novaPessoa(pessoaRepository.nextId(), nome, cpf, null, null, null);

			pessoaRepository.save(pessoa);
		}

		return pessoa;
	}
	
	private Pessoa carregarPessoa(String nome) {
		List<Pessoa> pessoas = pessoaRepository.findByNomeContaining(nome);
		
		if (pessoas.isEmpty()) {
			Pessoa pessoa = pessoaFactory.novaPessoa(pessoaRepository.nextId(), nome, null, null, null, null);

			pessoaRepository.save(pessoa);
			
			return pessoa;
		}
		
		return pessoas.get(0);
	}

}
