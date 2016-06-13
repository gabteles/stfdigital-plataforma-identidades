package br.jus.stf.plataforma.userauthentication.interfaces;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import br.jus.stf.core.shared.identidade.PessoaId;
import br.jus.stf.plataforma.userauthentication.application.PessoaApplicationService;
import br.jus.stf.plataforma.userauthentication.application.commands.CadastrarPessoasCommand;
import br.jus.stf.plataforma.userauthentication.domain.model.identidade.Pessoa;
import br.jus.stf.plataforma.userauthentication.domain.model.identidade.PessoaRepository;
import br.jus.stf.plataforma.userauthentication.domain.model.validation.CNPJUtils;
import br.jus.stf.plataforma.userauthentication.domain.model.validation.CPFUtils;
import br.jus.stf.plataforma.userauthentication.interfaces.dto.PessoaDto;
import br.jus.stf.plataforma.userauthentication.interfaces.dto.PessoaDtoAssembler;

/**
 * Api rest de consulta e cadastro de pessoa
 * 
 * @author Lucas Rodrigues
 */
@RestController
@RequestMapping("/api/pessoas")
public class PessoaRestResource {

	@Autowired
	private PessoaApplicationService pessoaApplicationService;	

	@Autowired
	private PessoaDtoAssembler pessoaDtoAssembler;
	
	@Autowired
	private PessoaRepository pessoaRepository;
	
	/**
	 * Retorna uma lista de IDs alocados para
	 * cadastro ou reutilização de pessoas
	 * 
	 * @return
	 */
	@RequestMapping(value = "/alocar", method = RequestMethod.POST)
	public List<Long> alocarIdPessoas(@RequestBody @Valid CadastrarPessoasCommand command, BindingResult result) {
		if (result.hasErrors()) {
			throw new IllegalArgumentException(result.getAllErrors().toString());
		}
		
		return pessoaApplicationService.alocarIdPessoas(command).stream().map(pessoaId -> pessoaId.toLong())
				.collect(Collectors.toList());
	}
	
	/**
	 * Retorna as pessoas cadastradas na mesma ordem de envio dos nomes
	 * 
	 * @param command
	 * @return
	 */
	@RequestMapping(value = "", method = RequestMethod.POST)
	public List<PessoaDto> cadastrarPessoas(@RequestBody @Valid CadastrarPessoasCommand command, BindingResult result) {
		if (result.hasErrors()) {
			throw new IllegalArgumentException(result.getAllErrors().toString());
		}
		
		return pessoaApplicationService.handle(command).stream()
				.map(pessoa -> pessoaDtoAssembler.toDto(pessoa)).collect(Collectors.toList());
	}
	
	/**
	 * Pesquisa uma pessoa pela identificação
	 * 
	 * @param pessoaId
	 * @return
	 */
	@RequestMapping(value = "/{pessoaId}", method = RequestMethod.GET)
	public PessoaDto pesquisar(@PathVariable("pessoaId") Long pessoaId) {
		return pessoaDtoAssembler.toDto(pessoaRepository.findOne(new PessoaId(pessoaId)));
	}
	
	/**
	 * Retorna uma lista com todas as pessoas
	 * 
	 * @return
	 */
	@RequestMapping(value = "", method = RequestMethod.GET)
	public List<PessoaDto> listar() {
		return pessoaRepository.findAll().stream().map(pessoa -> pessoaDtoAssembler.toDto(pessoa))
				.collect(Collectors.toList());
	}
	
	@RequestMapping(value = "/documento/{numero}", method = RequestMethod.GET)
	public List<PessoaDto> consultarPessoasPorNumero(@PathVariable("numero") String numero) {
		List<PessoaDto> pessoas = new ArrayList<PessoaDto>();
		PessoaDto pessoa = null;
		
		if (CPFUtils.isValido(numero)){
			pessoa = consultarPessoaPorCPF(numero);
			
			if (pessoa != null){
				pessoas.add(pessoa);
			} else {
				pessoa = consultarPessoaWSRF(numero);
			}
		} else if (CNPJUtils.isValido(numero)){
			
		}
		
		return null;
	}
	
	private PessoaDto consultarPessoaPorCPF(String cpf){
		Pessoa pessoa = pessoaRepository.findByCpf(cpf);
		
		return pessoaDtoAssembler.toDto(pessoa);
	}

	private PessoaDto consultarPessoaWSRF(String cpf){
		return null;
	}
}
