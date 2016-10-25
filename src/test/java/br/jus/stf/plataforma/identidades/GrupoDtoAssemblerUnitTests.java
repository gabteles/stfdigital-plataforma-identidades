package br.jus.stf.plataforma.identidades;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;

import br.jus.stf.core.shared.identidades.GrupoId;
import br.jus.stf.plataforma.identidades.domain.model.Grupo;
import br.jus.stf.plataforma.identidades.domain.model.TipoGrupo;
import br.jus.stf.plataforma.identidades.interfaces.dto.GrupoDto;
import br.jus.stf.plataforma.identidades.interfaces.dto.GrupoDtoAssembler;

public class GrupoDtoAssemblerUnitTests {

    @InjectMocks
    private GrupoDtoAssembler assembler;

    @Before
    public void configuracao() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void criaGrupoDtoPreenchido() {
        Grupo entity = new Grupo(new GrupoId(1L), "Servidor", TipoGrupo.CONFIGURACAO);
        GrupoDto dto = assembler.toDto(entity);

        Assert.assertNotNull("Dto não pode ser nulo.", dto);
        Assert.assertEquals("Identidade deve ser igual a 1.", entity.identity().toLong(), dto.getId());
        Assert.assertEquals("Nome deve ser igual a Servidor.", entity.nome(), dto.getNome());
    }

    @Test
    public void criaGrupoDtoNulo() {
        GrupoDto dto = assembler.toDto(null);

        Assert.assertNull("Dto deve ser nulo.", dto);
    }

}