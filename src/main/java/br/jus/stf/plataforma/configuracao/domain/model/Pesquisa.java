package br.jus.stf.plataforma.configuracao.domain.model;

import javax.persistence.Column;
import javax.persistence.Convert;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Lob;
import javax.persistence.Table;

import org.apache.commons.lang3.Validate;

import br.jus.stf.core.framework.domaindrivendesign.EntitySupport;
import br.jus.stf.core.framework.persistence.FlagSNConverter;
import br.jus.stf.core.framework.security.utils.AuthenticationUtils;
import br.jus.stf.core.shared.userauthentication.UsuarioId;

/**
 * @author Rafael Alencar
 * 
 * @since 1.0.0
 * @since 07.06.2016
 */
@Entity
@Table(name = "PESQUISA", schema = "CONFIGURACAO")
public class Pesquisa extends EntitySupport<Pesquisa, PesquisaId> {

	@EmbeddedId
	private PesquisaId id;
	
	@Column(name = "DSC_PESQUISA", nullable = false)
	private String descricao;
	
	@Column(name = "DSC_CONTEXTO", nullable = false)
	private String contexto;
	
	@Lob
	@Column(name = "BIN_CRITERIO", nullable = false)
	private String criterio;
	
	@Column(name = "FLG_EXECUCAO_AUTOMATICA", nullable = false)
	@Convert(converter = FlagSNConverter.class)
	private Boolean execucaoAutomatica;
	
	@Column(name = "SEQ_USUARIO", nullable = false)
	private UsuarioId usuario;

	Pesquisa() {
		// Deve ser usado apenas pelo Hibernate, que sempre usa o construtor default antes de popular uma nova instância.
	}
	
	/**
	 * @param id
	 * @param nome
	 */
	public Pesquisa(PesquisaId id, String descricao, String contexto, String criterio, Boolean execucaoAutomatica){
		Validate.notNull(id, "Identificador requerido.");
		Validate.notBlank(descricao, "Descrição requerida.");
		Validate.notBlank(contexto, "Contexto requerido.");
		Validate.notBlank(criterio, "Critério requerido.");
		Validate.notNull(execucaoAutomatica, "Execução automática requerida.");
		
		this.id = id;
		this.descricao = descricao;
		this.contexto = contexto;
		this.criterio = criterio;
		this.execucaoAutomatica = execucaoAutomatica;
		this.usuario = recuperarUsuarioId();
	}

	/**
	 * @return
	 */
	public String descricao() {
		return descricao;
	}
	
	/**
	 * @return
	 */
	public String contexto() {
		return contexto;
	}
	
	/**
	 * @return
	 */
	public String criterio() {
		return criterio;
	}
	
	public boolean isExecucaoAutomatica() {
		return Boolean.TRUE.equals(execucaoAutomatica);
	}
	
	/**
	 * @return
	 */
	public UsuarioId usuario() {
		return usuario;
	}

	@Override
	public PesquisaId identity() {
		return id;
	}
	
	/**
	 * @return
	 */
	public static UsuarioId recuperarUsuarioId() {
		return AuthenticationUtils.getUserDetail("usuarioId", Long.class)
				.map(UsuarioId::new)
				.orElseThrow(() -> new IllegalArgumentException("usuarioId não pode ser recuperado do contexto de segurança!"));
	}
	
}