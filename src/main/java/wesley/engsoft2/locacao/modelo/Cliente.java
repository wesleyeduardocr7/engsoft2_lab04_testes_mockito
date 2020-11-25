package wesley.engsoft2.locacao.modelo;
import javax.persistence.*;
import java.time.LocalDate;
import java.util.Objects;

@Entity
@Table(name = "cliente")
public class Cliente {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private Long id;

	@Column(name = "nome",length = 45)
	private String nome;

	@Column(name = "cpf")
	private Long cpf;

	@Column(name = "telefone_1")
	private Long telefone1;

	@Column(name = "telefone_2")
	private Long telefone2;

	@Column(name = "email",length = 50)
	private String email;

	@Column(name = "data_nascimento")
	private LocalDate dataNascimento;

	public Cliente() {}

	public Cliente(String nome) {
		this.nome = nome;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public Long getCpf() {
		return cpf;
	}

	public void setCpf(Long cpf) {
		this.cpf = cpf;
	}

	public Long getTelefone1() {
		return telefone1;
	}

	public void setTelefone1(Long telefone1) {
		this.telefone1 = telefone1;
	}

	public Long getTelefone2() {
		return telefone2;
	}

	public void setTelefone2(Long telefone2) {
		this.telefone2 = telefone2;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public LocalDate getDataNascimento() {
		return dataNascimento;
	}

	public void setDataNascimento(LocalDate dataNascimento) {
		this.dataNascimento = dataNascimento;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;
		Cliente cliente = (Cliente) o;
		return id.equals(cliente.id);
	}

	@Override
	public int hashCode() {
		return Objects.hash(id);
	}

	@Override
	public String toString() {
		return "Cliente{" +
				"id=" + id +
				", nome='" + nome + '\'' +
				", cpf=" + cpf +
				", telefone1=" + telefone1 +
				", telefone2=" + telefone2 +
				", email='" + email + '\'' +
				", dataNascimento=" + dataNascimento +
				'}';
	}
}