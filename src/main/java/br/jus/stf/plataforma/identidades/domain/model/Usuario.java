package br.jus.stf.plataforma.identidades.domain.model;

import java.util.Collections;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Optional;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.EmbeddedId;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

import org.apache.commons.lang3.Validate;

import br.jus.stf.core.framework.domaindrivendesign.EntitySupport;
import br.jus.stf.core.shared.identidades.GrupoId;
import br.jus.stf.core.shared.identidades.PapelId;
import br.jus.stf.core.shared.identidades.UsuarioId;

/**
 * @author Rafael Alencar
 * 
 * @since 1.0.0
 * @since 03.06.2016
 */
@javax.persistence.Entity
@Table(name = "USUARIO", schema = "IDENTIDADES", uniqueConstraints = @UniqueConstraint(columnNames = { "SIG_USUARIO" }))
public class Usuario extends EntitySupport<Usuario, UsuarioId> implements Principal {

    @EmbeddedId
    private UsuarioId id;

    @Embedded
    private Pessoa pessoa;

    @Column(name = "SIG_USUARIO", nullable = false)
    private String login;

    @ManyToOne
    @JoinColumn(name = "COD_SETOR_LOTACAO", referencedColumnName = "COD_SETOR")
    private Setor lotacao;

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinTable(name = "USUARIO_RECURSO", schema = "IDENTIDADES",
            joinColumns = @JoinColumn(name = "SEQ_USUARIO", nullable = false),
            inverseJoinColumns = @JoinColumn(name = "SEQ_RECURSO", nullable = false))
    private Set<Recurso> recursos = new HashSet<>(0);

    @OneToMany(cascade = CascadeType.ALL)
    @JoinTable(name = "PAPEL_USUARIO", schema = "IDENTIDADES",
            joinColumns = @JoinColumn(name = "SEQ_USUARIO", nullable = false),
            inverseJoinColumns = @JoinColumn(name = "SEQ_PAPEL", nullable = false))
    private Set<Papel> papeis = new HashSet<>(0);

    @OneToMany(cascade = CascadeType.ALL)
    @JoinTable(name = "GRUPO_USUARIO", schema = "IDENTIDADES",
            joinColumns = @JoinColumn(name = "SEQ_USUARIO", nullable = false),
            inverseJoinColumns = @JoinColumn(name = "SEQ_GRUPO", nullable = false))
    private Set<Grupo> grupos = new HashSet<>(0);

    Usuario() {
        // Deve ser usado apenas pelo Hibernate, que sempre usa o construtor default antes de popular uma nova
        // instância.
    }

    /**
     * @param id
     * @param pessoa
     * @param login
     */
    public Usuario(UsuarioId id, Pessoa pessoa, String login) {
        Validate.notNull(id, "Identificador requerido.");
        Validate.notNull(pessoa, "Pessoa requerida.");
        Validate.notBlank(login, "Login requerido.");

        this.id = id;
        this.pessoa = pessoa;
        this.login = login;
    }

    /**
     * @param id
     * @param pessoa
     * @param login
     * @param lotacao
     */
    public Usuario(UsuarioId id, Pessoa pessoa, String login, Setor lotacao) {
        this(id, pessoa, login);

        Validate.notNull(lotacao, "Lotação requerida.");

        this.lotacao = lotacao;
    }

    /**
     * @return
     */
    public Pessoa pessoa() {
        return pessoa;
    }

    /**
     * @return
     */
    public String login() {
        return login;
    }

    /**
     * @return
     */
    public Set<Papel> papeis() {
        return Collections.unmodifiableSet(papeis);
    }

    /**
     * @param papeis
     */
    public void atribuirPapeis(Set<Papel> papeis) {
        Validate.notEmpty(papeis, "Papeis requeridos.");

        this.papeis.addAll(papeis);
    }

    /**
     * @param papeis
     */
    public void removerPapeis(Set<PapelId> papeis) {
        Validate.notEmpty(papeis, "Papeis requeridos.");

        Iterator<Papel> papelIterator = this.papeis.iterator();

        while (papelIterator.hasNext()) {
            Papel papel = papelIterator.next();

            if (papeis.contains(papel.identity())) {
                papelIterator.remove();
            }
        }
    }

    /**
     * @return
     */
    public Set<Grupo> grupos() {
        return Collections.unmodifiableSet(grupos);
    }

    /**
     * @param grupos
     */
    public void atribuirGrupos(Set<Grupo> grupos) {
        Validate.notEmpty(grupos, "Grupos requeridos.");

        this.grupos.addAll(grupos);
    }

    /**
     * @param grupos
     */
    public void removerGrupos(Set<GrupoId> grupos) {
        Validate.notEmpty(grupos, "Grupos requeridos.");

        Iterator<Grupo> grupoIterator = this.grupos.iterator();

        while (grupoIterator.hasNext()) {
            Grupo grupo = grupoIterator.next();

            if (grupos.contains(grupo.identity())) {
                grupoIterator.remove();
            }
        }
    }

    /**
     * @return
     */
    public Setor lotacao() {
        return lotacao;
    }

    @Override
    public Set<Recurso> recursos() {
        Set<Recurso> recursosCompletos = new HashSet<>(0);

        Optional.ofNullable(papeis).ifPresent(p -> p.forEach(papel -> recursosCompletos.addAll(papel.recursos())));
        Optional.ofNullable(grupos).ifPresent(g -> g.forEach(grupo -> recursosCompletos.addAll(grupo.recursos())));
        Optional.ofNullable(recursos).ifPresent(recursosCompletos::addAll);

        return Collections.unmodifiableSet(recursosCompletos);
    }

    @Override
    public void atribuirRecursos(Set<Recurso> recursos) {
        Validate.notEmpty(recursos, "Recursos requeridos.");

        this.recursos.addAll(recursos);
    }

    @Override
    public void removerRecursos(Set<Recurso> recursos) {
        Validate.notEmpty(recursos, "Recursos requeridos.");

        this.recursos.removeAll(recursos);
    }

    @Override
    public UsuarioId identity() {
        return id;
    }

}
