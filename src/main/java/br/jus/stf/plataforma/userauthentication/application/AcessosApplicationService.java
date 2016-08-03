package br.jus.stf.plataforma.userauthentication.application;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;

import br.jus.stf.core.framework.component.command.Command;
import br.jus.stf.core.framework.domaindrivendesign.ApplicationService;
import br.jus.stf.core.shared.identidade.PessoaId;
import br.jus.stf.core.shared.userauthentication.GrupoId;
import br.jus.stf.core.shared.userauthentication.PapelId;
import br.jus.stf.core.shared.userauthentication.RecursoId;
import br.jus.stf.core.shared.userauthentication.UsuarioId;
import br.jus.stf.plataforma.userauthentication.application.commands.CadastrarUsuarioCommand;
import br.jus.stf.plataforma.userauthentication.application.commands.ConfigurarPermissoesUsuarioCommand;
import br.jus.stf.plataforma.userauthentication.domain.model.Grupo;
import br.jus.stf.plataforma.userauthentication.domain.model.GrupoRepository;
import br.jus.stf.plataforma.userauthentication.domain.model.Papel;
import br.jus.stf.plataforma.userauthentication.domain.model.PapelRepository;
import br.jus.stf.plataforma.userauthentication.domain.model.Pessoa;
import br.jus.stf.plataforma.userauthentication.domain.model.Recurso;
import br.jus.stf.plataforma.userauthentication.domain.model.RecursoRepository;
import br.jus.stf.plataforma.userauthentication.domain.model.TipoGrupo;
import br.jus.stf.plataforma.userauthentication.domain.model.Usuario;
import br.jus.stf.plataforma.userauthentication.domain.model.UsuarioRepository;

/**
 * @author Lucas.Rodrigues
 *
 */
@ApplicationService
@Transactional
public class AcessosApplicationService {
	
	@Autowired
	private UsuarioRepository usuarioRepository;
	
	@Autowired
	private GrupoRepository grupoRepository;
	
	@Autowired
	private PapelRepository papelRepository;
	
	@Autowired
	private RecursoRepository recursoRepository;
	
	/**
	 * @param command
	 */
	@Command(description = "Registra os grupos, papéis e recursos associados a um usuário")
	public void handle(ConfigurarPermissoesUsuarioCommand command) {
		Set<Papel> papeisAdic = new HashSet<>(0);
		Set<PapelId> papeisRemov = new HashSet<>(0);
		Set<Grupo> gruposAdic = new HashSet<>(0);
		Set<GrupoId> gruposRemov = new HashSet<>(0);
		Set<Recurso> recursosAdic = new HashSet<>(0);
		Set<Recurso> recursosRemov = new HashSet<>(0);
		
		Optional.ofNullable(command.getPapeisRemovidos()).ifPresent(p2 -> p2.forEach(p -> papeisRemov.add(new PapelId(p))));
		Optional.ofNullable(command.getGruposRemovidos()).ifPresent(g2 -> g2.forEach(g -> gruposRemov.add(new GrupoId(g))));
		Optional.ofNullable(command.getRecursosRemovidos()).ifPresent(r2 -> r2.forEach(r -> recursosRemov.add(recursoRepository.findOne(new RecursoId(r)))));
		Optional.ofNullable(command.getPapeisAdicionados()).ifPresent(p1 -> p1.forEach(p -> papeisAdic.add(papelRepository.findOne(new PapelId(p)))));
		Optional.ofNullable(command.getGruposAdicionados()).ifPresent(g1 -> g1.forEach(g -> gruposAdic.add(grupoRepository.findOne(new GrupoId(g)))));
		Optional.ofNullable(command.getRecursosAdicionados()).ifPresent(r1 -> r1.forEach(r -> recursosAdic.add(recursoRepository.findOne(new RecursoId(r)))));
				
		Usuario usuario = usuarioRepository.findOne(new UsuarioId(command.getIdUsuario()));
		if (!papeisRemov.isEmpty()) {
			usuario.removerPapeis(papeisRemov);
		}
		if (!gruposRemov.isEmpty()) {
			usuario.removerGrupos(gruposRemov);
		}
		if (!recursosRemov.isEmpty()) {
			usuario.removerRecursos(recursosRemov);
		}
		if (!papeisAdic.isEmpty()) {
			usuario.atribuirPapeis(papeisAdic);
		}
		if (!gruposAdic.isEmpty()) {
			usuario.atribuirGrupos(gruposAdic);
		}
		if (!recursosAdic.isEmpty()) {
			usuario.atribuirRecursos(recursosAdic);
		}
		
		usuarioRepository.save(usuario);
	}
	
	/**
	 * @param command
	 * @return
	 */
	@Command(description = "Cadastra um novo usuário")
	public Usuario handle(CadastrarUsuarioCommand command) {
		Pessoa pessoa = new Pessoa(new PessoaId(command.getPessoaId()), command.getNome(), command.getCpf(),
				command.getOab(), command.getEmail(), command.getTelefone());
		UsuarioId idUsuario = usuarioRepository.nextId();
		Usuario principal = new Usuario(idUsuario, pessoa, command.getLogin());
		Grupo grupoUsuario = grupoRepository.findOne("usuario", TipoGrupo.CONFIGURACAO);
		Set<Grupo> grupos = new HashSet<>(0);

		grupos.add(grupoUsuario);
		principal.atribuirGrupos(grupos);		
		usuarioRepository.save(principal);
		
		return principal;
	}

}
