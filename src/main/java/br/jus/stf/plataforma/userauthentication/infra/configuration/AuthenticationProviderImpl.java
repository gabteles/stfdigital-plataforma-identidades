package br.jus.stf.plataforma.userauthentication.infra.configuration;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

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
	private UsuarioRepository usuarioRepository;

	@Override
	@Transactional
	public Authentication authenticate(Authentication authentication) {
        Optional<Usuario> usuario = Optional.ofNullable(usuarioRepository.findOne(authentication.getName()));
        
        return usuario.map(this::extractUserDetails).orElse(null);
	}

	private Authentication extractUserDetails(Usuario usuario) {
		User user = new UserDetails(usuario.login(), usuario.pessoa().id().toLong(), usuario.papeis(), usuario.recursos());
		return new UsernamePasswordAuthenticationToken(user, PROTECTED, user.getAuthorities());
	}

	@Override
	public boolean supports(Class<?> authentication) {
		return authentication.equals(UsernamePasswordAuthenticationToken.class);
	}

}
