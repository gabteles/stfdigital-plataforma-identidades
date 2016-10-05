package br.jus.stf.plataforma.configuracao.interfaces;

import java.util.List;
import java.util.stream.Collectors;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import br.jus.stf.plataforma.configuracao.application.PesquisaApplicationService;
import br.jus.stf.plataforma.configuracao.application.commands.ExcluirPesquisaAvancadaCommand;
import br.jus.stf.plataforma.configuracao.application.commands.SalvarPesquisaAvancadaCommand;
import br.jus.stf.plataforma.configuracao.domain.model.PesquisaRepository;
import br.jus.stf.plataforma.configuracao.interfaces.dto.PesquisaDto;
import br.jus.stf.plataforma.configuracao.interfaces.dto.PesquisaDtoAssembler;

/**
 * Api rest de consulta e salvamento de pesquisa
 * 
 * @author Lucas Rodrigues
 */
@RestController
@RequestMapping("/api/pesquisas")
public class PesquisaRestResource {

	@Autowired
	private PesquisaApplicationService pesquisaApplicationService;
	
	@Autowired
	private PesquisaRepository pesquisaRepository;
	
	@Autowired
	private PesquisaDtoAssembler pesquisaDtoAssembler;
	
	/**
	 * Retorna o id da pesquisa salva
	 * 
	 * @param command
	 * @param result
	 * @return
	 */
	@RequestMapping(value = "", method = RequestMethod.POST)
	public Long salvar(@RequestBody @Valid SalvarPesquisaAvancadaCommand command, BindingResult result) {
		if (result.hasErrors()) {
			throw new IllegalArgumentException(result.getAllErrors().toString());
		}
		return pesquisaApplicationService.handle(command);
	}
	
	@RequestMapping(value = "", method = RequestMethod.GET)
	public List<PesquisaDto> listar() {
		return pesquisaRepository.findByUsuarioAutenticado()
				.map(pesquisaDtoAssembler::toDto)
				.collect(Collectors.toList());
	}
	
	@RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
	public void excluir(@Valid ExcluirPesquisaAvancadaCommand command, BindingResult result) {
		if (result.hasErrors()) {
			throw new IllegalArgumentException(result.getAllErrors().toString());
		}
		pesquisaApplicationService.handle(command);
	}
	
}
