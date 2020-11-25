package wesley.engsoft2.locacao.builder;
import wesley.engsoft2.locacao.modelo.Aluguel;
import wesley.engsoft2.locacao.modelo.Locacao;
import java.math.BigDecimal;
import java.time.LocalDate;

public class AluguelBuilder {

	private Aluguel aluguel;
	private static Long contadorID = new Long(1);

	private AluguelBuilder() {}
	
	public static AluguelBuilder umAluguel() {

		Locacao locacao = LocacaoBuilder.umaLocacao().ativo(true).paraUmImovel("Luxo")
				.noBairro("Calhau").paraUmCliente("Wesley").constroi();

		AluguelBuilder builder = new AluguelBuilder();

		builder.aluguel = new Aluguel();

		builder.aluguel.setLocacao(locacao);

		return builder;
	}

	public AluguelBuilder comDataDeVencimento(LocalDate dataVencimento) {
		aluguel.setDataVencimento(dataVencimento);
		return this;
	}

	public AluguelBuilder comValorpago(BigDecimal valorPago) {
		aluguel.setValorPago(valorPago);
		return this;
	}

	public AluguelBuilder comObs(String obs) {
		aluguel.setObs(obs);
		return this;
	}

	public AluguelBuilder comDataDePagamento(LocalDate dataPagamento) {
		aluguel.setDataPagamento(dataPagamento);
		return this;
	}

	public Aluguel constroi(){
		return aluguel;
	}
}