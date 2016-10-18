package br.jus.stf.plataforma.identidades;

import java.util.ArrayList;
import java.util.List;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import br.jus.stf.core.shared.identidade.PessoaId;
import br.jus.stf.plataforma.identidades.domain.model.corporativo.Orgao;
import br.jus.stf.plataforma.identidades.domain.model.corporativo.OrgaoRepository;

public class OrgaoRepositoryUnitTests {
	
	@Mock
	private OrgaoRepository orgaoRepository;
	
	private List<Orgao> orgaos;
	
	@Before
	public void setUp() {
		MockitoAnnotations.initMocks(this);
		
		orgaos = new ArrayList<>(1);
		Orgao orgao = orgao();
		
		orgaos.add(orgao);
		
		
		Mockito.when(orgaoRepository.save(orgao)).thenReturn(orgao);
		Mockito.when(orgaoRepository.findOne(new PessoaId(1L))).thenReturn(orgao);
		Mockito.when(orgaoRepository.findAll()).thenReturn(orgaos);
	}

	@Test
	public void salvarOrgao() {
		Orgao orgao = orgao();
		
		Assert.assertEquals("Órgãos devem ser iguais", orgao, orgaoRepository.save(orgao));
		Mockito.verify(orgaoRepository, Mockito.times(1)).save(orgao);
	}
	
	@Test
	public void encontraOrgaoPeloId() {
		Orgao orgao = orgao();
		
		Assert.assertEquals(orgao, orgaoRepository.findOne(new PessoaId(1L)));
		Mockito.verify(orgaoRepository, Mockito.times(1)).findOne(new PessoaId(1L));
	}
	
	@Test
	public void listaOrgaos() {
		Assert.assertEquals(orgaos, orgaoRepository.findAll());
		Mockito.verify(orgaoRepository, Mockito.times(1)).findAll();
	}
	
	private Orgao orgao() {
		PessoaId pessoaId = new PessoaId(1L);
		String nome = "AGU";
		
		return new Orgao(pessoaId, nome);
	}
	
}
