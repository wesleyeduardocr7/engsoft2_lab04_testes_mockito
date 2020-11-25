package wesley.engsoft2.locacao.servico;
import wesley.engsoft2.locacao.modelo.Aluguel;
import wesley.engsoft2.locacao.repositorio.AluguelRepository;
import java.util.List;

public class AluguelService {

    private AluguelRepository aluguelRepository;
    private EmailService emailService;

    public void enviaEmailParaClientesQueNaoPagaramNaDataPrevista() {
        List<Aluguel> alugueisAtrasados = aluguelRepository.recuperarAlugueisPagosEmAtraso();

        alugueisAtrasados.forEach(aluguel ->
                emailService.notifica(aluguel.getLocacao().getCliente()
                ));
    }

    public void setAluguelRepository(AluguelRepository aluguelRepository) {
        this.aluguelRepository = aluguelRepository;
    }

    public void setEmailService(EmailService emailService) {
        this.emailService = emailService;
    }
}
