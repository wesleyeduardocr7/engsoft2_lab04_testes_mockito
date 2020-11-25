package wesley.engsoft2.locacao.repositorio;
import wesley.engsoft2.locacao.modelo.Cliente;
import javax.persistence.EntityManager;

public class ClienteRepositoryImpl implements ClienteRepository {

	private EntityManager manager;

	public ClienteRepositoryImpl(EntityManager manager) {
		this.manager = manager;
	}

	@Override
	public Cliente buscaPorNome(String nome) {
		return manager.createQuery("SELECT c FROM Cliente c WHERE c.nome = :pNome", Cliente.class)
				.setParameter("pNome", nome)
				.getSingleResult();
	}

	@Override
	public void salva(Cliente cliente) {
		manager.persist(cliente);
	}

	@Override
	public void exclui(Cliente cliente) {
		manager.remove(cliente);
	}

	@Override
	public void atualiza(Cliente cliente) {
		manager.merge(cliente);
	}
}
