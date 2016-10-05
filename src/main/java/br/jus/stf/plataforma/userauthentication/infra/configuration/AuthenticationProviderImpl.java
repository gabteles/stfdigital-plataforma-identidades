package br.jus.stf.plataforma.userauthentication.infra.configuration;

import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import br.jus.stf.plataforma.userauthentication.domain.model.Papel;
import br.jus.stf.plataforma.userauthentication.domain.model.Usuario;
import br.jus.stf.plataforma.userauthentication.domain.model.UsuarioRepository;

/**
 * @author Rodrigo Barreiros
 * 
 * @since 1.0.0
 * @since 30.06.2016
 */
@Component
public class AuthenticationProviderImpl implements AuthenticationProvider {
	
	private static final String PROTECTED = "[PROTECTED]";
	
	@Autowired
	private UserDetailsExtractor userDetailsExtractor;
	
	@Autowired
	private UsuarioRepository usuarioRepository;

	@Override
	@Transactional
	public Authentication authenticate(Authentication authentication) {
        Optional<Usuario> usuario = Optional.ofNullable(usuarioRepository.findOne(authentication.getName()));
        
        return usuario.map(this::extractUserDetails).orElse(null);
	}

	@Override
	public boolean supports(Class<?> authentication) {
		return authentication.equals(UsernamePasswordAuthenticationToken.class);
	}
	
	private Authentication extractUserDetails(Usuario usuario) {
		List<GrantedAuthority> authorities = authorities(usuario.papeis());
		User user = new User(usuario.login(), PROTECTED, authorities);
		UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(user, PROTECTED, authorities);
		authentication.setDetails(userDetailsExtractor.extract(usuario, authorities));
		return authentication;
	}
	
	private static List<GrantedAuthority> authorities(Set<Papel> papeis) {
		return papeis.stream().map(papel -> new SimpleGrantedAuthority(papel.nome())).collect(Collectors.toList());
	}

}
