package br.jus.stf.plataforma.userauthentication.interfaces;

import java.util.Collections;
import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.wordnik.swagger.annotations.ApiOperation;
import com.wordnik.swagger.annotations.ApiResponse;
import com.wordnik.swagger.annotations.ApiResponses;

import br.jus.stf.core.shared.userauthentication.UsuarioId;
import br.jus.stf.plataforma.userauthentication.application.AcessosApplicationService;
import br.jus.stf.plataforma.userauthentication.application.commands.CadastrarUsuarioCommand;
import br.jus.stf.plataforma.userauthentication.application.commands.ConfigurarPermissoesUsuarioCommand;
import br.jus.stf.plataforma.userauthentication.domain.model.Grupo;
import br.jus.stf.plataforma.userauthentication.domain.model.GrupoRepository;
import br.jus.stf.plataforma.userauthentication.domain.model.Papel;
import br.jus.stf.plataforma.userauthentication.domain.model.PapelRepository;
import br.jus.stf.plataforma.userauthentication.domain.model.Permissao;
import br.jus.stf.plataforma.userauthentication.domain.model.Recurso;
import br.jus.stf.plataforma.userauthentication.domain.model.RecursoRepository;
import br.jus.stf.plataforma.userauthentication.domain.model.ResourceType;
import br.jus.stf.plataforma.userauthentication.domain.model.Usuario;
import br.jus.stf.plataforma.userauthentication.domain.model.UsuarioRepository;
import br.jus.stf.plataforma.userauthentication.interfaces.dto.GrupoDto;
import br.jus.stf.plataforma.userauthentication.interfaces.dto.GrupoDtoAssembler;
import br.jus.stf.plataforma.userauthentication.interfaces.dto.PapelDto;
import br.jus.stf.plataforma.userauthentication.interfaces.dto.PapelDtoAssembler;
import br.jus.stf.plataforma.userauthentication.interfaces.dto.PermissaoDto;
import br.jus.stf.plataforma.userauthentication.interfaces.dto.PermissaoDtoAssembler;
import br.jus.stf.plataforma.userauthentication.interfaces.dto.RecursoDto;
import br.jus.stf.plataforma.userauthentication.interfaces.dto.RecursoDtoAssembler;
import br.jus.stf.plataforma.userauthentication.interfaces.dto.UsuarioDto;
import br.jus.stf.plataforma.userauthentication.interfaces.dto.UsuarioDtoAssembler;

/**
 * @author Lucas.Rodrigues
 *
 */
@RestController
@RequestMapping("/api/acessos")
public class AcessosRestResource {
	
	@Autowired
	private AcessosApplicationService acessosApplicationService;
	
	@Autowired
	private RecursoDtoAssembler recursoDtoAssembler;
	
	@Autowired
	private PermissaoDtoAssembler permissaoDtoAssembler;
	
	@Autowired
	private UsuarioDtoAssembler usuarioDtoAssembler;
	
	@Autowired
	private GrupoDtoAssembler grupoDtoAssembler;
	
	@Autowired
	private PapelDtoAssembler papelDtoAssembler;
	
	@Autowired
	private UsuarioRepository usuarioRepository;
	
	@Autowired
	private RecursoRepository recursoRepository;
	
	@Autowired
	private GrupoRepository grupoRepository;
	
	@Autowired
	private PapelRepository papelRepository;
	
	@RequestMapping("/usuarios/permissoes")
	public Set<PermissaoDto> permissoes(@RequestParam("login") String login) {
		// TODO: Verificar como as permissões serão utilizadas para finalizar implementação
		Set<Permissao> permissoes = new HashSet<>(0);
		
		return permissoes.stream().map(permissao -> permissaoDtoAssembler.toDto(permissao)).collect(Collectors.toSet());
	}
	
	@RequestMapping("/usuarios/recursos")
	public Set<RecursoDto> recursos(@RequestParam("login") String login) {
		Set<Recurso> recursos = Optional.ofNullable(usuarioRepository.findOne(login)).map(usuario -> usuario.recursos())
				.orElse(Collections.emptySet());
		
		return recursos.stream().map(permissao -> recursoDtoAssembler.toDto(permissao)).collect(Collectors.toSet());
	}
	
	@RequestMapping("/recursos/papeis")
	public List<PapelDto> papeis(@RequestParam("nome") String nome, @RequestParam("tipo") String tipo) {
		List<Papel> papeis = Optional.ofNullable(recursoRepository.findOne(nome, ResourceType.valueOf(tipo)))
				.map(recurso -> papelRepository.findPapelByRecurso(recurso.identity())).orElse(Collections.emptyList());
		
		return papeis.stream().map(papel -> papelDtoAssembler.toDto(papel)).collect(Collectors.toList());
	}

	@RequestMapping("/usuarios/papeis")
	public Set<PapelDto> papeis(@RequestParam("login") String login) {
		Set<Papel> papeis = Optional.ofNullable(usuarioRepository.findOne(login)).map(usuario -> {
			usuario.papeis().size(); // inicializa o proxy
			return usuario.papeis();
		}).orElse(Collections.emptySet());
		
		return papeis.stream().map(papel -> papelDtoAssembler.toDto(papel))
				.sorted((p1, p2) -> p1.getNome().compareTo(p2.getNome()))
				.collect(Collectors.toCollection(LinkedHashSet::new));
	}
	
	@RequestMapping("/papeis")
	public Set<PapelDto> todosPapeis() {
		return papelRepository.findAll().stream().map(papel -> papelDtoAssembler.toDto(papel))
				.sorted((p1, p2) -> p1.getNome().compareTo(p2.getNome()))
				.collect(Collectors.toCollection(LinkedHashSet::new));
	}
	
	@RequestMapping("/grupos")
	public Set<GrupoDto> todosGrupos() {
		return grupoRepository.findAll().stream().map(grupo -> grupoDtoAssembler.toDto(grupo))
				.sorted((p1, p2) -> p1.getNome().compareTo(p2.getNome()))
				.collect(Collectors.toCollection(LinkedHashSet::new));
	}
	
	@RequestMapping("/usuarios/grupos")
	public Set<GrupoDto> grupos(@RequestParam("login") String login) {
		Set<Grupo> grupos = Optional.ofNullable(usuarioRepository.findOne(login)).map(usuario -> {
			usuario.grupos().size(); // inicializa o proxy
			return usuario.grupos();
		}).orElse(Collections.emptySet());
		
		return grupos.stream().map(grupo -> grupoDtoAssembler.toDto(grupo))
				.sorted((g1, g2) -> g1.getNome().compareTo(g2.getNome()))
				.collect(Collectors.toCollection(LinkedHashSet::new));
	}
	
	@ApiOperation("Configura as permissões a grupos e papéis de um usuário.")
	@RequestMapping(value = "/permissoes/configuracao", method = RequestMethod.POST)
	@ResponseStatus(HttpStatus.OK)
	public void configurarPermissoesUsuario(@RequestBody ConfigurarPermissoesUsuarioCommand command ){
		acessosApplicationService.handle(command);
	}
	
	/**
	 * Recupera as informações do usuário logado.
	 *
	 * @return Informações do usuário.
	 */
	@ApiOperation("Recupera as informações do usuário logado.")
	@RequestMapping(value="/usuario", method = RequestMethod.GET)
	public UsuarioDto recuperarUsuario() {
        // TODO: Ver como será recuperado o usuário logado...
//		String login = SecurityContextUtil.getUser().getUsername();
		String login = "usuario-teste";
		
		return usuarioDtoAssembler.toDto(usuarioRepository.findOne(login));
	}
	
	/**
	 * Recupera as informações do usuário informado.
	 * 
	 * @param usuarioId Id do usuário.
	 * @return Informações do usuário.
	 */
	@ApiOperation("Recupera as informações do usuário informado.")
	@RequestMapping(value="/usuarios/:usuarioId", method = RequestMethod.GET)
	public UsuarioDto recuperarUsuario(@PathVariable("usuarioId") Long usuarioId) {
		return usuarioDtoAssembler.toDto(usuarioRepository.findOne(new UsuarioId(usuarioId)));
	}
	
	/**
	 * Recupera as informações do usuário informado.
	 * 
	 * @param login do usuário.
	 * @return Informações do usuário.
	 */
	@ApiOperation("Recupera as informações do usuário informado.")
	@RequestMapping(value="/usuarios/:login.+", method = RequestMethod.GET)
	public UsuarioDto recuperarUsuario(@PathVariable("login") String login) {
		return usuarioDtoAssembler.toDto(usuarioRepository.findOne(login));
	}
	
	/**
	 * Recupera o ID de determinado usuário
	 * 
	 * @param String login Login do usuário.
	 * @return ID do usuário
	 */
	@ApiOperation("Recupera o ID de um usuário.")
	@RequestMapping("/usuarios/id")
	public Long recuperarId(@RequestParam("login") String login) {
		Usuario usuario = usuarioRepository.findOne(login);
		
		return usuario.identity().toLong();
	}
	
	/**
	 * Cadastra um novo usuário
	 * 
	 * @param CadastrarUsuarioCommand command
	 * @param BindingResult binding
	 * @return Dto do usuário criado
	 */
	@ApiOperation("Cadastra um novo usuário")
	@ApiResponses(value = {@ApiResponse(code = 400, message = "Usuário Inválido")})
	@RequestMapping(value="/usuarios", method = RequestMethod.POST)
	public UsuarioDto cadastrarUsuario(@RequestBody @Valid CadastrarUsuarioCommand command, BindingResult binding) {
		if (binding.hasErrors()) {
			throw new IllegalArgumentException("Usuário inválido: " + binding.getAllErrors());
		}
		
		Usuario usuario = acessosApplicationService.handle(command);
		
		return usuarioDtoAssembler.toDto(usuario);
	}
}
