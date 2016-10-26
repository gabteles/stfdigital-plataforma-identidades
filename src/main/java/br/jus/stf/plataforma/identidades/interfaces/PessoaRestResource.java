package br.jus.stf.plataforma.identidades.interfaces;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import javax.validation.Valid;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.wordnik.swagger.annotations.ApiOperation;

import br.jus.stf.core.shared.identidade.PessoaId;
import br.jus.stf.plataforma.identidades.application.PessoaApplicationService;
import br.jus.stf.plataforma.identidades.application.commands.CadastrarPessoaCommand;
import br.jus.stf.plataforma.identidades.application.commands.CadastrarPessoasCommand;
import br.jus.stf.plataforma.identidades.domain.model.corporativo.Pessoa;
import br.jus.stf.plataforma.identidades.domain.model.corporativo.PessoaRepository;
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

    private static final String PESSOA_INVALIDA_PATTERN = "Dados Inválidos: %S";

    @Autowired
    private PessoaApplicationService pessoaApplicationService;

    @Autowired
    private PessoaDtoAssembler pessoaDtoAssembler;

    @Autowired
    private PessoaRepository pessoaRepository;

    /**
     * Retorna uma lista de IDs alocados para
     * cadastro ou reutilização de pessoas.
     * 
     * @param command Command com a lista de nomes.
     * @param result Resultado das validações.
     * @return Lista de IDs alocados.
     */
    @ApiOperation("Retorna uma lista de IDs alocados para cadastro ou reutilização de pessoas.")
    @RequestMapping(value = "/alocacoes-id", method = RequestMethod.POST)
    public List<Long> alocarIdPessoas(@RequestBody @Valid CadastrarPessoasCommand command, BindingResult result) {
        isValid(result);

        return pessoaApplicationService.alocarIdPessoas(command).stream()
                .map(pessoaId -> pessoaId.toLong())
                .collect(Collectors.toList());
    }

    /**
     * Retorna um Dto com a Pessoa cadastrada.
     * 
     * @param command Command com os dados da pessoa a ser cadastrada.
     * @param result Resultado das validações.
     * @return Dto com a Pessoa cadastrada.
     */
    @ApiOperation("Retorna um Dto com a Pessoa cadastrada.")
    @RequestMapping(value = "/cadastro", method = RequestMethod.POST)
    public PessoaDto cadastrarPessoa(@RequestBody @Valid CadastrarPessoaCommand command, BindingResult result) {
        isValid(result);

        Pessoa pessoa = pessoaApplicationService.handle(command);

        return pessoaDtoAssembler.toDto(pessoa);
    }

    /**
     * Retorna as pessoas cadastradas na mesma ordem de envio dos nomes.
     * 
     * @param command Command com a lista de nomes.
     * @param result Resultado das validações.
     * @return Lista das pessoas cadastradas na mesma ordem de envio dos nomes.
     */
    @ApiOperation("Retorna as pessoas cadastradas na mesma ordem de envio dos nomes.")
    @RequestMapping(value = "", method = RequestMethod.POST)
    public List<PessoaDto> cadastrarPessoas(@RequestBody @Valid CadastrarPessoasCommand command, BindingResult result) {
        isValid(result);

        return pessoaApplicationService.handle(command).stream()
                .map(pessoaDtoAssembler::toDto)
                .collect(Collectors.toList());
    }

    /**
     * Pesquisa uma pessoa pela identificação.
     * 
     * @param pessoaId Identificação da pessoa pesquisada.
     * @return Dto com os dados da pessoa.
     */
    @ApiOperation("Pesquisa uma pessoa pela identificação.")
    @RequestMapping(value = "/{pessoaId}", method = RequestMethod.GET)
    public PessoaDto pesquisar(@PathVariable("pessoaId") Long pessoaId) {
        return pessoaDtoAssembler.toDto(pessoaRepository.findOne(new PessoaId(pessoaId)));
    }

    /**
     * Retorna uma lista com todas as pessoas ou as que possuem um número de documento, quando informado.
     * 
     * @return Listas com todas as pessoas cadastradas ou as que possuem o número de documento informado.
     */
    @ApiOperation("Lista com todas as pessoas ou filtrada por um número de documento, quando informado.")
    @RequestMapping(value = "", method = RequestMethod.GET)
    public List<PessoaDto> consultarPessoas(
            @RequestParam(value = "documento", required = false) String numeroDocumento) {
        List<PessoaDto> pessoas;

        if (StringUtils.isNotBlank(numeroDocumento)) {
            pessoas = consultarPessoasPorNumeroDocumento(numeroDocumento);
        } else {
            pessoas = pessoaRepository.findAll().stream()
                    .map(pessoaDtoAssembler::toDto)
                    .collect(Collectors.toList());
        }

        return pessoas;
    }

    private List<PessoaDto> consultarPessoasPorNumeroDocumento(String numeroDocumento) {
        List<PessoaDto> pessoas = new ArrayList<>(0);
        PessoaDto pessoa;

        if (CPFUtils.isValido(numeroDocumento)) {
            pessoa = consultarPessoaPorCPF(numeroDocumento);

            if (pessoa != null) {
                pessoas.add(pessoa);
            } else {
                pessoa = consultarPessoaWSRF(numeroDocumento);

                Optional.ofNullable(pessoa).ifPresent(pessoas::add);
            }
        }

        return pessoas;
    }

    private PessoaDto consultarPessoaPorCPF(String cpf) {
        Pessoa pessoa = pessoaRepository.findByCpf(cpf);

        return pessoaDtoAssembler.toDto(pessoa);
    }

    private static PessoaDto consultarPessoaWSRF(String cpf) {
        // TODO Implementar chamada ao WS da Receita
        return null;
    }

    private static void isValid(BindingResult result) {
        if (result.hasErrors()) {
            throw new IllegalArgumentException(message(result.getAllErrors()));
        }
    }

    private static String message(List<ObjectError> errors) {
        return String.format(PESSOA_INVALIDA_PATTERN, errors);
    }
}
