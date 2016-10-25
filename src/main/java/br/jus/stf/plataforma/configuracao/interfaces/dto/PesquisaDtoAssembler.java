package br.jus.stf.plataforma.configuracao.interfaces.dto;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Component;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import br.jus.stf.plataforma.configuracao.domain.model.Pesquisa;

/**
 * Monta o dto de Pesquisa que está pronto para ser consumido pelo mecanismo
 * de pesquisa avançada
 * 
 * @author lucas.rodrigues
 *
 */
@Component
public class PesquisaDtoAssembler {

    private ObjectMapper mapper = new ObjectMapper();

    public PesquisaDto toDto(Pesquisa pesquisa) {
        TypeReference<List<Map<String, Object>>> typeRef = new TypeReference<List<Map<String, Object>>>() {
        };
        List<Map<String, Object>> criterio;
        try {
            criterio = mapper.readValue(pesquisa.criterio(), typeRef);
        } catch (IOException e) {
            throw new IllegalArgumentException("Não foi possível converter os critérios de pesquisa!", e);
        }
        return new PesquisaDto(pesquisa.identity().toLong(), pesquisa.descricao(), pesquisa.contexto(),
                pesquisa.isExecucaoAutomatica(), criterio);
    }

}
