package br.jus.stf.plataforma.userauthentication;

import java.util.ArrayList;
import java.util.List;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import br.jus.stf.core.shared.identidade.PessoaId;
import br.jus.stf.core.shared.userauthentication.RecursoId;
import br.jus.stf.core.shared.userauthentication.UsuarioId;
import br.jus.stf.plataforma.userauthentication.domain.model.Pessoa;
import br.jus.stf.plataforma.userauthentication.domain.model.Recurso;
import br.jus.stf.plataforma.userauthentication.domain.model.ResourceType;
import br.jus.stf.plataforma.userauthentication.domain.model.Usuario;
import br.jus.stf.plataforma.userauthentication.domain.model.UsuarioRepository;

public class UsuarioRepositoryUnitTests {
	
	@Mock
	private UsuarioRepository usuarioRepository;
	
	private List<Recurso> recursos;
	private List<Usuario> usuarios;
	
	@Before
	public void setUp() {
		MockitoAnnotations.initMocks(this);
		
		recursos = new ArrayList<>(1);
		
		recursos.add(new Recurso(new RecursoId(1L), "Nova petição", ResourceType.ACAO));
		
		usuarios = new ArrayList<>(1);
		Usuario usuario = usuario();
		
		usuarios.add(usuario);
		
		
		Mockito.when(usuarioRepository.save(usuario)).thenReturn(usuario);
		Mockito.when(usuarioRepository.findOne(new UsuarioId(1L))).thenReturn(usuario);
		Mockito.when(usuarioRepository.findOne("joao.silva")).thenReturn(usuario);
		Mockito.when(usuarioRepository.findAll()).thenReturn(usuarios);
		Mockito.when(usuarioRepository.nextId()).thenReturn(new UsuarioId(2L));
		Mockito.when(usuarioRepository.findRecursoByUsuario("joao.silva")).thenReturn(recursos);
	}

	@Test
	public void salvarUsuario() {
		Usuario usuario = usuario();
		
		Assert.assertEquals("Usuários devem ser iguais", usuario, usuarioRepository.save(usuario));
		Mockito.verify(usuarioRepository, Mockito.times(1)).save(usuario);
	}
	
	@Test
	public void encontraUsuarioPeloId() {
		Usuario usuario = usuario();
		
		Assert.assertEquals(usuario, usuarioRepository.findOne(new UsuarioId(1L)));
		Mockito.verify(usuarioRepository, Mockito.times(1)).findOne(new UsuarioId(1L));
	}
	
	@Test
	public void encontraUsuarioPeloLogin() {
		Usuario usuario = usuario();
		
		Assert.assertEquals(usuario, usuarioRepository.findOne("joao.silva"));
		Mockito.verify(usuarioRepository, Mockito.times(1)).findOne("joao.silva");
	}
	
	@Test
	public void listaUsuarios() {
		Assert.assertEquals(usuarios, usuarioRepository.findAll());
		Mockito.verify(usuarioRepository, Mockito.times(1)).findAll();
	}
	
	@Test
	public void proximoIdUsuario() {
		Assert.assertEquals(new UsuarioId(2L), usuarioRepository.nextId());
		Mockito.verify(usuarioRepository, Mockito.times(1)).nextId();
	}
	
	@Test
	public void encontraRecursosDoUsuario() {
		Assert.assertEquals(recursos, usuarioRepository.findRecursoByUsuario("joao.silva"));
		Mockito.verify(usuarioRepository, Mockito.times(1)).findRecursoByUsuario("joao.silva");
	}
	
	private Usuario usuario() {
		UsuarioId id = new UsuarioId(1L);
		Pessoa pessoa = new Pessoa(new PessoaId(1L), "João Silva");
		String login = "joao.silva";
		
		return new Usuario(id, pessoa, login);
	}
	
}
