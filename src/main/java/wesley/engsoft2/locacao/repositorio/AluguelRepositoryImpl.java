package wesley.engsoft2.locacao.repositorio;
import wesley.engsoft2.locacao.modelo.Aluguel;
import wesley.engsoft2.locacao.modelo.Locacao;

import javax.persistence.EntityManager;
import java.time.LocalDate;
import java.util.List;

public class AluguelRepositoryImpl implements AluguelRepository {

	private EntityManager manager;

	public AluguelRepositoryImpl(EntityManager manager) {
		this.manager = manager;
	}

	@Override
	public Aluguel buscaPorDataVencimento(LocalDate dataVencimento) {
		return manager.createQuery("SELECT a FROM Aluguel a WHERE a.dataVencimento = :pDataVencimento", Aluguel.class)
				.setParameter("pDataVencimento", dataVencimento)
				.getSingleResult();
	}

	@Override
	public Aluguel buscaPorObs(String obs) {
		return manager.createQuery("SELECT a FROM Aluguel a WHERE a.obs = :pObs", Aluguel.class)
				.setParameter("pObs", obs)
				.getSingleResult();
	}

	@Override
	public List<Aluguel> recuperarAlugueisPagosPor(String nomeCliente) {
		return manager.createQuery("SELECT a FROM Aluguel a WHERE a.locacao.cliente.nome = :pNomeCliente" , Aluguel.class)
				.setParameter("pNomeCliente", nomeCliente)
				.getResultList();
	}

	@Override
	public List<Aluguel> recuperarAlugueisPagosEmAtrasoNaDataDeVencimentoPor(String nomeCliente) {
		return manager.createQuery("SELECT a FROM Aluguel a WHERE a.locacao.cliente.nome = :pNomeCliente AND a.dataPagamento > a.dataVencimento ", Aluguel.class)
				.setParameter("pNomeCliente", nomeCliente)
				.getResultList();
	}

	@Override
	public void salva(Aluguel aluguel) {
		manager.persist(aluguel);
	}

	@Override
	public void exclui(Aluguel aluguel) {
		manager.remove(aluguel);
	}

	@Override
	public void atualiza(Aluguel aluguel) {
		manager.merge(aluguel);
	}
}
