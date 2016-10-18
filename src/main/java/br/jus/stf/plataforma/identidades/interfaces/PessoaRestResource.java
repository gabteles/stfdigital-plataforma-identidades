package br.jus.stf.plataforma.identidades.interfaces;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
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
import br.jus.stf.plataforma.identidades.application.PessoaApplicationService;
import br.jus.stf.plataforma.identidades.application.commands.CadastrarAssociadoCommand;
import br.jus.stf.plataforma.identidades.application.commands.CadastrarPessoaCommand;
import br.jus.stf.plataforma.identidades.application.commands.CadastrarPessoasCommand;
import br.jus.stf.plataforma.identidades.domain.model.corporativo.Pessoa;
import br.jus.stf.plataforma.identidades.domain.model.corporativo.PessoaRepository;
import br.jus.stf.plataforma.identidades.domain.model.validation.CNPJUtils;
import br.jus.stf.plataforma.identidades.domain.model.validation.CPFUtils;
import br.jus.stf.plataforma.identidades.interfaces.dto.PessoaDto;
import br.jus.stf.plataforma.identidades.interfaces.dto.PessoaDtoAssembler;

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
     * @param command
     * @param result
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
     * Retorna um Dto com a Pessoa cadastrada
     * 
     * @param command
     * @param result
     * @return Dto com a Pessoa cadastrada
     */
    @RequestMapping(value = "/cadastrar", method = RequestMethod.POST)
    public PessoaDto cadastrarPessoa(@RequestBody @Valid CadastrarPessoaCommand command, BindingResult result) {
        if (result.hasErrors()) {
            throw new IllegalArgumentException(result.getAllErrors().toString());
        }

        Pessoa pessoa = pessoaApplicationService.handle(command);

        return pessoaDtoAssembler.toDto(pessoa);
    }

    /**
     * Retorna as pessoas cadastradas na mesma ordem de envio dos nomes
     * 
     * @param command
     * @param result
     * @return
     */
    @RequestMapping(value = "", method = RequestMethod.POST)
    public List<PessoaDto> cadastrarPessoas(@RequestBody @Valid CadastrarPessoasCommand command, BindingResult result) {
        if (result.hasErrors()) {
            throw new IllegalArgumentException(result.getAllErrors().toString());
        }

        return pessoaApplicationService.handle(command).stream().map(pessoaDtoAssembler::toDto)
                .collect(Collectors.toList());
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
        return pessoaRepository.findAll().stream().map(pessoaDtoAssembler::toDto).collect(Collectors.toList());
    }

    /**
     * @param numero
     * @return
     */
    @RequestMapping(value = "/documento/{numero}", method = RequestMethod.GET)
    public List<PessoaDto> consultarPessoasPorNumero(@PathVariable("numero") String numero) {
        List<PessoaDto> pessoas = new ArrayList<>();
        PessoaDto pessoa;

        if (CPFUtils.isValido(numero)) {
            pessoa = consultarPessoaPorCPF(numero);

            if (pessoa != null) {
                pessoas.add(pessoa);
            } else {
                pessoa = consultarPessoaWSRF(numero);

                Optional.ofNullable(pessoa).ifPresent(pessoas::add);
            }
        } else if (CNPJUtils.isValido(numero)) {
            // TODO Implementar
        }

        return pessoas;
    }

    /**
     * Cria ou carrega uma pessoa e a associa a um Órgão.
     * 
     * @param command
     * @param result
     */
    @RequestMapping(value = "/associado", method = RequestMethod.POST)
    public void cadastrarAssociado(@RequestBody @Valid CadastrarAssociadoCommand command, BindingResult result) {
        if (result.hasErrors()) {
            throw new IllegalArgumentException(result.getAllErrors().toString());
        }

        pessoaApplicationService.handle(command);
    }

    private PessoaDto consultarPessoaPorCPF(String cpf) {
        Pessoa pessoa = pessoaRepository.findByCpf(cpf);

        return pessoaDtoAssembler.toDto(pessoa);
    }

    private PessoaDto consultarPessoaWSRF(String cpf) {
        // TODO Implementar
        return null;
    }
}
