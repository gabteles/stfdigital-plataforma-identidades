package br.jus.stf.plataforma.userauthentication;

import org.junit.Assert;
import org.junit.Test;

import br.jus.stf.core.shared.identidade.PessoaId;
import br.jus.stf.plataforma.userauthentication.domain.model.identidade.Pessoa;

public class PessoaUnitTests {
	
	@Test
	public void criaPessoaValida() {
		Pessoa pessoa = new Pessoa(new PessoaId(1L), "José da Silva");
		
		Assert.assertNotNull("Pessoa não deve se nula.", pessoa);
		Assert.assertEquals("Identidades devem ser iguais.", pessoa.identity(), new PessoaId(1L));
		Assert.assertEquals("Nomes devem ser iguais.", pessoa.nome(), "José da Silva");
	}
	
	@Test
	public void criaPessoaComCpfValido() {
		Pessoa pessoa = new Pessoa(new PessoaId(1L), "José da Silva", "28649904157");
		
		Assert.assertNotNull("Pessoa não deve se nula.", pessoa);
		Assert.assertEquals("Identidades devem ser iguais.", pessoa.identity(), new PessoaId(1L));
		Assert.assertEquals("Nomes devem ser iguais.", pessoa.nome(), "José da Silva");
		Assert.assertEquals("CPFs devem ser iguais.", pessoa.cpf(), "28649904157");
	}
	
	@Test
	public void criaPessoaSemOabEValidaQueNaoDeveSerAdvogado() {
		Pessoa pessoa = new Pessoa(new PessoaId(1L), "José da Silva");
		
		Assert.assertFalse("Sem OAB não deve ser advogado.", pessoa.ehAdvogado());
	}
	
	@Test
	public void criaPessoaComOabEValidaQueDeveSerAdvogado() {
		Pessoa pessoa = new Pessoa(new PessoaId(1L), "José da Silva", "28649904157", "0142/DF", "jose.silva@myemail.com", "(61)98547-8966");
		
		Assert.assertTrue("Com OAB deve ser advogado.", pessoa.ehAdvogado());
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void tentaCriaPessoaComCpfVazio() {
		new Pessoa(new PessoaId(1L), "José da Silva", "");
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void tentaCriaPessoaComCpfInvalido() {
		new Pessoa(new PessoaId(1L), "José da Silva", "28649904150");
	}
	
	@Test(expected = NullPointerException.class)
	public void tentaCriarPessoaComIdNulo() {
		new Pessoa(null, "José da Silva");
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void tentaCriaPessoaComNomeVazio() {
		new Pessoa(new PessoaId(1L), "");
	}
	
	@Test(expected = NullPointerException.class)
	public void tentaCriaPessoaComNomeNulo() {
		new Pessoa(new PessoaId(1L), null);
	}

}