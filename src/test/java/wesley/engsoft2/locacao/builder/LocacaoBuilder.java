package wesley.engsoft2.locacao.builder;
import wesley.engsoft2.locacao.modelo.Aluguel;
import wesley.engsoft2.locacao.modelo.Cliente;
import wesley.engsoft2.locacao.modelo.Imovel;
import wesley.engsoft2.locacao.modelo.Locacao;

import java.math.BigDecimal;
import java.time.LocalDate;

public class LocacaoBuilder {

	private Locacao locacao;
	private static Long contadorID = new Long(1);

	private LocacaoBuilder() {}
	
	public static LocacaoBuilder umaLocacao() {

		LocacaoBuilder builder = new LocacaoBuilder();

		builder.locacao = new Locacao();
		builder.locacao.setDataInicio(LocalDate.now());

		return builder;
	}

	public LocacaoBuilder paraUmImovel(String tipoImovel) {
		locacao.setImovel(new Imovel(tipoImovel));
		return this;
	}

	public LocacaoBuilder paraUmCliente(String nomeCliente) {
		locacao.setCliente(new Cliente(nomeCliente));
		return this;
	}

	public LocacaoBuilder ativo(boolean ativo) {
		locacao.setAtivo(ativo);
		return this;
	}

	public LocacaoBuilder noBairro(String bairro) {
		locacao.getImovel().setBairro(bairro);
		return this;
	}

	public LocacaoBuilder comValorSugerido(BigDecimal valorSugerido) {
		locacao.getImovel().setValorDoAluguelSugerido(valorSugerido);
		return this;
	}

	public Locacao constroi(){
		return locacao;
	}
}