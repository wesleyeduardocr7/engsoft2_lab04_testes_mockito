package wesley.engsoft2.locacao.repositorio;
import org.junit.jupiter.api.*;
import wesley.engsoft2.locacao.modelo.Imovel;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.NoResultException;
import javax.persistence.Persistence;

public class ImovelRepositoryTest {

	private EntityManager manager;
	private static EntityManagerFactory emf;
	private ImovelRepository imoveis;

	@BeforeAll
	public static void inicio() {
		emf = Persistence.createEntityManagerFactory("locadoraPU_test");
	}

	@BeforeEach
	public void antes() {
		manager = emf.createEntityManager();
		manager.getTransaction().begin();
		imoveis = new ImovelRepositoryImpl(manager);
	}

	@AfterEach
	public void depois() {
		manager.getTransaction().rollback();
	}

	@AfterAll
	public static void fim() {
		emf.close();
	}

	@Test
	public void deveSalvarUmImovel() {

		Imovel imovel = new Imovel("Luxo");

		imoveis.salva(imovel);
		manager.flush();
		manager.clear();

		Imovel novoImovel = imoveis.buscaPorTipo("Luxo" );
		Assertions.assertNotNull(novoImovel);
	}

	@Test
	public void deveAtualizarUmImovel() {

		Imovel imovel = new Imovel("Luxo");

		imoveis.salva(imovel);
		imovel.setTipoDeImovel("Básico");

		imoveis.atualiza(imovel);

		manager.flush();
		manager.clear();

		Imovel novoImovel = imoveis.buscaPorTipo("Básico" );
		Assertions.assertNotNull(novoImovel);

		Assertions.assertThrows(NoResultException.class,
				() -> imoveis.buscaPorTipo("luxo"),
				"Deveria ter lançado a exceção NoResultException");
	}

	@Test
	public void deveExcluirUmImovel() {

		Imovel imovel = new Imovel("Luxo");

		imoveis.salva(imovel);
		imoveis.exclui(imovel);

		manager.flush();
		manager.clear();

		Assertions.assertThrows(NoResultException.class,
				() -> imoveis.buscaPorTipo("luxo"),
				"Deveria ter lançado a exceção NoResultException");
	}

}
