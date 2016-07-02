package br.jus.stf.plataforma.userauthentication.interfaces;

import java.security.Principal;
import java.util.LinkedHashMap;
import java.util.Map;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Rodrigo Barreiros
 * 
 * @since 1.0.0
 * @since 30.06.2016
 */
@RestController
public class UserRestResource {

	@RequestMapping({ "/user" })
	public Map<String, String> user(Principal principal) {
		Map<String, String> map = new LinkedHashMap<>();
		map.put("name", principal.getName());
		return map;
	}

}
