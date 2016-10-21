package br.jus.stf.plataforma.identidades.domain.model.corporativo;

import org.junit.Assert;
import org.junit.Test;

import br.jus.stf.core.shared.identidade.PessoaId;

public class PessoaFactoryUnitTests {

    PessoaFactory factory = new PessoaFactory();

    @Test
    public void criaPessoaComCpfOabEmailTelefone() {
        Pessoa pessoa = factory.novaPessoa(new PessoaId(1L), "José da Silva", "28649904157", "212/DF",
                "jsilva@email.com", "(61)98547-6355");

        Assert.assertNotNull("Pessoa não deve se nula.", pessoa);
        Assert.assertEquals("Identidades devem ser iguais.", new PessoaId(1L), pessoa.identity());
        Assert.assertEquals("Nomes devem ser iguais.", "José da Silva", pessoa.nome());
        Assert.assertEquals("CPFs devem ser iguais.", "28649904157", pessoa.cpf());
        Assert.assertEquals("OABs devem ser iguais.", "212/DF", pessoa.oab());
        Assert.assertEquals("E-mails devem ser iguais.", "jsilva@email.com", pessoa.email());
        Assert.assertEquals("Telefones devem ser iguais.", "(61)98547-6355", pessoa.telefone());
    }

    @Test
    public void criaPessoaComCpfEmailTelefone() {
        Pessoa pessoa = factory.novaPessoa(new PessoaId(1L), "José da Silva", "28649904157", null, "jsilva@email.com",
                "(61)98547-6355");

        Assert.assertNotNull("Pessoa não deve se nula.", pessoa);
        Assert.assertEquals("Identidades devem ser iguais.", new PessoaId(1L), pessoa.identity());
        Assert.assertEquals("Nomes devem ser iguais.", "José da Silva", pessoa.nome());
        Assert.assertEquals("CPFs devem ser iguais.", "28649904157", pessoa.cpf());
        Assert.assertNull("OAB deve ser nula.", pessoa.oab());
        Assert.assertEquals("E-mails devem ser iguais.", "jsilva@email.com", pessoa.email());
        Assert.assertEquals("Telefones devem ser iguais.", "(61)98547-6355", pessoa.telefone());
    }

    @Test
    public void criaPessoaComCpf() {
        Pessoa pessoa = factory.novaPessoa(new PessoaId(1L), "José da Silva", "28649904157", null, null, null);

        Assert.assertNotNull("Pessoa não deve se nula.", pessoa);
        Assert.assertEquals("Identidades devem ser iguais.", new PessoaId(1L), pessoa.identity());
        Assert.assertEquals("Nomes devem ser iguais.", "José da Silva", pessoa.nome());
        Assert.assertEquals("CPFs devem ser iguais.", "28649904157", pessoa.cpf());
        Assert.assertNull("OAB deve ser nula.", pessoa.oab());
        Assert.assertNull("E-mail deve ser nulo.", pessoa.email());
        Assert.assertNull("Telefone deve ser nulo.", pessoa.telefone());
    }

    @Test
    public void criaPessoa() {
        Pessoa pessoa = factory.novaPessoa(new PessoaId(1L), "José da Silva", null, null, null, null);

        Assert.assertNotNull("Pessoa não deve se nula.", pessoa);
        Assert.assertEquals("Identidades devem ser iguais.", new PessoaId(1L), pessoa.identity());
        Assert.assertEquals("Nomes devem ser iguais.", "José da Silva", pessoa.nome());
        Assert.assertNull("CPF deve ser nulo.", pessoa.cpf());
        Assert.assertNull("OAB deve ser nula.", pessoa.oab());
        Assert.assertNull("E-mail deve ser nulo.", pessoa.email());
        Assert.assertNull("Telefone deve ser nulo.", pessoa.telefone());
    }

}