package br.jus.stf.plataforma.identidades;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;

import br.jus.stf.core.shared.identidade.PessoaId;
import br.jus.stf.plataforma.identidades.domain.model.corporativo.Pessoa;
import br.jus.stf.plataforma.identidades.interfaces.dto.PessoaDto;
import br.jus.stf.plataforma.identidades.interfaces.dto.PessoaDtoAssembler;

public class PessoaDtoAssemblerUnitTests {

    @InjectMocks
    private PessoaDtoAssembler assembler;

    @Before
    public void configuracao() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void criaPessoaDtoPreenchido() {
        Pessoa entity = new Pessoa(new PessoaId(1L), "Maria");
        PessoaDto dto = assembler.toDto(entity);

        Assert.assertNotNull("Dto não pode ser nulo.", dto);
        Assert.assertEquals("Identidade deve ser igual a 1.", entity.identity().toLong(), dto.getId());
        Assert.assertEquals("Nome deve ser igual a Maria.", entity.nome(), dto.getNome());
    }

    @Test
    public void criaPessoaDtoNulo() {
        PessoaDto dto = assembler.toDto(null);

        Assert.assertNull("Dto deve ser nulo.", dto);
    }

}