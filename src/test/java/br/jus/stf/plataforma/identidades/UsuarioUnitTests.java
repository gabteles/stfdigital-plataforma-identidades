package br.jus.stf.plataforma.identidades;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

import org.junit.Assert;
import org.junit.Test;

import br.jus.stf.core.shared.identidade.PessoaId;
import br.jus.stf.core.shared.identidades.GrupoId;
import br.jus.stf.core.shared.identidades.PapelId;
import br.jus.stf.core.shared.identidades.RecursoId;
import br.jus.stf.core.shared.identidades.UsuarioId;
import br.jus.stf.plataforma.identidades.domain.model.Grupo;
import br.jus.stf.plataforma.identidades.domain.model.Papel;
import br.jus.stf.plataforma.identidades.domain.model.Pessoa;
import br.jus.stf.plataforma.identidades.domain.model.Recurso;
import br.jus.stf.plataforma.identidades.domain.model.ResourceType;
import br.jus.stf.plataforma.identidades.domain.model.Setor;
import br.jus.stf.plataforma.identidades.domain.model.TipoGrupo;
import br.jus.stf.plataforma.identidades.domain.model.Usuario;

public class UsuarioUnitTests {

    @Test
    public void criaUsuarioSemLotacaoValido() {
        UsuarioId id = new UsuarioId(1L);
        Pessoa pessoa = new Pessoa(new PessoaId(1L), "João Silva");
        String login = "joao.silva";
        Usuario usuario = new Usuario(id, pessoa, login);

        Assert.assertNotNull("Usuário não pode ser nulo.", usuario);
        Assert.assertEquals("Identidades devem ser iguais.", id, usuario.identity());
        Assert.assertEquals("Pessoas devem ser iguais.", pessoa, usuario.pessoa());
        Assert.assertEquals("Logins devem ser iguais.", login, usuario.login());
    }

    @Test
    public void criaUsuarioComLotacaoValido() {
        UsuarioId id = new UsuarioId(1L);
        Pessoa pessoa = new Pessoa(new PessoaId(1L), "João Silva");
        String login = "joao.silva";
        Setor lotacao = new Setor(1L, "Setor A", "SA");
        Usuario usuario = new Usuario(id, pessoa, login, lotacao);

        Assert.assertNotNull("Usuário não pode ser nulo.", usuario);
        Assert.assertEquals("Identidades devem ser iguais.", id, usuario.identity());
        Assert.assertEquals("Pessoas devem ser iguais.", pessoa, usuario.pessoa());
        Assert.assertEquals("Logins devem ser iguais.", login, usuario.login());
        Assert.assertEquals("Lotações devem ser iguais.", lotacao, usuario.lotacao());
    }

    @Test(expected = NullPointerException.class)
    public void tentaCriarUsuarioComIdNulo() {
        Pessoa pessoa = new Pessoa(new PessoaId(1L), "João Silva");
        String login = "joao.silva";

        new Usuario(null, pessoa, login);
    }

    @Test(expected = NullPointerException.class)
    public void tentaCriarUsuarioComPessoaNulo() {
        UsuarioId id = new UsuarioId(1L);
        String login = "joao.silva";

        new Usuario(id, null, login);
    }

    @Test(expected = NullPointerException.class)
    public void tentaCriarUsuarioComLoginNulo() {
        UsuarioId id = new UsuarioId(1L);
        Pessoa pessoa = new Pessoa(new PessoaId(1L), "João Silva");
        String login = "joao.silva";

        new Usuario(id, pessoa, login, null);
    }

    @Test(expected = NullPointerException.class)
    public void tentaCriarUsuarioComLotacaoNulo() {
        UsuarioId id = new UsuarioId(1L);
        Pessoa pessoa = new Pessoa(new PessoaId(1L), "João Silva");
        String login = "joao.silva";

        new Usuario(id, pessoa, login, null);
    }

    @Test
    public void atribuiPapeisAoUsuario() {
        Usuario usuario = usuario();
        Set<Papel> papeis = new HashSet<>(0);

        Assert.assertTrue("Usuário não deve ter papeis.", usuario.papeis().isEmpty());

        papeis.add(new Papel(new PapelId(1L), "User"));
        usuario.atribuirPapeis(papeis);

        Assert.assertEquals("Usuário deve ter um papel.", papeis, usuario.papeis());
    }

    @Test
    public void atribuiGruposAoUsuario() {
        Usuario usuario = usuario();
        Set<Grupo> grupos = new HashSet<>(0);

        Assert.assertTrue("Usuário não deve ter grupos.", usuario.grupos().isEmpty());

        grupos.add(new Grupo(new GrupoId(1L), "User", TipoGrupo.CONFIGURACAO));
        usuario.atribuirGrupos(grupos);

        Assert.assertEquals("Usuário deve ter um grupo.", grupos, usuario.grupos());
    }

    @Test
    public void atribuiRecursosAoUsuario() {
        Usuario usuario = usuario();
        Set<Recurso> recursos = new HashSet<>(0);

        Assert.assertTrue("Usuário não deve ter recursos.", usuario.recursos().isEmpty());

        recursos.add(new Recurso(new RecursoId(1L), "Nova petição", ResourceType.ACAO));
        usuario.atribuirRecursos(recursos);

        Assert.assertEquals("Usuário deve ter um recurso.", recursos, usuario.recursos());
    }

    @Test
    public void verificaRecursosCompletosDoUsuario() {
        Usuario usuario = usuario();
        Set<Papel> papeis = new HashSet<>(0);
        Papel papel = new Papel(new PapelId(1L), "User");
        Recurso minhasTarefas = new Recurso(new RecursoId(1L), "Minhas tarefas", ResourceType.DASHLET);

        papel.atribuirRecursos(new HashSet<Recurso>(Arrays.asList(minhasTarefas)));
        papeis.add(papel);
        usuario.atribuirPapeis(papeis);

        Set<Grupo> grupos = new HashSet<>(0);
        Grupo grupo = new Grupo(new GrupoId(1L), "User", TipoGrupo.CONFIGURACAO);
        Recurso minhasPeticoes = new Recurso(new RecursoId(2L), "Minhas peticoes", ResourceType.DASHLET);

        grupo.atribuirRecursos(new HashSet<Recurso>(Arrays.asList(minhasPeticoes)));
        grupos.add(grupo);
        usuario.atribuirGrupos(grupos);

        Set<Recurso> recursos = new HashSet<>(0);

        recursos.add(new Recurso(new RecursoId(3L), "Nova petição", ResourceType.ACAO));
        usuario.atribuirRecursos(recursos);

        Set<Recurso> recursosEsperados = new HashSet<>(3);

        recursosEsperados.addAll(papel.recursos());
        recursosEsperados.addAll(grupo.recursos());
        recursosEsperados.addAll(recursos);

        Assert.assertEquals(
                "Usuário deve ter como recursos os associados aos seus papeis e seus grupos, bem como os diretamente associados a ele.",
                recursosEsperados, usuario.recursos());
    }

    @Test
    public void removePapelDoUsuario() {
        Usuario usuario = usuario();
        Set<Papel> papeis = new HashSet<>(0);

        papeis.add(new Papel(new PapelId(1L), "User"));
        usuario.atribuirPapeis(papeis);
        usuario.removerPapeis(new HashSet<PapelId>(Arrays.asList(new PapelId(1L))));

        Assert.assertTrue("Usuário deve ter papeis.", usuario.papeis().isEmpty());
    }

    @Test
    public void removeGrupoDoUsuario() {
        Usuario usuario = usuario();
        Set<Grupo> grupos = new HashSet<>(0);

        grupos.add(new Grupo(new GrupoId(1L), "User", TipoGrupo.CONFIGURACAO));
        usuario.atribuirGrupos(grupos);
        usuario.removerGrupos(new HashSet<GrupoId>(Arrays.asList(new GrupoId(1L))));

        Assert.assertTrue("Usuário deve ter grupos.", usuario.grupos().isEmpty());
    }

    @Test
    public void removeRecursosDoUsuario() {
        Usuario usuario = usuario();
        Set<Recurso> recursos = new HashSet<>(0);
        Recurso recurso = new Recurso(new RecursoId(1L), "Minhas petições", ResourceType.ACAO);

        recursos.add(recurso);
        usuario.atribuirRecursos(recursos);
        usuario.removerRecursos(new HashSet<Recurso>(Arrays.asList(recurso)));

        Assert.assertTrue("Usuário deve ter recursos.", usuario.recursos().isEmpty());
    }

    private Usuario usuario() {
        UsuarioId id = new UsuarioId(1L);
        Pessoa pessoa = new Pessoa(new PessoaId(1L), "João Silva");
        String login = "joao.silva";

        return new Usuario(id, pessoa, login);
    }

}