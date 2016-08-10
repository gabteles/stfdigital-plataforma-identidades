package br.jus.stf.plataforma.userauthentication.infra.configuration;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;

import br.jus.stf.plataforma.userauthentication.domain.model.Papel;
import br.jus.stf.plataforma.userauthentication.domain.model.Recurso;

/**
 * @author Rodrigo Barreiros
 * 
 * @since 1.0.0
 * @since 01.07.2016
 */
public class UserDetails extends User {

	private static final long serialVersionUID = 1L;
	
	private Long pessoaId;
	private Set<Recurso> recursos;

	public UserDetails(String username, Long pessoaId, Set<Papel> papeis, Set<Recurso> recursos) {
		super(username, "[PROTECTED]", true, true, true, true, authorities(papeis));
		
		this.pessoaId = pessoaId;
		this.recursos = recursos;
	}

	private static List<GrantedAuthority> authorities(Set<Papel> papeis) {
		return papeis.stream().map(papel -> new SimpleGrantedAuthority(papel.nome())).collect(Collectors.toList());
	}
	
	public Long getPessoaId() {
		return pessoaId;
	}
	
	public Set<Recurso> getRecursos() {
		return recursos;
	}
	
}
