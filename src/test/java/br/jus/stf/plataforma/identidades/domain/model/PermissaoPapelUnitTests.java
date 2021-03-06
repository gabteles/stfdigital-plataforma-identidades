package br.jus.stf.plataforma.identidades.domain.model;

import java.util.HashSet;
import java.util.Set;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import br.jus.stf.core.shared.identidades.PapelId;
import br.jus.stf.core.shared.identidades.RecursoId;
import br.jus.stf.plataforma.identidades.domain.model.Papel;
import br.jus.stf.plataforma.identidades.domain.model.Recurso;
import br.jus.stf.plataforma.identidades.domain.model.ResourceType;

public class PermissaoPapelUnitTests {

    private Papel papel;

    private Recurso criarPeticaoEletronica;

    private Set<Recurso> recursos;

    @Before
    public void setUp() {
        criarPeticaoEletronica = new Recurso(new RecursoId(2L), "Criar petição eletrônica", ResourceType.ACAO);
        recursos = new HashSet<Recurso>(0);

        recursos.add(criarPeticaoEletronica);

        papel = new Papel(new PapelId(1L), "Advogado");
        papel.atribuirRecursos(recursos);
    }

    @Test
    public void papelPossuiAcessoNoRecurso() {
        Assert.assertTrue(papel.possuiAcessoNo(criarPeticaoEletronica));
    }

    @Test
    public void papelNaoPossuiAcessoNoRecurso() {
        Recurso distribuir = new Recurso(new RecursoId(1L), "Distribuir", ResourceType.ACAO);

        Assert.assertFalse(papel.possuiAcessoNo(distribuir));
    }

    @Test
    public void papelNaoPossuiAcessoAposRetiradaDoRecurso() {
        Assert.assertTrue(papel.possuiAcessoNo(criarPeticaoEletronica));

        papel.removerRecursos(recursos);

        Assert.assertFalse(papel.possuiAcessoNo(criarPeticaoEletronica));
    }

}
