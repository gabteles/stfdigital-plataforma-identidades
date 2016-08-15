package br.jus.stf.plataforma.userauthentication.infra.configuration;

import java.security.Principal;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.security.core.userdetails.User;
import org.springframework.security.oauth2.provider.OAuth2Authentication;
import org.springframework.stereotype.Component;

import br.jus.stf.plataforma.userauthentication.domain.model.Recurso;
import br.jus.stf.plataforma.userauthentication.domain.model.Usuario;

/**
 * @author Rodrigo Barreiros
 * 
 * @since 1.0.0
 * @since 15.08.2016
 */
@Component
public class UserDetailsExtractor {

	public Map<String, Object> extract(Usuario usuario, User user) {
		Map<String, Object> details = new LinkedHashMap<>();
		details.put("componentes", extractComponents(usuario.recursos()));
		details.put("authorities", user.getAuthorities());
		details.put("pessoaId", usuario.pessoa().id());
		details.put("login", user.getUsername());
		return details;
	}

	public Map<String, Object> extract(Principal principal) {
		OAuth2Authentication authentication = (OAuth2Authentication) principal;
		Map<String, Object> map = new LinkedHashMap<>();
		if (authentication.getPrincipal() instanceof UserDetails) {
			UserDetails user = (UserDetails) authentication.getPrincipal();
			map.put("componentes", extractComponents(user.getRecursos()));
			map.put("authorities", user.getAuthorities());
			map.put("pessoaId", user.getPessoaId());
			map.put("login", user.getUsername());
		} else {
			map.put("login", authentication.getPrincipal().toString());
		}
		return map;
	}
	
	private List<String> extractComponents(Set<Recurso> recursos) {
		return recursos.stream().map(r -> r.nome()).collect(Collectors.toList());
	}

}
