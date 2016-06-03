package br.jus.stf.plataforma.acessos.infra.configuration;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;

import br.jus.stf.core.framework.Profiles;
import br.jus.stf.plataforma.acessos.domain.model.Usuario;
import br.jus.stf.plataforma.acessos.domain.model.UsuarioRepository;

/**
 * @author Lucas.Rodrigues
 *
 */
@Configuration
public class AcessosConfiguration {
	
	public static final String INDICE_USUARIO = "usuario";
	
	private static final String USUARIO_RESOURCE = "/indices/acessos/usuario.json";
	
	@Autowired
	private Environment env;
	
	@Autowired
	private UsuarioRepository usuarioRepository;
	
//	@Autowired
//	private IndexadorRestAdapter indexadorRestAdapter;
	
	@PostConstruct
	private void configure() throws Exception {
		criarIndice(INDICE_USUARIO, USUARIO_RESOURCE);
		
		if (env.acceptsProfiles(Profiles.DEVELOPMENT)) {
			carregarIndiceUsuarios();
		}
	}
	
	private void criarIndice(String indice, String resource) throws Exception {
		// TODO: Verificar como será feita a indexação para o Elastic
//		String configuracao = ResourceFileUtils.read(resource);			
//		indexadorRestAdapter.criarIndice(indice, configuracao);
	}
	
	private void carregarIndiceUsuarios() {
		usuarioRepository.findAll().forEach(u -> {
			try {
//				indexadorRestAdapter.indexar(INDICE_USUARIO, prepararUsuarioIndexacao(u));
		    } catch (Exception e) {
		        throw new RuntimeException(e);
		    }	
		});
	}
	
	private Usuario prepararUsuarioIndexacao(Usuario usuario) {
		if (usuario.lotacao() != null) {
			return new Usuario(usuario.identity(), usuario.pessoa(), usuario.login(), usuario.lotacao());
		}
		return new Usuario(usuario.identity(), usuario.pessoa(), usuario.login());
	}
	
}
