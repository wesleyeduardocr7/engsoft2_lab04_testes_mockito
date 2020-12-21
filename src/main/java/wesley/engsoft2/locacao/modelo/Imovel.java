package wesley.engsoft2.locacao.modelo;
import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Objects;

@Entity
@Table(name = "imovel")
public class Imovel {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private Long id;

	@Column(name = "tipo_imovel", nullable = false, length = 100)
	private String tipoDeImovel;

	@Column(name = "endereco")
	private String endereco;

	@Column(name = "bairro",length = 45)
	private String bairro;

	@Column(name = "cep")
	private Long cep;

	@Column(name = "metragem")
	private BigDecimal metragem;

	@Column(name = "quantidade_dormitorios")
	private Integer quantidadeDeDormitorios;

	@Column(name = "quantidade_banheiros")
	private Integer quantidadeDeBanheiros;

	@Column(name = "quantidade_Suites")
	private Integer quantidadeDeSuites;

	@Column(name = "quantidade_vagas_garagem")
	private Integer quantidadeDeVagasDeGaragem;

	@Column(name = "valor_aluguel_sugerido")
	private BigDecimal valorDoAluguelSugerido;

	@Column(name = "obs")
	private String obs;

	public Imovel() {}

	public Imovel(String tipoDeImovel) {
		this.tipoDeImovel = tipoDeImovel;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getTipoDeImovel() {
		return tipoDeImovel;
	}

	public void setTipoDeImovel(String tipoDeImovel) {
		this.tipoDeImovel = tipoDeImovel;
	}

	public String getEndereco() {
		return endereco;
	}

	public void setEndereco(String endereco) {
		this.endereco = endereco;
	}

	public String getBairro() {
		return bairro;
	}

	public void setBairro(String bairro) {
		this.bairro = bairro;
	}

	public Long getCep() {
		return cep;
	}

	public void setCep(Long cep) {
		this.cep = cep;
	}

	public BigDecimal getMetragem() {
		return metragem;
	}

	public void setMetragem(BigDecimal metragem) {
		this.metragem = metragem;
	}

	public Integer getQuantidadeDeDormitorios() {
		return quantidadeDeDormitorios;
	}

	public void setQuantidadeDeDormitorios(Integer quantidadeDeDormitorios) {
		this.quantidadeDeDormitorios = quantidadeDeDormitorios;
	}

	public Integer getQuantidadeDeBanheiros() {
		return quantidadeDeBanheiros;
	}

	public void setQuantidadeDeBanheiros(Integer quantidadeDeBanheiros) {
		this.quantidadeDeBanheiros = quantidadeDeBanheiros;
	}

	public Integer getQuantidadeDeSuites() {
		return quantidadeDeSuites;
	}

	public void setQuantidadeDeSuites(Integer quantidadeDeSuites) {
		this.quantidadeDeSuites = quantidadeDeSuites;
	}

	public Integer getQuantidadeDeVagasDeGaragem() {
		return quantidadeDeVagasDeGaragem;
	}

	public void setQuantidadeDeVagasDeGaragem(Integer quantidadeDeVagasDeGaragem) {
		this.quantidadeDeVagasDeGaragem = quantidadeDeVagasDeGaragem;
	}

	public BigDecimal getValorDoAluguelSugerido() {
		return valorDoAluguelSugerido;
	}

	public void setValorDoAluguelSugerido(BigDecimal valorDoAluguelSugerido) {
		this.valorDoAluguelSugerido = valorDoAluguelSugerido;
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
		Imovel imovel = (Imovel) o;
		return id.equals(imovel.id);
	}

	@Override
	public int hashCode() {
		return Objects.hash(id);
	}

	@Override
	public String toString() {
		return "Imovel{" +
				"id=" + id +
				", tipoDeImovel='" + tipoDeImovel + '\'' +
				", endereco='" + endereco + '\'' +
				", bairro='" + bairro + '\'' +
				", cep=" + cep +
				", metragem=" + metragem +
				", quantidadeDeDormitorios=" + quantidadeDeDormitorios +
				", quantidadeDeBanheiros=" + quantidadeDeBanheiros +
				", quantidadeDeSuites=" + quantidadeDeSuites +
				", quantidadeDeVagasDeGaragem=" + quantidadeDeVagasDeGaragem +
				", valorDoAluguelSugerido=" + valorDoAluguelSugerido +
				", obs='" + obs + '\'' +
				'}';
	}
}