package br.jus.stf.plataforma.identidades;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import br.jus.stf.core.shared.identidade.PessoaId;
import br.jus.stf.core.shared.identidades.UsuarioId;
import br.jus.stf.plataforma.identidades.domain.model.Pessoa;
import br.jus.stf.plataforma.identidades.domain.model.Usuario;
import br.jus.stf.plataforma.identidades.interfaces.dto.SetorDtoAssembler;
import br.jus.stf.plataforma.identidades.interfaces.dto.UsuarioDto;
import br.jus.stf.plataforma.identidades.interfaces.dto.UsuarioDtoAssembler;

public class UsuarioDtoAssemblerUnitTests {

    @Mock
    private SetorDtoAssembler setorDtoAssembler;

    @InjectMocks
    private UsuarioDtoAssembler assembler;

    @Before
    public void configuracao() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void criaUsuarioDtoPreenchido() {
        Usuario entity =
                new Usuario(new UsuarioId(1L), new Pessoa(new PessoaId(1L), "Maria Silva"), "msilva");
        UsuarioDto dto = assembler.toDto(entity);

        Assert.assertNotNull("Dto não pode ser nulo.", dto);
        Assert.assertEquals("Identidade deve ser igual a 1.", entity.identity().toLong(), dto.getId());
        Assert.assertEquals("Nome deve ser igual a Servidor.", entity.pessoa().nome(), dto.getNome());
        Assert.assertEquals("Pessoa deve ser igual a 1.", entity.pessoa().id().toLong(), dto.getPessoaId());
        Assert.assertEquals("Login deve ser igual a msilva.", entity.login(), dto.getLogin());
        Assert.assertNull("Lotação deve ser nula.", dto.getSetorLotacao());

        Mockito.verify(setorDtoAssembler, Mockito.times(1)).toDto(null);
    }

    @Test
    public void criaUsuarioDtoNulo() {
        UsuarioDto dto = assembler.toDto(null);

        Assert.assertNull("Dto deve ser nulo.", dto);
    }

}