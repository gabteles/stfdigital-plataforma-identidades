package br.jus.stf.plataforma.identidades.interfaces.actions;

import org.springframework.beans.factory.annotation.Autowired;

import br.jus.stf.plataforma.identidades.application.AcessosApplicationService;
import br.jus.stf.plataforma.identidades.application.commands.ConfigurarPermissoesUsuarioCommand;

//@ActionController(groups = "menu")
public class AcessosActionsResource {
	
	@Autowired
	private AcessosApplicationService acessosApplicationService;
	
//	@ActionMapping(id = "configurar-permissao", name = "Configurar Permissões", resourcesMode = ResourcesMode.None)
	public void configurarPermissoesUsuario(ConfigurarPermissoesUsuarioCommand command){
		this.acessosApplicationService.handle(command);
	}
}
