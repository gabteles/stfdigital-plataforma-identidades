package br.jus.stf.plataforma.identidades.domain.model.corporativo;

import org.junit.Assert;
import org.junit.Test;

import br.jus.stf.core.shared.identidade.PessoaId;
import br.jus.stf.plataforma.identidades.domain.model.corporativo.Associado;
import br.jus.stf.plataforma.identidades.domain.model.corporativo.Pessoa;
import br.jus.stf.plataforma.identidades.domain.model.corporativo.TipoAssociado;

public class AssociadoUnitTests {
	
	@Test
	public void criaAssociadoSemCargoValido() {
		Pessoa pessoa = new Pessoa(new PessoaId(1L), "José da Silva");
		Associado associado = new Associado(pessoa, TipoAssociado.GESTOR, null);
		
		
		Assert.assertNotNull("Associado não deve ser nulo.", associado);
		Assert.assertEquals("Pessoas devem ser iguais.", pessoa, associado.pessoa());
		Assert.assertEquals("Tipo de associados deve ser iguais.", TipoAssociado.GESTOR, associado.tipo());
		Assert.assertNull("Cargos/Funções devem ser nulo.", associado.cargoFuncao());
	}
	
	@Test
	public void criaAssociadoComCargoValido() {
		Pessoa pessoa = new Pessoa(new PessoaId(1L), "José da Silva");
		String cargoFuncao = "Procurador";
		Associado associado = new Associado(pessoa, TipoAssociado.REPRESENTANTE, cargoFuncao);
		
		
		Assert.assertNotNull("Associado não deve ser nulo.", associado);
		Assert.assertEquals("Pessoas devem ser iguais.", pessoa, associado.pessoa());
		Assert.assertEquals("Tipo de associados deve ser iguais.", TipoAssociado.REPRESENTANTE, associado.tipo());
		Assert.assertEquals("Cargos/Funções devem ser iguais.", cargoFuncao, associado.cargoFuncao());
	}
	
	@Test
	public void verificaPessoaAssociadaQueNaoERepresentante() {
		Pessoa pessoa = new Pessoa(new PessoaId(1L), "José da Silva");
		Associado associado = new Associado(pessoa, TipoAssociado.ASSOCIADO, null);
		
		
		Assert.assertFalse("Pessoa não é associado representante.", associado.isRepresentante(pessoa));
	}
	
	@Test
	public void verificaPessoaAssociadaQueERepresentante() {
		Pessoa pessoa = new Pessoa(new PessoaId(1L), "José da Silva");
		String cargoFuncao = "Advogado";
		Associado associado = new Associado(pessoa, TipoAssociado.REPRESENTANTE, cargoFuncao);
		
		
		Assert.assertTrue("Pessoa é associado representante.", associado.isRepresentante(pessoa));
	}
	
	@Test(expected=NullPointerException.class)
	public void tentaCriarAssociadoComPessoaNula() {
		new Associado(null, TipoAssociado.ASSOCIADO, "Estagiário");
	}
	
	@Test(expected=NullPointerException.class)
	public void tentaCriarAssociadoComTipoNulo() {
		new Associado(new Pessoa(new PessoaId(1L), "José da Silva"), null, "Estagiário");
	}
	
}