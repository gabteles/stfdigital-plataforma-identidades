package br.jus.stf.plataforma.userauthentication.infra;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.persistence.TypedQuery;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.support.SimpleJpaRepository;
import org.springframework.stereotype.Repository;

import br.jus.stf.core.shared.identidade.PessoaId;
import br.jus.stf.plataforma.userauthentication.domain.model.identidade.Pessoa;
import br.jus.stf.plataforma.userauthentication.domain.model.identidade.PessoaRepository;

/**
 * @author Lucas.Rodrigues
 *
 */
@Repository
public class PessoaRepositoryImpl extends SimpleJpaRepository<Pessoa, PessoaId> implements PessoaRepository {

	private EntityManager entityManager;
	
	/**
	 * @param entityManager
	 */
	@Autowired
	public PessoaRepositoryImpl(EntityManager entityManager) {
		super(Pessoa.class, entityManager);
		this.entityManager = entityManager;
	}

	@Override
	public PessoaId nextId() {
		Query query = entityManager.createNativeQuery("SELECT corporativo.seq_pessoa.NEXTVAL FROM DUAL");
		Long sequencial = ((Number) query.getSingleResult()).longValue();
		
		return new PessoaId(sequencial);
	}
	
	@Override
	public List<Pessoa> findByNomeContaining(String nome) {
		TypedQuery<Pessoa> query = entityManager.createQuery("FROM Pessoa pess WHERE UPPER(pess.nome) LIKE :nome ORDER BY pess.nome", Pessoa.class);
		
		query.setParameter("nome", "%" + nome.trim().toUpperCase() + "%");
		
		return query.getResultList();
	}
	
	@Override
	public Pessoa findByCpf(String cpf) {
		TypedQuery<Pessoa> query = entityManager.createQuery("FROM Pessoa pess WHERE pess.cpf = :cpf", Pessoa.class);
		
		query.setParameter("cpf", cpf.trim());
		
		List<Pessoa> pessoas = query.getResultList();
		
		return pessoas.isEmpty() ? null : pessoas.get(0);
	}

}
