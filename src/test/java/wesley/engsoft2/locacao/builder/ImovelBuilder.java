package wesley.engsoft2.locacao.builder;
import wesley.engsoft2.locacao.modelo.Imovel;

public class ImovelBuilder {

	private Imovel imovel;
	private static Long contadorID = new Long(1);
	
	private ImovelBuilder() {}
	
	public static ImovelBuilder umImovel() {
		
		ImovelBuilder builder = new ImovelBuilder();
		builder.imovel = new Imovel();
		builder.imovel.setId(contadorID + new Long(1));
		builder.imovel.setTipoDeImovel("Luxo");
		builder.imovel.setEndereco("São Luís");
		return builder;
	}

	public Imovel constroi(){
		return imovel;
	}
}