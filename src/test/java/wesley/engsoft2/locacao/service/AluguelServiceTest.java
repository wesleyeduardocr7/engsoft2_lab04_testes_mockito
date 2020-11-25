package wesley.engsoft2.locacao.service;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import wesley.engsoft2.locacao.builder.AluguelBuilder;
import wesley.engsoft2.locacao.builder.ClienteBuilder;
import wesley.engsoft2.locacao.builder.LocacaoBuilder;
import wesley.engsoft2.locacao.modelo.Aluguel;
import wesley.engsoft2.locacao.modelo.Cliente;
import wesley.engsoft2.locacao.modelo.Locacao;
import wesley.engsoft2.locacao.repositorio.AluguelRepository;
import wesley.engsoft2.locacao.servico.AluguelService;
import wesley.engsoft2.locacao.servico.EmailService;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.mockito.Mockito.*;

public class AluguelServiceTest {

    private EmailService emailService;
    private AluguelService aluguelService;
    private AluguelRepository aluguelRepository;
    private Cliente cliente;

    @BeforeEach
    public void setup() {
        aluguelService = new AluguelService();

        cliente = ClienteBuilder.umCliente().constroi();

        Cliente cliente = mock(Cliente.class);

        aluguelRepository =  Mockito.mock(AluguelRepository.class);

        emailService = Mockito.mock(EmailService.class );

        aluguelService.setAluguelRepository(aluguelRepository );

        aluguelService.setEmailService(emailService );
    }

    @Test
    public void deveEnviarEmailParaUsuariosAtrasados() {

        Cliente cliente1 = mock(Cliente.class);
        Cliente cliente2 = mock(Cliente.class);
        Cliente cliente3 = mock(Cliente.class);
        Cliente cliente4 = mock(Cliente.class);

        Locacao locacao1 = LocacaoBuilder.umaLocacao().paraUmCliente(cliente1).constroi();
        Locacao locacao2 = LocacaoBuilder.umaLocacao().paraUmCliente(cliente2).constroi();
        Locacao locacao3 = LocacaoBuilder.umaLocacao().paraUmCliente(cliente3).constroi();

        List<Aluguel> alugueisEmAtraso = new ArrayList<>();

        Aluguel aluguel01 = AluguelBuilder.umAluguel().comLocacao(locacao1).comDataDeVencimento(LocalDate.of(2020,11,10))
                .comDataDePagamento(LocalDate.of(2020,12,30)).comValorpago(new BigDecimal(500)).constroi();

        Aluguel aluguel02 = AluguelBuilder.umAluguel().comLocacao(locacao2).comDataDeVencimento(LocalDate.of(2020,11,15))
                .comDataDePagamento(LocalDate.of(2020,11,30)).comValorpago(new BigDecimal(500)).constroi();

        Aluguel aluguel03 = AluguelBuilder.umAluguel().comLocacao(locacao3).comDataDeVencimento(LocalDate.of(2020,11,27))
                .comDataDePagamento(LocalDate.of(2020,11,29)).comValorpago(new BigDecimal(500)).constroi();

        alugueisEmAtraso.add(aluguel01);
        alugueisEmAtraso.add(aluguel02);
        alugueisEmAtraso.add(aluguel03);

        Mockito.when(aluguelRepository.recuperarAlugueisPagosEmAtraso()).thenReturn(alugueisEmAtraso);

        aluguelService.enviaEmailParaClientesQueNaoPagaramNaDataPrevista();

        Mockito.verify(emailService, times(1)).notifica(cliente1);
        Mockito.verify(emailService, times(1)).notifica(cliente2);
        Mockito.verify(emailService, times(1)).notifica(cliente3);
        Mockito.verify(emailService, Mockito.never()).notifica(cliente4);

        verifyNoMoreInteractions(emailService);

    }

    @Test
    public void verificaSeUmaExcecaoFoiLancada() {

        Cliente cliente1 = mock(Cliente.class);
        Cliente cliente2 = mock(Cliente.class);
        Cliente cliente3 = mock(Cliente.class);
        Cliente cliente4 = mock(Cliente.class);

        Locacao locacao1 = LocacaoBuilder.umaLocacao().paraUmCliente(cliente1).constroi();
        Locacao locacao2 = LocacaoBuilder.umaLocacao().paraUmCliente(cliente2).constroi();
        Locacao locacao3 = LocacaoBuilder.umaLocacao().paraUmCliente(cliente3).constroi();

        List<Aluguel> alugueisEmAtraso = new ArrayList<>();

        Aluguel aluguel01 = AluguelBuilder.umAluguel().comLocacao(locacao1).comDataDeVencimento(LocalDate.of(2020,11,10))
                .comDataDePagamento(LocalDate.of(2020,12,30)).comValorpago(new BigDecimal(500)).constroi();

        Aluguel aluguel02 = AluguelBuilder.umAluguel().comLocacao(locacao2).comDataDeVencimento(LocalDate.of(2020,11,15))
                .comDataDePagamento(LocalDate.of(2020,11,30)).comValorpago(new BigDecimal(500)).constroi();

        Aluguel aluguel03 = AluguelBuilder.umAluguel().comLocacao(locacao3).comDataDeVencimento(LocalDate.of(2020,11,27))
                .comDataDePagamento(LocalDate.of(2020,11,29)).comValorpago(new BigDecimal(500)).constroi();

        alugueisEmAtraso.add(aluguel01);
        alugueisEmAtraso.add(aluguel02);
        alugueisEmAtraso.add(aluguel03);

        Mockito.when(aluguelRepository.recuperarAlugueisPagosEmAtraso()).thenReturn(alugueisEmAtraso);

        aluguelService.enviaEmailParaClientesQueNaoPagaramNaDataPrevista();

        Mockito.when(emailService.notifica(cliente4)).thenThrow(new RuntimeException("Não foi possível enviar email") );
        Mockito.verify(emailService, times(1)).notifica(cliente1);
        Mockito.verify(emailService, times(1)).notifica(cliente2);
        Mockito.verify(emailService, times(1)).notifica(cliente3);

        verifyNoMoreInteractions(emailService);
    }
}
