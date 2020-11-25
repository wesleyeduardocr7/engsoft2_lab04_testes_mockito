package wesley.engsoft2.locacao.modelo;
import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Objects;

@Entity
@Table(name = "locacao")
public class Locacao {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private Long id;

	@ManyToOne(cascade= {CascadeType.MERGE, CascadeType.PERSIST})
	private Imovel imovel;

	@ManyToOne(cascade= {CascadeType.MERGE, CascadeType.PERSIST})
	private Cliente cliente;

	@Column(name = "valor_aluguel")
	private BigDecimal valorAluguel;

	@Column(name = "percentual_multa")
	private BigDecimal percentualMulta;

	@Column(name = "dia_vencimento")
	private Integer diaVencimento;

	@Column(name = "data_inicio")
	private LocalDate dataInicio;

	@Column(name = "data_fim")
	private LocalDate dataFim;

	@Column(name = "ativo")
	private boolean ativo;

	@Column(name = "obs")
	private String obs;

	public Locacao() {}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Imovel getImovel() {
		return imovel;
	}

	public void setImovel(Imovel imovel) {
		this.imovel = imovel;
	}

	public Cliente getCliente() {
		return cliente;
	}

	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}

	public BigDecimal getValorAluguel() {
		return valorAluguel;
	}

	public void setValorAluguel(BigDecimal valorAluguel) {
		this.valorAluguel = valorAluguel;
	}

	public BigDecimal getPercentualMulta() {
		return percentualMulta;
	}

	public void setPercentualMulta(BigDecimal percentualMulta) {
		this.percentualMulta = percentualMulta;
	}

	public Integer getDiaVencimento() {
		return diaVencimento;
	}

	public void setDiaVencimento(Integer diaVencimento) {
		this.diaVencimento = diaVencimento;
	}

	public LocalDate getDataInicio() {
		return dataInicio;
	}

	public void setDataInicio(LocalDate dataInicio) {
		this.dataInicio = dataInicio;
	}

	public LocalDate getDataFim() {
		return dataFim;
	}

	public void setDataFim(LocalDate dataFim) {
		this.dataFim = dataFim;
	}

	public boolean isAtivo() {
		return ativo;
	}

	public void setAtivo(boolean ativo) {
		this.ativo = ativo;
	}

	public String getObs() {
		return obs;
	}

	public void setObs(String obs) {
		this.obs = obs;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;
		Locacao locacao = (Locacao) o;
		return id.equals(locacao.id);
	}

	@Override
	public int hashCode() {
		return Objects.hash(id);
	}

	@Override
	public String toString() {
		return "Locacao{" +
				"id=" + id +
				", imovel=" + imovel +
				", cliente=" + cliente +
				", valorAluguel=" + valorAluguel +
				", percentualMulta=" + percentualMulta +
				", diaVencimento=" + diaVencimento +
				", dataInicio=" + dataInicio +
				", dataFim=" + dataFim +
				", ativo=" + ativo +
				", obs='" + obs + '\'' +
				'}';
	}
}