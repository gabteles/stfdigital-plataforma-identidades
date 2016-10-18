package br.jus.stf.plataforma.identidades;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

import org.junit.Assert;
import org.junit.Test;

import br.jus.stf.core.shared.identidade.PessoaId;
import br.jus.stf.plataforma.identidades.domain.model.corporativo.Associado;
import br.jus.stf.plataforma.identidades.domain.model.corporativo.Orgao;
import br.jus.stf.plataforma.identidades.domain.model.corporativo.Pessoa;
import br.jus.stf.plataforma.identidades.domain.model.corporativo.TipoAssociado;

public class OrgaoUnitTests {
	
	@Test
	public void criaOrgaoSemAssociadosValido() {
		PessoaId id = new PessoaId(1L);
		String nome = "AGU";
		Orgao orgao = new Orgao(id, nome);
		
		Assert.assertNotNull("Órgão não pode ser nulo.", orgao);
		Assert.assertEquals("Identidades devem ser iguais.", id, orgao.identity());
		Assert.assertEquals("Nomes devem ser iguais.", nome, orgao.nome());
		Assert.assertTrue("Órgão não possui associados.", orgao.associados().isEmpty());
	}
	
	@Test
	public void criaOrgaoComAssociadosValido() {
		PessoaId id = new PessoaId(1L);
		String nome = "AGU";
		Set<Associado> associados = new HashSet<>(Arrays.asList(
				new Associado(new Pessoa(new PessoaId(1L), "José Santos"), TipoAssociado.ASSOCIADO, "Estagiário")));
		Orgao orgao = new Orgao(id, nome, associados);
		
		Assert.assertNotNull("Órgão não pode ser nulo.", orgao);
		Assert.assertEquals("Identidades devem ser iguais.", id, orgao.identity());
		Assert.assertEquals("Nomes devem ser iguais.", nome, orgao.nome());
		Assert.assertEquals("Órgão deve ter um associado.", associados, orgao.associados());
	}
	
	@Test(expected = NullPointerException.class)
	public void tentaCriarOrgaoComIdNulo() {
		new Orgao(null, "AGU");
	}
	
	@Test(expected = NullPointerException.class)
	public void tentaCriarOrgaoComNomeNulo() {
		new Orgao(new PessoaId(1L), null);
	}
	
	@Test
	public void adicionaAssociadoAoOrgao() {
		Orgao orgao = orgao();
		
		Assert.assertTrue("Órgão não deve ter associados.", orgao.associados().isEmpty());
		
		Associado associado = new Associado(new Pessoa(new PessoaId(1L), "José Santos"), TipoAssociado.ASSOCIADO, "Estagiário");
		Set<Associado> associados = new HashSet<>(Arrays.asList(associado));
		
		orgao.adicionarAssociado(associado);
		
		Assert.assertEquals("Órgão deve ter um associado.", associados, orgao.associados());
	}
	
	@Test
	public void verificaPessoaQueNaoRepresentaOrgao() {
		Orgao orgao = orgao();
		Pessoa pessoa = new Pessoa(new PessoaId(1L), "José Santos");
		Associado associado = new Associado(pessoa, TipoAssociado.ASSOCIADO, "Estagiário");
		
		orgao.adicionarAssociado(associado);
		
		Assert.assertFalse("Pessoa não deve ser representante do Órgão.", orgao.isRepresentadoPor(pessoa));
	}
	
	@Test
	public void verificaPessoaQueRepresentaOrgao() {
		Orgao orgao = orgao();
		Pessoa pessoa = new Pessoa(new PessoaId(1L), "José Matos");
		Associado associado = new Associado(pessoa, TipoAssociado.REPRESENTANTE, "Advogado");
		
		orgao.adicionarAssociado(associado);
		
		Assert.assertTrue("Pessoa deve ser representa do Órgão.", orgao.isRepresentadoPor(pessoa));
	}
	
	private Orgao orgao() {
		PessoaId id = new PessoaId(1L);
		String nome = "AGU";
		
		return new Orgao(id, nome);
	}

}