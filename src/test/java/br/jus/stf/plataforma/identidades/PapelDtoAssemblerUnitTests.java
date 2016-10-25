package br.jus.stf.plataforma.identidades;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;

import br.jus.stf.core.shared.identidades.GrupoId;
import br.jus.stf.core.shared.identidades.PapelId;
import br.jus.stf.plataforma.identidades.domain.model.Grupo;
import br.jus.stf.plataforma.identidades.domain.model.Papel;
import br.jus.stf.plataforma.identidades.domain.model.TipoGrupo;
import br.jus.stf.plataforma.identidades.interfaces.dto.PapelDto;
import br.jus.stf.plataforma.identidades.interfaces.dto.PapelDtoAssembler;

public class PapelDtoAssemblerUnitTests {

    @InjectMocks
    private PapelDtoAssembler assembler;

    @Before
    public void configuracao() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void criaPapelDtoPreenchido() {
        Papel entity =
                new Papel(new PapelId(1L), "Servidor", new Grupo(new GrupoId(1L), "Gabinete", TipoGrupo.CONFIGURACAO));
        PapelDto dto = assembler.toDto(entity);

        Assert.assertNotNull("Dto não pode ser nulo.", dto);
        Assert.assertEquals("Identidade deve ser igual a 1.", entity.identity().toLong(), dto.getId());
        Assert.assertEquals("Nome deve ser igual a Servidor.", entity.nome(), dto.getNome());
        Assert.assertEquals("Setor deve ser igual a Gabinete.", entity.grupo().nome(), dto.getSetor());
    }

    @Test
    public void criaPapelDtoNulo() {
        PapelDto dto = assembler.toDto(null);

        Assert.assertNull("Dto deve ser nulo.", dto);
    }

}