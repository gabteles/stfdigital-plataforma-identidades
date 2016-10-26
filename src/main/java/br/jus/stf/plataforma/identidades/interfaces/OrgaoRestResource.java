package br.jus.stf.plataforma.identidades.interfaces;

import java.util.Arrays;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.wordnik.swagger.annotations.ApiOperation;

import br.jus.stf.plataforma.identidades.application.PessoaApplicationService;
import br.jus.stf.plataforma.identidades.application.commands.CadastrarAssociadoCommand;

/**
 * Api rest de consulta de órgãos
 * 
 * @author Rafael Alencar
 * @since 26.10.2016
 */
@RestController
@RequestMapping("/api/orgaos")
public class OrgaoRestResource {

    private static final String ORGAO_INVALIDO_PATTERN = "Dados Inválidos: %S";

    @Autowired
    private PessoaApplicationService pessoaApplicationService;

    /**
     * Cria ou carrega uma pessoa e a associa a um Órgão.
     * 
     * @param command
     * @param result Resultado da validação.
     */
    @ApiOperation("Cria ou carrega uma pessoa e a associa a um Órgão.")
    @RequestMapping(value = "/{orgaoId}/associados", method = RequestMethod.POST)
    public void cadastrarAssociado(@PathVariable("orgaoId") Long orgaoId,
            @RequestBody @Valid CadastrarAssociadoCommand command, BindingResult result) {
        isValid(orgaoId, command.getOrgao(), result);

        pessoaApplicationService.handle(command);
    }

    private static void isValid(Long orgaoIdPath, Long orgaoIdCommand, BindingResult binding) {
        isValid(binding);

        if (!orgaoIdPath.equals(orgaoIdCommand)) {
            throw new IllegalArgumentException(message(
                    Arrays.asList(
                            new ObjectError("Associado", "Identificadores do comando incompatíveis."))));
        }
    }

    private static void isValid(BindingResult result) {
        if (result.hasErrors()) {
            throw new IllegalArgumentException(message(result.getAllErrors()));
        }
    }

    private static String message(List<ObjectError> errors) {
        return String.format(ORGAO_INVALIDO_PATTERN, errors);
    }
}
