package wesley.engsoft2.locacao.repositorio;
import org.junit.jupiter.api.*;
import wesley.engsoft2.locacao.builder.AluguelBuilder;
import wesley.engsoft2.locacao.modelo.Aluguel;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.NoResultException;
import javax.persistence.Persistence;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class AluguelRepositoryTest {

	private EntityManager manager;
	private static EntityManagerFactory emf;
	private AluguelRepository alugueis;

	@BeforeAll
	public static void inicio() {
		emf = Persistence.createEntityManagerFactory("locadoraPU_test");
	}

	@BeforeEach
	public void antes() {
		manager = emf.createEntityManager();
		manager.getTransaction().begin();
		alugueis = new AluguelRepositoryImpl(manager);
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
	public void deveSalvarUmAluguel(){

		Aluguel aluguel = AluguelBuilder.umAluguel().comDataDeVencimento((LocalDate.now().plusDays(30))).constroi();

		alugueis.salva(aluguel);
		manager.flush();
		manager.clear();

		Aluguel novoAluguel = alugueis.buscaPorDataVencimento(LocalDate.now().plusDays(30));

		Assertions.assertNotNull(novoAluguel);

	}

	@Test
	public void deveAtualizarUmAluguel() {

		Aluguel aluguel = AluguelBuilder.umAluguel().comDataDeVencimento((LocalDate.now().plusDays(30))).comObs("Teste").constroi();

		alugueis.salva(aluguel);
		aluguel.setObs("Mudou");

		alugueis.atualiza(aluguel);

		manager.flush();
		manager.clear();

		Aluguel novoAluguel = alugueis.buscaPorObs("Mudou");
		Assertions.assertNotNull(novoAluguel);

		Assertions.assertThrows(NoResultException.class,
			() -> alugueis.buscaPorObs("Teste"),
			"Deveria ter lançado a exceção NoResultException");
	}

	@Test
	public void deveExcluirUmAluguel() {

		Aluguel aluguel = AluguelBuilder.umAluguel().comDataDeVencimento((LocalDate.now().plusDays(30))).comObs("Teste").constroi();

		alugueis.salva(aluguel);
		alugueis.exclui(aluguel);

		manager.flush();
		manager.clear();

		Assertions.assertThrows(NoResultException.class,
				() -> alugueis.buscaPorDataVencimento(LocalDate.now().plusDays(30)),
				"Deveria ter lançado a exceção NoResultException");
	}

	@Test
	public void deveRecuperarUmaListaComTodosOsAlugueisPagosDeUmCliente(){

		Aluguel aluguel01 = AluguelBuilder.umAluguel().comDataDeVencimento(LocalDate.of(2020,11,30)).comValorpago(new BigDecimal(500)).constroi();

		Aluguel aluguel02 = AluguelBuilder.umAluguel().comDataDeVencimento(LocalDate.of(2020,11,25)).comValorpago(new BigDecimal(1000)).constroi();

		Aluguel aluguel03 = AluguelBuilder.umAluguel().comDataDeVencimento(LocalDate.of(2020,11,20)).constroi();

		alugueis.salva(aluguel01);
		alugueis.salva(aluguel02);
		alugueis.salva(aluguel03);

		manager.flush();
		manager.clear();

		List<Aluguel> listaDeAlugueis = alugueis.recuperarAlugueisPagosPor("Wesley");

		Stream<Aluguel> listaDeAlugueisPagos = listaDeAlugueis.
				stream().filter(aluguel -> aluguel.getValorPago()!=null);

		assertEquals(2, listaDeAlugueisPagos.count());
	}

	@Test
	public void deveRecuperarUmaListaComTodosOsAlugueisPagosComAtrasoNaDataDeVencimentoDeUmCliente() {

		Aluguel aluguel01 = AluguelBuilder.umAluguel().comDataDeVencimento(LocalDate.of(2020,11,30))
				.comDataDePagamento(LocalDate.of(2020,12,30)).comValorpago(new BigDecimal(500)).constroi();

		Aluguel aluguel02 = AluguelBuilder.umAluguel().comDataDeVencimento(LocalDate.of(2020,11,25)).comValorpago(new BigDecimal(1000)).constroi();

		Aluguel aluguel03 = AluguelBuilder.umAluguel().comDataDeVencimento(LocalDate.of(2020,11,20))
				.comDataDePagamento(LocalDate.of(2020,12,15)).constroi();

		alugueis.salva(aluguel01);
		alugueis.salva(aluguel02);
		alugueis.salva(aluguel03);

		manager.flush();
		manager.clear();

		List<Aluguel> listaDeAlugueis = alugueis.recuperarAlugueisPagosEmAtrasoNaDataDeVencimentoPor("Wesley");

		Stream<Aluguel> listaDeAlugueisPagos = listaDeAlugueis.
				stream().filter(aluguel -> aluguel.getValorPago() != null);

		Assertions.assertEquals(1, listaDeAlugueisPagos.count());
	}

}
