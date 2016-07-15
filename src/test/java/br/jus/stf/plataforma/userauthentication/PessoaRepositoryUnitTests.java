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
import br.jus.stf.plataforma.userauthentication.domain.model.identidade.Pessoa;
import br.jus.stf.plataforma.userauthentication.domain.model.identidade.PessoaRepository;

public class PessoaRepositoryUnitTests {
	
	@Mock
	private PessoaRepository pessoaRepository;
	
	private List<Pessoa> pessoas;
	
	@Before
	public void setUp() {
		MockitoAnnotations.initMocks(this);
		
		pessoas = new ArrayList<>(1);
		Pessoa pessoa = pessoa();
		
		pessoas.add(pessoa);
		
		
		Mockito.when(pessoaRepository.save(pessoa)).thenReturn(pessoa);
		Mockito.when(pessoaRepository.findOne(new PessoaId(1L))).thenReturn(pessoa);
		Mockito.when(pessoaRepository.findByCpf("28649904157")).thenReturn(pessoa);
		Mockito.when(pessoaRepository.findAll()).thenReturn(pessoas);
		Mockito.when(pessoaRepository.nextId()).thenReturn(new PessoaId(2L));
		Mockito.when(pessoaRepository.findByNomeContaining("joao")).thenReturn(pessoas);
	}

	@Test
	public void salvarPessoa() {
		Pessoa pessoa = pessoa();
		
		Assert.assertEquals("Pessoas devem ser iguais", pessoa, pessoaRepository.save(pessoa));
		Mockito.verify(pessoaRepository, Mockito.times(1)).save(pessoa);
	}
	
	@Test
	public void encontraPessoaPeloId() {
		Pessoa pessoa = pessoa();
		
		Assert.assertEquals(pessoa, pessoaRepository.findOne(new PessoaId(1L)));
		Mockito.verify(pessoaRepository, Mockito.times(1)).findOne(new PessoaId(1L));
	}
	
	@Test
	public void listaPessoasPorParteDoNome() {
		Assert.assertEquals(pessoas, pessoaRepository.findByNomeContaining("joao"));
		Mockito.verify(pessoaRepository, Mockito.times(1)).findByNomeContaining("joao");
	}
	
	@Test
	public void listaPessoas() {
		Assert.assertEquals(pessoas, pessoaRepository.findAll());
		Mockito.verify(pessoaRepository, Mockito.times(1)).findAll();
	}
	
	@Test
	public void proximoIdPessoa() {
		Assert.assertEquals(new PessoaId(2L), pessoaRepository.nextId());
		Mockito.verify(pessoaRepository, Mockito.times(1)).nextId();
	}
	
	@Test
	public void encontraPessoaPeloCpf() {
		Pessoa pessoa = pessoa();
		
		Assert.assertEquals(pessoa, pessoaRepository.findByCpf("28649904157"));
		Mockito.verify(pessoaRepository, Mockito.times(1)).findByCpf("28649904157");
	}
	
	private Pessoa pessoa() {
		return new Pessoa(new PessoaId(1L), "João Silva", "28649904157");
	}
	
}