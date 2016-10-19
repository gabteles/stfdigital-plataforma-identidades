package br.jus.stf.plataforma.identidades.infra.configuration;

import java.security.Principal;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.oauth2.provider.OAuth2Authentication;
import org.springframework.stereotype.Component;

import br.jus.stf.plataforma.identidades.domain.model.Recurso;
import br.jus.stf.plataforma.identidades.domain.model.Usuario;

/**
 * @author Rodrigo Barreiros
 * 
 * @since 1.0.0
 * @since 15.08.2016
 */
@Component
public class UserDetailsExtractor {

    public Map<String, Object> extract(Usuario usuario, List<GrantedAuthority> authorities) {
        Map<String, Object> details = new LinkedHashMap<>();
        details.put("componentes", extractComponents(usuario.recursos()));
        details.put("authorities", authorities);
        details.put("pessoaId", usuario.pessoa().id().toLong());
        details.put("usuarioId", usuario.identity().toLong());
        details.put("login", usuario.login());
        return details;
    }

    @SuppressWarnings("unchecked")
    public Map<String, Object> extract(Principal principal) {
        OAuth2Authentication authentication = (OAuth2Authentication) principal;
        if (authentication.getPrincipal() instanceof User) {
            return (Map<String, Object>) authentication.getUserAuthentication().getDetails();
        } else {
            Map<String, Object> map = new LinkedHashMap<>();
            map.put("login", authentication.getPrincipal().toString());
            return map;
        }
    }

    private static List<String> extractComponents(Set<Recurso> recursos) {
        return recursos.stream().map(r -> r.nome()).collect(Collectors.toList());
    }

}
