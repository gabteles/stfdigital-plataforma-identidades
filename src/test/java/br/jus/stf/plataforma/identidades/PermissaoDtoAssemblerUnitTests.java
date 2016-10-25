package br.jus.stf.plataforma.identidades;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;

import br.jus.stf.core.shared.identidades.TipoInformacaoId;
import br.jus.stf.plataforma.identidades.domain.model.Permissao;
import br.jus.stf.plataforma.identidades.domain.model.TipoInformacao;
import br.jus.stf.plataforma.identidades.domain.model.TipoPermissao;
import br.jus.stf.plataforma.identidades.interfaces.dto.PermissaoDto;
import br.jus.stf.plataforma.identidades.interfaces.dto.PermissaoDtoAssembler;

public class PermissaoDtoAssemblerUnitTests {

    @InjectMocks
    private PermissaoDtoAssembler assembler;

    @Before
    public void configuracao() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void criaPermissaoDtoPreenchido() {
        Permissao entity =
                new Permissao(1L, TipoPermissao.CRIAR, new TipoInformacao(new TipoInformacaoId(1L), "Pública"));
        PermissaoDto dto = assembler.toDto(entity);

        Assert.assertNotNull("Dto não pode ser nulo.", dto);
        Assert.assertEquals("Descrição deve ser igual a Criar_Pública.",
                entity.tipo().name() + "_" + entity.tipoInformacao().nome(), dto.getDescricao());
    }

    @Test
    public void criaPermissaoDtoNulo() {
        PermissaoDto dto = assembler.toDto(null);

        Assert.assertNull("Dto deve ser nulo.", dto);
    }

}