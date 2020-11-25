package wesley.engsoft2.locacao.repositorio;
import wesley.engsoft2.locacao.modelo.Aluguel;

import java.time.LocalDate;
import java.util.List;

public interface AluguelRepository {

	Aluguel buscaPorDataVencimento(LocalDate dataVencimento);

	Aluguel buscaPorObs(String obs);

	List<Aluguel> recuperarAlugueisPagosPor(String nomeCliente);

	void salva(Aluguel aluguel);

	void exclui(Aluguel aluguel);

	void atualiza(Aluguel aluguel);

	List<Aluguel> recuperarAlugueisPagosEmAtrasoNaDataDeVencimentoPor(String nomeCliente);
}
