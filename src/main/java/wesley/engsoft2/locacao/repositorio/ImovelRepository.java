package wesley.engsoft2.locacao.repositorio;
import wesley.engsoft2.locacao.modelo.Imovel;

public interface ImovelRepository {

	Imovel buscaPorId(Long id);

	Imovel buscaPorTipo(String tipo);

	void salva(Imovel imovel);

	void exclui(Imovel imovel);

	void atualiza(Imovel imovel);

}
