package br.jus.stf.plataforma.userauthentication.interfaces;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import br.jus.stf.core.shared.eventos.EnvolvidoRegistrado;
import br.jus.stf.plataforma.userauthentication.application.PessoaApplicationService;
import br.jus.stf.plataforma.userauthentication.application.commands.CadastrarPessoaCommand;
import br.jus.stf.plataforma.userauthentication.infra.RabbitConfiguration;

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
    
    @RabbitListener(queues = RabbitConfiguration.PARTE_REGISTRADA_QUEUE)
    public void handle(EnvolvidoRegistrado event) {
    	pessoaApplicationService.handle(new CadastrarPessoaCommand(event.getPessoaId(), event.getNome(), null, null, null, null));
    }

}
