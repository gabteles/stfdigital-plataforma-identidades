package br.jus.stf.plataforma.identidades.domain.model.corporativo;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Component;

import br.jus.stf.core.shared.identidade.PessoaId;

/**
 * @author Rafael Alencar
 * 
 * @since 1.0.0
 * @since 10.06.2016
 */
@Component
public class PessoaFactory {

	/**
	 * @param id
	 * @param nome
	 * @param cpf
	 * @param oab
	 * @param email
	 * @param telefone
	 * @return
	 */
	public Pessoa novaPessoa(PessoaId id, String nome, String cpf, String oab, String email, String telefone) {
		Pessoa pessoa;

		if (StringUtils.isNotBlank(cpf) && StringUtils.isNotBlank(oab) && StringUtils.isNotBlank(email)
				&& StringUtils.isNotBlank(telefone)) {
			pessoa = new Pessoa(id, nome, cpf, oab, email, telefone);

		} else if (StringUtils.isNotBlank(cpf) && StringUtils.isNotBlank(email) && StringUtils.isNotBlank(telefone)) {
			pessoa = new Pessoa(id, nome, cpf, email, telefone);

		} else if (StringUtils.isNotBlank(cpf)) {
			pessoa = new Pessoa(id, nome, cpf);

		} else {
			pessoa = new Pessoa(id, nome);
		}

		return pessoa;
	}

}
