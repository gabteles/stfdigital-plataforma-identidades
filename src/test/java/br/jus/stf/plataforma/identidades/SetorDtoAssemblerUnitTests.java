package br.jus.stf.plataforma.identidades;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;

import br.jus.stf.plataforma.identidades.domain.model.Setor;
import br.jus.stf.plataforma.identidades.interfaces.dto.SetorDto;
import br.jus.stf.plataforma.identidades.interfaces.dto.SetorDtoAssembler;

public class SetorDtoAssemblerUnitTests {

    @InjectMocks
    private SetorDtoAssembler assembler;

    @Before
    public void configuracao() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void criaSetorDtoPreenchido() {
        Setor entity =
                new Setor(1L, "Secretaria de TI", "STI");
        SetorDto dto = assembler.toDto(entity);

        Assert.assertNotNull("Dto não pode ser nulo.", dto);
        Assert.assertEquals("Identidade deve ser igual a 1.", entity.codigo(), dto.getCodigo());
        Assert.assertEquals("Nome deve ser igual a Secretaria de TI.", entity.nome(), dto.getNome());
        Assert.assertEquals("Sigla deve ser igual a STI.", entity.sigla(), dto.getSigla());
    }

    @Test
    public void criaSetorDtoNulo() {
        SetorDto dto = assembler.toDto(null);

        Assert.assertNull("Dto deve ser nulo.", dto);
    }

}