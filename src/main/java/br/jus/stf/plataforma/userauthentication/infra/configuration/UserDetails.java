package br.jus.stf.plataforma.userauthentication.infra.configuration;

import java.util.Collection;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;

/**
 * @author Rodrigo Barreiros
 * 
 * @since 1.0.0
 * @since 01.07.2016
 */
public class UserDetails extends User {

	private static final long serialVersionUID = 1L;
	
	private Long pessoaId;

	public UserDetails(String username, Long pessoaId, Collection<? extends GrantedAuthority> authorities) {
		super(username, "[PROTECTED]", true, true, true, true, authorities);
		this.pessoaId = pessoaId;
	}
	
	public Long getPessoaId() {
		return pessoaId;
	}
	
}
