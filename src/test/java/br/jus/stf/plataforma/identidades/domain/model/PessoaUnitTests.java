package br.jus.stf.plataforma.identidades.domain.model;

import org.junit.Assert;
import org.junit.Test;

import br.jus.stf.core.shared.identidade.PessoaId;

public class PessoaUnitTests {

    @Test
    public void criaPessoaSemCpf() {
        Pessoa pessoa = new Pessoa(new PessoaId(1L), "José da Silva");

        Assert.assertNotNull("Pessoa não deve se nula.", pessoa);
        Assert.assertEquals("Identidades devem ser iguais.", new PessoaId(1L), pessoa.id());
        Assert.assertEquals("Nomes devem ser iguais.", "José da Silva", pessoa.nome());
        Assert.assertNull("CPF deve ser nulo.", pessoa.cpf());
        Assert.assertNull("OAB deve ser nula.", pessoa.oab());
        Assert.assertNull("E-mail deve ser nulo.", pessoa.email());
        Assert.assertNull("Telefone deve ser nulo.", pessoa.telefone());
    }

    @Test
    public void criaPessoaComCpfValido() {
        Pessoa pessoa = new Pessoa(new PessoaId(1L), "José da Silva", "28649904157", "123DF", "jose.silva@domain.com",
                "(61)98547-4588");

        Assert.assertNotNull("Pessoa não deve se nula.", pessoa);
        Assert.assertEquals("Identidades devem ser iguais.", new PessoaId(1L), pessoa.id());
        Assert.assertEquals("Nomes devem ser iguais.", "José da Silva", pessoa.nome());
        Assert.assertEquals("CPFs devem ser iguais.", "28649904157", pessoa.cpf());
        Assert.assertEquals("OABs devem ser iguais.", "123DF", pessoa.oab());
        Assert.assertEquals("E-mails devem ser iguais.", "jose.silva@domain.com", pessoa.email());
        Assert.assertEquals("Telefones devem ser iguais.", "(61)98547-4588", pessoa.telefone());
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