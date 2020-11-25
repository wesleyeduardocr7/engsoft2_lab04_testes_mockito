package wesley.engsoft2.locacao.repositorio;
import wesley.engsoft2.locacao.modelo.Cliente;

public interface ClienteRepository {

	Cliente buscaPorNome(String nome);

	void salva(Cliente cliente);

	void exclui(Cliente cliente);

	void atualiza(Cliente cliente);
}
