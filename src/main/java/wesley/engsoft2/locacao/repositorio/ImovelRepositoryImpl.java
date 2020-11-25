package wesley.engsoft2.locacao.repositorio;
import wesley.engsoft2.locacao.modelo.Imovel;
import javax.persistence.EntityManager;

public class ImovelRepositoryImpl implements ImovelRepository {

	private EntityManager manager;

	public ImovelRepositoryImpl(EntityManager manager) {
		this.manager = manager;
	}

	@Override
	public Imovel buscaPorId(Long id) {
		return manager.createQuery("SELECT u FROM Imovel u WHERE u.id = :pId", Imovel.class)
				.setParameter("pId", id)
				.getSingleResult();
	}

	@Override
	public Imovel buscaPorTipo(String tipo) {
		return manager.createQuery("SELECT u FROM Imovel u WHERE u.tipoDeImovel = :pTipo", Imovel.class)
				.setParameter("pTipo", tipo)
				.getSingleResult();
	}

	@Override
	public void salva(Imovel imovel) {
		manager.persist(imovel);
	}

	@Override
	public void exclui(Imovel imovel) {
		manager.remove(imovel);
	}

	@Override
	public void atualiza(Imovel imovel) {
		manager.merge(imovel );
	}
}
