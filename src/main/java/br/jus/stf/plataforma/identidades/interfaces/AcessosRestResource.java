package br.jus.stf.plataforma.identidades.interfaces;

import java.util.Arrays;
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
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.wordnik.swagger.annotations.ApiOperation;

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

    private static final String USUARIO_INVALIDA_PATTERN = "Usuário Inválido: %S";

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
     * @param login Login do usuário.
     * @return Conjunto de permissões associadas ao usuário do login informado.
     */
    @ApiOperation("Conjunto de permissões associadas ao usuário do login informado.")
    @RequestMapping(value = "/usuarios/{usuarioId}/permissoes", method = RequestMethod.GET)
    public Set<PermissaoDto> permissoes(@PathVariable("usuarioId") Long usuarioId) {
        // TODO: Verificar como as permissões serão utilizadas para finalizar implementação
        Set<Permissao> permissoes = new HashSet<>(0);

        return permissoes.stream()
                .map(permissaoDtoAssembler::toDto)
                .collect(Collectors.toSet());
    }

    /**
     * @param usuarioId Identificador do usuário.
     * @return Lista de recursos associados ao usuário informado.
     */
    @ApiOperation(value = "Lista todos os recursos associados ao usuário informado.")
    @RequestMapping(value = "/usuarios/{usuarioId}/recursos", method = RequestMethod.GET)
    public Set<RecursoDto> recursos(@PathVariable("usuarioId") Long usuarioId) {
        Set<Recurso> recursos = Optional.ofNullable(usuarioRepository.findOne(new UsuarioId(usuarioId)))
                .map(usuario -> usuario.recursos())
                .orElse(Collections.emptySet());

        return recursos.stream()
                .map(recursoDtoAssembler::toDto)
                .collect(Collectors.toSet());
    }

    /**
     * @param nome Nome do recurso.
     * @param tipo Tipo do recurso. {@link br.jus.stf.plataforma.identidades.domain.model.ResourceType ResourceType}
     * @return Lista de papeis associados ao recurso cujo nome e tipo foram informados.
     */
    @ApiOperation(value = "Lista todos os papeis associados ao recurso cujo nome e tipo foram informados.")
    @RequestMapping(value = "/recursos/papeis", method = RequestMethod.GET)
    public List<PapelDto> papeis(@RequestParam("nome") String nome, @RequestParam("tipo") String tipo) {
        List<Papel> papeis = Optional.ofNullable(recursoRepository.findOne(nome, ResourceType.valueOf(tipo)))
                .map(recurso -> papelRepository.findPapelByRecurso(recurso.identity()))
                .orElse(Collections.emptyList());

        return papeis.stream()
                .map(papelDtoAssembler::toDto)
                .collect(Collectors.toList());
    }

    /**
     * @param usuarioId Identificador do usuário.
     * @return Lista de papeis associados ao usuário informado.
     */
    @ApiOperation(value = "Lista todos os papeis associados ao usuário informado.")
    @RequestMapping(value = "/usuarios/{usuarioId}/papeis", method = RequestMethod.GET)
    public Set<PapelDto> papeis(@PathVariable("usuarioId") Long usuarioId) {
        Set<Papel> papeis = Optional.ofNullable(usuarioRepository.findOne(new UsuarioId(usuarioId)))
                .map(usuario -> {
                    usuario.papeis().size(); // inicializa o proxy
                    return usuario.papeis();
                })
                .orElse(Collections.emptySet());

        return papeis.stream()
                .map(papelDtoAssembler::toDto)
                .sorted((p1, p2) -> p1.getNome().compareTo(p2.getNome()))
                .collect(Collectors.toCollection(LinkedHashSet::new));
    }

    /**
     * @return Todos os papeis cadastrados.
     */
    @ApiOperation(value = "Lista todos os papeis cadastrados.")
    @RequestMapping(value = "/papeis", method = RequestMethod.GET)
    public Set<PapelDto> todosPapeis() {
        return papelRepository.findAll().stream()
                .map(papelDtoAssembler::toDto)
                .sorted((p1, p2) -> p1.getNome().compareTo(p2.getNome()))
                .collect(Collectors.toCollection(LinkedHashSet::new));
    }

    /**
     * @return Todos os grupos cadastrados.
     */
    @ApiOperation(value = "Lista todos os grupos cadastrados.")
    @RequestMapping(value = "/grupos", method = RequestMethod.GET)
    public Set<GrupoDto> todosGrupos() {
        return grupoRepository.findAll().stream()
                .map(grupoDtoAssembler::toDto)
                .sorted((p1, p2) -> p1.getNome().compareTo(p2.getNome()))
                .collect(Collectors.toCollection(LinkedHashSet::new));
    }

    /**
     * @param usuarioId Identificador do usuário.
     * @return Lista de grupos associados ao usuário informado.
     */
    @ApiOperation(value = "Lista todos os grupos associados ao usuário informado.")
    @RequestMapping(value = "/usuarios/{usuarioId}/grupos", method = RequestMethod.GET)
    public Set<GrupoDto> grupos(@PathVariable("usuarioId") Long usuarioId) {
        Set<Grupo> grupos = Optional.ofNullable(usuarioRepository.findOne(new UsuarioId(usuarioId)))
                .map(usuario -> {
                    usuario.grupos().size(); // inicializa o proxy
                    return usuario.grupos();
                })
                .orElse(Collections.emptySet());

        return grupos.stream()
                .map(grupoDtoAssembler::toDto)
                .sorted((g1, g2) -> g1.getNome().compareTo(g2.getNome()))
                .collect(Collectors.toCollection(LinkedHashSet::new));
    }

    /**
     * @param usuarioId Identificador do usuário.
     * @param command Permissões de grupos, papeis e recursos para um usuário.
     * @param binding Resultado das validações.
     */
    @ApiOperation("Configura as permissões de grupos, papeis e recursos de um usuário.")
    @RequestMapping(value = "/usuarios/{usuarioId}/configuracoes-permissao", method = RequestMethod.POST)
    @ResponseStatus(HttpStatus.OK)
    public void configurarPermissoesUsuario(@PathVariable("usuarioId") Long usuarioId,
            @RequestBody @Valid ConfigurarPermissoesUsuarioCommand command,
            BindingResult binding) {
        isValid(usuarioId, command.getIdUsuario(), binding);
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
    @RequestMapping(value = "/usuarios/{usuarioId}", method = RequestMethod.GET)
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
    @RequestMapping(value = "/usuarios/id", method = RequestMethod.GET)
    public Long recuperarId(@RequestParam("login") String login) {
        Usuario usuario = usuarioRepository.findOne(login);

        return usuario.identity().toLong();
    }

    /**
     * Cadastra um novo usuário
     * 
     * @param command Dados do usuário.
     * @param binding Resultado das validações.
     * @return Dto do usuário criado.
     */
    @ApiOperation("Cadastra um novo usuário.")
    @RequestMapping(value = "/usuarios", method = RequestMethod.POST)
    public UsuarioDto cadastrarUsuario(@RequestBody @Valid CadastrarUsuarioCommand command, BindingResult binding) {
        isValid(binding);

        Usuario usuario = acessosApplicationService.handle(command);

        return usuarioDtoAssembler.toDto(usuario);
    }

    private static void isValid(Long usuarioIdPath, Long usuarioIdCommand, BindingResult binding) {
        isValid(binding);

        if (!usuarioIdPath.equals(usuarioIdCommand)) {
            throw new IllegalArgumentException(message(
                    Arrays.asList(
                            new ObjectError("Usuário", "Identificadores do comando incompatíveis."))));
        }
    }

    private static void isValid(BindingResult result) {
        if (result.hasErrors()) {
            throw new IllegalArgumentException(message(result.getAllErrors()));
        }
    }

    private static String message(List<ObjectError> errors) {
        return String.format(USUARIO_INVALIDA_PATTERN, errors);
    }
}
