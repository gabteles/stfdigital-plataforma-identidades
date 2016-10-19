package br.jus.stf.plataforma.identidades.interfaces;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.stereotype.Component;

import br.jus.stf.core.shared.eventos.EnvolvidoRegistrado;
import br.jus.stf.plataforma.identidades.application.PessoaApplicationService;
import br.jus.stf.plataforma.identidades.application.commands.CadastrarPessoaCommand;

/**
 * @author Rafael Alencar
 * 
 * @since 1.0.0
 * @since 10.06.2016
 */
@Component
public class PessoaEventHandler {

    @Autowired
    private PessoaApplicationService pessoaApplicationService;

    /**
     * @param event
     */
    @StreamListener(EnvolvidoRegistrado.EVENT_KEY)
    public void handle(EnvolvidoRegistrado event) {
        pessoaApplicationService
                .handle(new CadastrarPessoaCommand(event.getPessoaId(), event.getNome()));
    }

}
