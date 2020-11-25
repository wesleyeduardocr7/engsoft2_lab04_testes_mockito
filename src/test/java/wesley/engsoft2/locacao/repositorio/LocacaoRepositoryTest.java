package wesley.engsoft2.locacao.repositorio;
import org.junit.jupiter.api.*;
import wesley.engsoft2.locacao.builder.LocacaoBuilder;
import wesley.engsoft2.locacao.modelo.Locacao;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.NoResultException;
import javax.persistence.Persistence;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class LocacaoRepositoryTest {

	private EntityManager manager;
	private static EntityManagerFactory emf;
	private LocacaolRepository locacoes;

	@BeforeAll
	public static void inicio() {
		emf = Persistence.createEntityManagerFactory("locadoraPU_test");
	}

	@BeforeEach
	public void antes() {
		manager = emf.createEntityManager();
		manager.getTransaction().begin();
		locacoes = new LocacaoRepositoryImpl(manager);
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
	public void deveSalvarUmaLocacao() {

		Locacao locacao = LocacaoBuilder.umaLocacao().ativo(true).paraUmImovel("Luxo").paraUmCliente("Wesley").constroi();

		locacoes.salva(locacao);
		manager.flush();
		manager.clear();

		Locacao novaLocacao = locacoes.buscaPorDataInicio(LocalDate.now());

		Assertions.assertNotNull(novaLocacao);
	}

	@Test
	public void deveAtualizarUmaLocacao() {

		Locacao locacao = LocacaoBuilder.umaLocacao().ativo(true).paraUmImovel("Luxo").paraUmCliente("Wesley").constroi();

		locacoes.salva(locacao);
		locacao.setDataInicio(LocalDate.now().plusDays(5));

		locacoes.atualiza(locacao);

		manager.flush();
		manager.clear();

		Locacao novaLocacao = locacoes.buscaPorDataInicio(LocalDate.now().plusDays(5));
		Assertions.assertNotNull(novaLocacao);

		Assertions.assertThrows(NoResultException.class,
				() -> locacoes.buscaPorDataInicio(LocalDate.now()),
				"Deveria ter lançado a exceção NoResultException");
	}

	@Test
	public void deveExcluirUmaLocacao() {

		Locacao locacao = LocacaoBuilder.umaLocacao().ativo(true).paraUmImovel("Luxo").paraUmCliente("Wesley").constroi();

		locacoes.salva(locacao);
		locacoes.exclui(locacao);

		manager.flush();
		manager.clear();

		Assertions.assertThrows(NoResultException.class,
				() -> locacoes.buscaPorDataInicio(LocalDate.now()),
				"Deveria ter lançado a exceção NoResultException");
	}

	@Test
	public void deveRecuperarTodosOsImoveisDisponiveisDeUmDeterminadoBairroETipoApartamento() {

		Locacao locacao01 = LocacaoBuilder.umaLocacao().ativo(true).paraUmImovel("Luxo")
				.noBairro("Calhau").paraUmCliente("Wesley").constroi();

		Locacao locacao02 = LocacaoBuilder.umaLocacao().ativo(true).paraUmImovel("Casa")
				.noBairro("Turu").paraUmCliente("Carlos").constroi();

		Locacao locacao03 = LocacaoBuilder.umaLocacao().ativo(true).paraUmImovel("Apartamento")
				.noBairro("Barramar").paraUmCliente("Luis").constroi();

		Locacao locacao04 = LocacaoBuilder.umaLocacao().ativo(true).paraUmImovel("Sitio")
				.noBairro("Barramar").paraUmCliente("Andre").constroi();

		Locacao locacao05 = LocacaoBuilder.umaLocacao().ativo(false).paraUmImovel("Apartamento")
				.noBairro("Turu").paraUmCliente("Eduardo").constroi();

		Locacao locacao06 = LocacaoBuilder.umaLocacao().ativo(false).paraUmImovel("Apartamento")
				.noBairro("Turu").paraUmCliente("Antonio").constroi();

		locacoes.salva(locacao01);
		locacoes.salva(locacao02);
		locacoes.salva(locacao03);
		locacoes.salva(locacao04);
		locacoes.salva(locacao05);
		locacoes.salva(locacao06);
		manager.flush();
		manager.clear();

		List<Locacao> locacaoList = locacoes.recuperarLocacoesPor("Turu","Apartamento",false);

		assertEquals(2, locacaoList.size());
	}

	@Test
	public void deveRecuperarTodosOsImoveisDisponiveisComValorDoAluguelSugeridoIgualOuInferiorAUmDeterminadoValor() {

		Locacao locacao01 = LocacaoBuilder.umaLocacao().ativo(true).paraUmImovel("Luxo")
				.comValorSugerido(new BigDecimal(50000)).noBairro("Calhau").paraUmCliente("Wesley").constroi();

		Locacao locacao02 = LocacaoBuilder.umaLocacao().ativo(true).paraUmImovel("Casa")
				.comValorSugerido(new BigDecimal(70000)).noBairro("Turu").paraUmCliente("Carlos").constroi();

		Locacao locacao03 = LocacaoBuilder.umaLocacao().ativo(true).paraUmImovel("Apartamento")
				.comValorSugerido(new BigDecimal(85000)).noBairro("Barramar").paraUmCliente("Luis").constroi();

		Locacao locacao04 = LocacaoBuilder.umaLocacao().ativo(true).paraUmImovel("Sitio")
				.comValorSugerido(new BigDecimal(90000)).noBairro("Barramar").paraUmCliente("Andre").constroi();

		Locacao locacao05 = LocacaoBuilder.umaLocacao().ativo(false).paraUmImovel("Apartamento")
				.comValorSugerido(new BigDecimal(35000)).noBairro("Turu").paraUmCliente("Eduardo").constroi();

		Locacao locacao06 = LocacaoBuilder.umaLocacao().ativo(false).paraUmImovel("Apartamento")
				.comValorSugerido(new BigDecimal(20000)).noBairro("Turu").paraUmCliente("Antonio").constroi();

		locacoes.salva(locacao01);
		locacoes.salva(locacao02);
		locacoes.salva(locacao03);
		locacoes.salva(locacao04);
		locacoes.salva(locacao05);
		locacoes.salva(locacao06);
		manager.flush();
		manager.clear();

		List<Locacao> locacaoList = locacoes.recuperarLocacoesPor(new BigDecimal(50000),false);

		assertEquals(2, locacaoList.size());
	}

}
