package br.jus.stf.plataforma.identidades.domain.model.corporativo;

import org.junit.Assert;
import org.junit.Test;

import br.jus.stf.core.shared.identidade.PessoaId;
import br.jus.stf.plataforma.identidades.domain.model.corporativo.Pessoa;

public class PessoaUnitTests {

    @Test
    public void criaPessoaValida() {
        Pessoa pessoa = new Pessoa(new PessoaId(1L), "José da Silva");

        Assert.assertNotNull("Pessoa não deve se nula.", pessoa);
        Assert.assertEquals("Identidades devem ser iguais.", new PessoaId(1L), pessoa.identity());
        Assert.assertEquals("Nomes devem ser iguais.", "José da Silva", pessoa.nome());
    }

    @Test
    public void criaPessoaComCpfValido() {
        Pessoa pessoa = new Pessoa(new PessoaId(1L), "José da Silva", "28649904157");

        Assert.assertNotNull("Pessoa não deve se nula.", pessoa);
        Assert.assertEquals("Identidades devem ser iguais.", new PessoaId(1L), pessoa.identity());
        Assert.assertEquals("Nomes devem ser iguais.", "José da Silva", pessoa.nome());
        Assert.assertEquals("CPFs devem ser iguais.", "28649904157", pessoa.cpf());
    }

    @Test
    public void criaPessoaSemOabEValidaQueNaoDeveSerAdvogado() {
        Pessoa pessoa = new Pessoa(new PessoaId(1L), "José da Silva");

        Assert.assertFalse("Sem OAB não deve ser advogado.", pessoa.ehAdvogado());
    }

    @Test
    public void criaPessoaComOabEValidaQueDeveSerAdvogado() {
        Pessoa pessoa = new Pessoa(new PessoaId(1L), "José da Silva", "28649904157", "0142/DF",
                "jose.silva@myemail.com", "(61)98547-8966");

        Assert.assertEquals("OAB deve ser igual a 0142/DF.", "0142/DF", pessoa.oab());
        Assert.assertTrue("Com OAB deve ser advogado.", pessoa.ehAdvogado());
        Assert.assertEquals("E-mails devem ser iguais.", "jose.silva@myemail.com", pessoa.email());
        Assert.assertEquals("Telefones devem ser iguais.", "(61)98547-8966", pessoa.telefone());
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