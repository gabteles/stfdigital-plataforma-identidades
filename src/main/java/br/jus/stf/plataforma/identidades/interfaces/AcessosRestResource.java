package br.jus.stf.plataforma.identidades.interfaces;

import java.util.Collections;
import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.provider.OAuth2Authentication;
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

import br.jus.stf.core.shared.identidades.UsuarioId;
import br.jus.stf.plataforma.identidades.application.AcessosApplicationService;
import br.jus.stf.plataforma.identidades.application.commands.CadastrarUsuarioCommand;
import br.jus.stf.plataforma.identidades.application.commands.ConfigurarPermissoesUsuarioCommand;
import br.jus.stf.plataforma.identidades.domain.model.Grupo;
import br.jus.stf.plataforma.identidades.domain.model.GrupoRepository;
import br.jus.stf.plataforma.identidades.domain.model.Papel;
import br.jus.stf.plataforma.identidades.domain.model.PapelRepository;
import br.jus.stf.plataforma.identidades.domain.model.Permissao;
import br.jus.stf.plataforma.identidades.domain.model.Recurso;
import br.jus.stf.plataforma.identidades.domain.model.RecursoRepository;
import br.jus.stf.plataforma.identidades.domain.model.ResourceType;
import br.jus.stf.plataforma.identidades.domain.model.Usuario;
import br.jus.stf.plataforma.identidades.domain.model.UsuarioRepository;
import br.jus.stf.plataforma.identidades.interfaces.dto.GrupoDto;
import br.jus.stf.plataforma.identidades.interfaces.dto.GrupoDtoAssembler;
import br.jus.stf.plataforma.identidades.interfaces.dto.PapelDto;
import br.jus.stf.plataforma.identidades.interfaces.dto.PapelDtoAssembler;
import br.jus.stf.plataforma.identidades.interfaces.dto.PermissaoDto;
import br.jus.stf.plataforma.identidades.interfaces.dto.PermissaoDtoAssembler;
import br.jus.stf.plataforma.identidades.interfaces.dto.RecursoDto;
import br.jus.stf.plataforma.identidades.interfaces.dto.RecursoDtoAssembler;
import br.jus.stf.plataforma.identidades.interfaces.dto.UsuarioDto;
import br.jus.stf.plataforma.identidades.interfaces.dto.UsuarioDtoAssembler;

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

    /**
     * @param login
     * @return
     */
    @RequestMapping("/usuarios/permissoes")
    public Set<PermissaoDto> permissoes(@RequestParam("login") String login) {
        // TODO: Verificar como as permissões serão utilizadas para finalizar implementação
        Set<Permissao> permissoes = new HashSet<>(0);

        return permissoes.stream().map(permissaoDtoAssembler::toDto).collect(Collectors.toSet());
    }

    /**
     * @param login
     * @return
     */
    @RequestMapping("/usuarios/recursos")
    public Set<RecursoDto> recursos(@RequestParam("login") String login) {
        Set<Recurso> recursos = Optional.ofNullable(usuarioRepository.findOne(login)).map(usuario -> usuario.recursos())
                .orElse(Collections.emptySet());

        return recursos.stream().map(recursoDtoAssembler::toDto).collect(Collectors.toSet());
    }

    /**
     * @param nome
     * @param tipo
     * @return
     */
    @RequestMapping("/recursos/papeis")
    public List<PapelDto> papeis(@RequestParam("nome") String nome, @RequestParam("tipo") String tipo) {
        List<Papel> papeis = Optional.ofNullable(recursoRepository.findOne(nome, ResourceType.valueOf(tipo)))
                .map(recurso -> papelRepository.findPapelByRecurso(recurso.identity())).orElse(Collections.emptyList());

        return papeis.stream().map(papelDtoAssembler::toDto).collect(Collectors.toList());
    }

    /**
     * @param login
     * @return
     */
    @RequestMapping("/usuarios/papeis")
    public Set<PapelDto> papeis(@RequestParam("login") String login) {
        Set<Papel> papeis = Optional.ofNullable(usuarioRepository.findOne(login)).map(usuario -> {
            usuario.papeis().size(); // inicializa o proxy
            return usuario.papeis();
        }).orElse(Collections.emptySet());

        return papeis.stream().map(papelDtoAssembler::toDto)
                .sorted((p1, p2) -> p1.getNome().compareTo(p2.getNome()))
                .collect(Collectors.toCollection(LinkedHashSet::new));
    }

    /**
     * @return
     */
    @RequestMapping("/papeis")
    public Set<PapelDto> todosPapeis() {
        return papelRepository.findAll().stream().map(papelDtoAssembler::toDto)
                .sorted((p1, p2) -> p1.getNome().compareTo(p2.getNome()))
                .collect(Collectors.toCollection(LinkedHashSet::new));
    }

    /**
     * @return
     */
    @RequestMapping("/grupos")
    public Set<GrupoDto> todosGrupos() {
        return grupoRepository.findAll().stream().map(grupoDtoAssembler::toDto)
                .sorted((p1, p2) -> p1.getNome().compareTo(p2.getNome()))
                .collect(Collectors.toCollection(LinkedHashSet::new));
    }

    /**
     * @param login
     * @return
     */
    @RequestMapping("/usuarios/grupos")
    public Set<GrupoDto> grupos(@RequestParam("login") String login) {
        Set<Grupo> grupos = Optional.ofNullable(usuarioRepository.findOne(login)).map(usuario -> {
            usuario.grupos().size(); // inicializa o proxy
            return usuario.grupos();
        }).orElse(Collections.emptySet());

        return grupos.stream().map(grupoDtoAssembler::toDto)
                .sorted((g1, g2) -> g1.getNome().compareTo(g2.getNome()))
                .collect(Collectors.toCollection(LinkedHashSet::new));
    }

    /**
     * @param command
     */
    @ApiOperation("Configura as permissões a grupos, papéis e recursos de um usuário.")
    @RequestMapping(value = "/permissoes/configuracao", method = RequestMethod.POST)
    @ResponseStatus(HttpStatus.OK)
    public void configurarPermissoesUsuario(@RequestBody @Valid ConfigurarPermissoesUsuarioCommand command) {
        acessosApplicationService.handle(command);
    }

    /**
     * Recupera as informações do usuário logado.
     *
     * @return Informações do usuário.
     */
    @SuppressWarnings("unchecked")
    @ApiOperation("Recupera as informações do usuário logado.")
    @RequestMapping(value = "/usuario", method = RequestMethod.GET)
    public UsuarioDto recuperarUsuario() {
        OAuth2Authentication authentication =
                (OAuth2Authentication) SecurityContextHolder.getContext().getAuthentication();
        Map<String, Object> principal = (Map<String, Object>) authentication.getUserAuthentication().getDetails();
        String login = principal.get("login").toString();

        return usuarioDtoAssembler.toDto(usuarioRepository.findOne(login));
    }

    /**
     * Recupera as informações do usuário informado.
     * 
     * @param usuarioId Id do usuário.
     * @return Informações do usuário.
     */
    @ApiOperation("Recupera as informações do usuário informado.")
    @RequestMapping(value = "/usuarios/{usuarioId:[\\d]+}", method = RequestMethod.GET)
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
    @RequestMapping(value = "/usuarios", method = RequestMethod.GET)
    public UsuarioDto recuperarUsuario(@RequestParam("login") String login) {
        return usuarioDtoAssembler.toDto(usuarioRepository.findOne(login));
    }

    /**
     * Recupera o ID de determinado usuário
     * 
     * @param login Login do usuário.
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
     * @param command
     * @param binding
     * @return Dto do usuário criado
     */
    @ApiOperation("Cadastra um novo usuário")
    @ApiResponses(value = { @ApiResponse(code = 400, message = "Usuário Inválido") })
    @RequestMapping(value = "/usuarios", method = RequestMethod.POST)
    public UsuarioDto cadastrarUsuario(@RequestBody @Valid CadastrarUsuarioCommand command, BindingResult binding) {
        if (binding.hasErrors()) {
            throw new IllegalArgumentException("Usuário inválido: " + binding.getAllErrors());
        }

        Usuario usuario = acessosApplicationService.handle(command);

        return usuarioDtoAssembler.toDto(usuario);
    }
}
