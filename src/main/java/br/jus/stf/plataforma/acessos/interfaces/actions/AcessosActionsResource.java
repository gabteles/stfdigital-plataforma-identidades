package br.jus.stf.plataforma.acessos.interfaces.actions;

import org.springframework.beans.factory.annotation.Autowired;

import br.jus.stf.plataforma.acessos.application.AcessosApplicationService;
import br.jus.stf.plataforma.acessos.interfaces.commands.ConfigurarPermissoesUsuarioCommand;

//@ActionController(groups = "menu")
public class AcessosActionsResource {
	
	@Autowired
	private AcessosApplicationService acessosApplicationService;
	
//	@ActionMapping(id = "configurar-permissao", name = "Configurar Permissões", resourcesMode = ResourcesMode.None)
	public void configurarPermissoesUsuario(ConfigurarPermissoesUsuarioCommand command){
		this.acessosApplicationService.configurarPermissoesUsuario(command.getIdUsuario(), command.getPapeisAdicionados(), 
				command.getGruposAdicionados(), command.getPapeisRemovidos(), command.getGruposRemovidos());
	}
}
