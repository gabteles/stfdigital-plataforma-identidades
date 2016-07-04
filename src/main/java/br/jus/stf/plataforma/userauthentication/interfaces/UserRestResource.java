package br.jus.stf.plataforma.userauthentication.interfaces;

import java.security.Principal;
import java.util.LinkedHashMap;
import java.util.Map;

import org.springframework.security.oauth2.provider.OAuth2Authentication;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.jus.stf.plataforma.userauthentication.infra.configuration.UserDetails;

/**
 * @author Rodrigo Barreiros
 * 
 * @since 1.0.0
 * @since 30.06.2016
 */
@RestController
public class UserRestResource {

	@RequestMapping({ "/user" })
	public Map<String, Object> user(Principal principal) {
		OAuth2Authentication authentication = (OAuth2Authentication) principal;
		UserDetails user = (UserDetails) authentication.getPrincipal();
		Map<String, Object> map = new LinkedHashMap<>();
		map.put("pessoaId", user.getPessoaId());
		map.put("login", user.getUsername());
		return map;
	}

}