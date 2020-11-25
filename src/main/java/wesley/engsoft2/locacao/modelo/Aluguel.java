package wesley.engsoft2.locacao.modelo;
import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.Period;
import java.util.Objects;

@Entity
@Table(name = "aluguel")
public class Aluguel {

	@ManyToOne(cascade= {CascadeType.MERGE, CascadeType.ALL})
	private Locacao locacao;

	@Id
	@Column(name = "data_vencimento")
	private LocalDate dataVencimento;

	@Column(name = "valor_pago")
	private BigDecimal valorPago;

	@Column(name = "data_pagamento")
	private LocalDate dataPagamento;

	@Column(name = "obs")
	private String obs;

	public Aluguel(){}

	public Locacao getLocacao() {
		return locacao;
	}

	public void setLocacao(Locacao locacao) {
		this.locacao = locacao;
	}

	public LocalDate getDataVencimento() {
		return dataVencimento;
	}

	public void setDataVencimento(LocalDate dataVencimento) {
		this.dataVencimento = dataVencimento;
	}

	public BigDecimal getValorPago() {
		return valorPago;
	}

	public void setValorPago(BigDecimal valorPago) {

		if (valorPago != null && this.locacao.getValorAluguel() != null) {
			if (valorPago.compareTo(this.locacao.getValorAluguel()) == -1) {
				throw new IllegalArgumentException("Valor pago deve ser no mímimo igual ao valor do Aluguel");
			}
		}
		this.valorPago = valorPago;
	}

	public LocalDate getDataPagamento() {
		return dataPagamento;
	}

	public void setDataPagamento(LocalDate dataPagamento) {
		this.dataPagamento = dataPagamento;
	}

	public String getObs() {
		return obs;
	}

	public void setObs(String obs) {
		this.obs = obs;
	}

	public BigDecimal getValorASerPago() {

		if(pagamentoEmAtraso()){
		  return aluguelComMulta();
		}

		return  this.locacao.getValorAluguel();
	}

	private BigDecimal aluguelComMulta(){

		BigDecimal valorMulta = calculaValorDaMulta();

		if (valorMulta.compareTo(oitentaPorCentoValorFixoDoAlugue()) == 1) {
			return this.locacao.getValorAluguel().add(oitentaPorCentoValorFixoDoAlugue());
		} else {
			return this.locacao.getValorAluguel().add(valorMulta);
		}
	}

	private BigDecimal calculaValorDaMulta(){
		Integer quantidadeDeDiasEmAtraso = getQuantidadeDeDiasEmAtraso();
		return (new BigDecimal(quantidadeDeDiasEmAtraso).multiply(new BigDecimal(0.33)))
				.divide(new BigDecimal(100)).multiply(this.locacao.getValorAluguel());
	}

	private Integer getQuantidadeDeDiasEmAtraso(){
		Period period = Period.between(getDataVencimento(),getDataPagamento());
		return period.getDays();
	}

	private BigDecimal oitentaPorCentoValorFixoDoAlugue(){
		return this.locacao.getValorAluguel().multiply(new BigDecimal(0.8));
	}

	private boolean pagamentoEmAtraso(){

		if(this.dataPagamento!=null && this.dataVencimento!=null){
			if(this.dataPagamento.isAfter(this.dataVencimento)){
				return true;
			}
		}

		return false;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;
		Aluguel aluguel = (Aluguel) o;
		return locacao.equals(aluguel.locacao);
	}

	@Override
	public int hashCode() {
		return Objects.hash(locacao);
	}


	@Override
	public String toString() {
		return "Aluguel{" +
				"locacao=" + locacao.toString() +
				", dataVencimento=" + dataVencimento +
				", valorPago=" + valorPago +
				", valorASerPago=" + getValorASerPago()+
				", dataPagamento=" + dataPagamento +
				", obs='" + obs + '\'' +
				'}';
	}
}