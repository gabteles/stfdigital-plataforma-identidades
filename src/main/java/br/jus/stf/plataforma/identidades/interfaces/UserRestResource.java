package br.jus.stf.plataforma.identidades.interfaces;

import java.security.Principal;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.jus.stf.plataforma.identidades.infra.configuration.UserDetailsExtractor;

/**
 * @author Rodrigo Barreiros
 * 
 * @since 1.0.0
 * @since 30.06.2016
 */
@RestController
public class UserRestResource {

    @Autowired
    private UserDetailsExtractor userDetailsExtractor;

    /**
     * @param principal
     * @return
     */
    @RequestMapping({ "/user" })
    public Map<String, Object> user(Principal principal) {
        return userDetailsExtractor.extract(principal);
    }

}
