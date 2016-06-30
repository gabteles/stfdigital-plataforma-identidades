package br.jus.stf.plataforma.userauthentication;

import java.util.HashSet;
import java.util.Set;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import br.jus.stf.core.shared.userauthentication.GrupoId;
import br.jus.stf.core.shared.userauthentication.RecursoId;
import br.jus.stf.plataforma.userauthentication.domain.model.Grupo;
import br.jus.stf.plataforma.userauthentication.domain.model.Recurso;
import br.jus.stf.plataforma.userauthentication.domain.model.ResourceType;
import br.jus.stf.plataforma.userauthentication.domain.model.TipoGrupo;

public class PermissaoGrupoUnitTests {
	
	private Grupo grupo;
	private Recurso criarPeticaoEletronica;
	private Set<Recurso> recursos;
	
	@Before
	public void setUp() {
		criarPeticaoEletronica = new Recurso(new RecursoId(2L), "Criar petição eletrônica", ResourceType.ACAO);
		recursos = new HashSet<Recurso>(0);
		
		recursos.add(criarPeticaoEletronica);
		
		grupo = new Grupo(new GrupoId(1L), "STI", TipoGrupo.SETOR);
		grupo.atribuirRecursos(recursos);
	}
	
	@Test
	public void grupoPossuiAcessoNoRecurso() {
		Assert.assertTrue(grupo.possuiAcessoNo(criarPeticaoEletronica));
	}
	
	@Test
	public void grupoNaoPossuiAcessoNoRecurso() {
		Recurso distribuir = new Recurso(new RecursoId(1L), "Distribuir", ResourceType.ACAO);
		
		Assert.assertFalse(grupo.possuiAcessoNo(distribuir));
	}

}
