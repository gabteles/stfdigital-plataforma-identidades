package br.jus.stf.plataforma.configuracao.application;

import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;

import br.jus.stf.core.framework.component.command.Command;
import br.jus.stf.core.framework.domaindrivendesign.ApplicationService;
import br.jus.stf.plataforma.configuracao.application.commands.ExcluirPesquisaAvancadaCommand;
import br.jus.stf.plataforma.configuracao.application.commands.SalvarPesquisaAvancadaCommand;
import br.jus.stf.plataforma.configuracao.domain.model.Pesquisa;
import br.jus.stf.plataforma.configuracao.domain.model.PesquisaId;
import br.jus.stf.plataforma.configuracao.domain.model.PesquisaRepository;

/**
 * @author Lucas.Rodrigues
 *
 */
@ApplicationService
@Transactional
public class PesquisaApplicationService {

    @Autowired
    private PesquisaRepository pesquisaRepository;

    /**
     * @param command
     */
    @Command(description = "Salvar pesquisa avançada", listable = false, targetType = Pesquisa.class)
    public Long handle(SalvarPesquisaAvancadaCommand command) {

        PesquisaId id = Optional.ofNullable(command.getPesquisaId())
                .map(pesquisaId -> pesquisaRepository.findOne(new PesquisaId(pesquisaId)))
                .map(pesquisa -> pesquisa.identity())
                .orElse(pesquisaRepository.nextId());
        Pesquisa pesquisa = new Pesquisa(id, command.getDescricao(), command.getContexto(), command.getCriterio(),
                command.isExecucaoAutomatica());

        pesquisa = pesquisaRepository.save(pesquisa);
        return pesquisa.identity().toLong();
    }

    /**
     * @param command
     */
    @Command(description = "Excluir pesquisa avançada", listable = false, targetType = Pesquisa.class)
    public void handle(ExcluirPesquisaAvancadaCommand command) {

        Pesquisa pesquisa = Optional.ofNullable(command.getId())
                .map(pesquisaId -> pesquisaRepository.findOne(new PesquisaId(pesquisaId)))
                .orElseThrow(IllegalArgumentException::new);
        pesquisaRepository.delete(pesquisa);
    }

}
