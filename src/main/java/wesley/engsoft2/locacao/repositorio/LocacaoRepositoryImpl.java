package wesley.engsoft2.locacao.repositorio;
import wesley.engsoft2.locacao.modelo.Locacao;
import javax.persistence.EntityManager;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

public class LocacaoRepositoryImpl implements LocacaolRepository {

	private EntityManager manager;

	public LocacaoRepositoryImpl(EntityManager manager) {
		this.manager = manager;
	}

	@Override
	public Locacao buscaPorDataInicio(LocalDate dataInicio) {
		return manager.createQuery("SELECT l FROM Locacao l WHERE l.dataInicio = :pDataInicio", Locacao.class)
				.setParameter("pDataInicio", dataInicio)
				.getSingleResult();
	}

	@Override
	public List<Locacao> recuperarLocacoesPor(String bairro, String tipoImovel, boolean ativo) {
		return manager.createQuery("SELECT l FROM Locacao l WHERE l.ativo = :pAtivo AND l.imovel.bairro = :pBairro AND l.imovel.tipoDeImovel = :pTipoImovel" , Locacao.class)
				.setParameter("pAtivo", ativo)
				.setParameter("pBairro", bairro)
				.setParameter("pTipoImovel", tipoImovel)
				.getResultList();
	}

	@Override
	public List<Locacao> recuperarLocacoesPor(BigDecimal limitePreco, boolean ativo) {
		return manager.createQuery("SELECT l FROM Locacao l WHERE l.ativo = :pAtivo AND l.imovel.valorDoAluguelSugerido <= :pLimitePreco " , Locacao.class)
				.setParameter("pAtivo", ativo)
				.setParameter("pLimitePreco", limitePreco)
				.getResultList();
	}

	@Override
	public void salva(Locacao locacao) {
		manager.persist(locacao);
	}

	@Override
	public void exclui(Locacao locacao) {
		manager.remove(locacao);
	}

	@Override
	public void atualiza(Locacao locacao) {
		manager.merge(locacao);
	}
}
