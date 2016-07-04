package br.jus.stf.plataforma.userauthentication.infra.configuration;

import java.util.Collections;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Component;

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
	public Authentication authenticate(Authentication authentication) {
		String login = authentication.getName();
        Optional<Usuario> usuario = Optional.ofNullable(usuarioRepository.findOne(login));
        
        if (usuario.isPresent()) {
        	User user = new UserDetails(login, usuario.get().pessoa().id().toLong(), Collections.emptyList());
            return new UsernamePasswordAuthenticationToken(user, PROTECTED, user.getAuthorities());
        }
        
        return null;
	}

	@Override
	public boolean supports(Class<?> authentication) {
		return authentication.equals(UsernamePasswordAuthenticationToken.class);
	}

}
