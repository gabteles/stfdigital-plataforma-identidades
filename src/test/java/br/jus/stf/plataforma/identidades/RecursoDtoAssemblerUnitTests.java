package br.jus.stf.plataforma.identidades;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;

import br.jus.stf.core.shared.identidades.RecursoId;
import br.jus.stf.plataforma.identidades.domain.model.Recurso;
import br.jus.stf.plataforma.identidades.domain.model.ResourceType;
import br.jus.stf.plataforma.identidades.interfaces.dto.RecursoDto;
import br.jus.stf.plataforma.identidades.interfaces.dto.RecursoDtoAssembler;

public class RecursoDtoAssemblerUnitTests {

    @InjectMocks
    private RecursoDtoAssembler assembler;

    @Before
    public void configuracao() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void criaRecursoDtoPreenchido() {
        Recurso entity =
                new Recurso(new RecursoId(1L), "Remessa", ResourceType.ACAO);
        RecursoDto dto = assembler.toDto(entity);

        Assert.assertNotNull("Dto não pode ser nulo.", dto);
        Assert.assertEquals("Identidade deve ser igual a 1.", entity.identity().toLong(), dto.getId());
        Assert.assertEquals("Nome deve ser igual a Remessa.", entity.nome(), dto.getNome());
        Assert.assertEquals("Tipo deve ser igual a Ação.", entity.tipo().name(), dto.getTipo());
    }

    @Test
    public void criaRecursoDtoNulo() {
        RecursoDto dto = assembler.toDto(null);

        Assert.assertNull("Dto deve ser nulo.", dto);
    }

}