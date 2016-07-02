package br.jus.stf.plataforma.userauthentication.infra.configuration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.oauth2.config.annotation.configurers.ClientDetailsServiceConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerEndpointsConfigurer;

/**
 * @author Rodrigo Barreiros
 * 
 * @since 1.0.0
 * @since 30.06.2016
 */
@Configuration
@EnableAuthorizationServer
public class OAuth2AuthorizationConfigurer extends AuthorizationServerConfigurerAdapter {
	
	@Autowired
	private AuthenticationManager authenticationManager;
	
	@Override
	public void configure(AuthorizationServerEndpointsConfigurer endpoints) throws Exception {
		endpoints.authenticationManager(authenticationManager);
	}
	
	@Override
	public void configure(ClientDetailsServiceConfigurer clients) throws Exception {
		clients.inMemory()
			.withClient("userinterface")
			.secret("userinterface")
			.authorizedGrantTypes("authorization_code", "refresh_token", "password")
			.scopes("openid")
		.and()
			.withClient("peticionamento")
			.secret("peticionamento")
			.authorizedGrantTypes("client_credentials", "refresh_token")
			.scopes("server");
	}

}
