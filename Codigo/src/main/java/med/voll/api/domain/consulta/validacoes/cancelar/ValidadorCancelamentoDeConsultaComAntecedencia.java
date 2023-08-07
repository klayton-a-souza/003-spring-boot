package med.voll.api.domain.consulta.validacoes.cancelar;

import med.voll.api.domain.ValidacaoException;
import med.voll.api.domain.consulta.ConsultaRepository;
import med.voll.api.domain.consulta.cancelar.DadosCancelamentoConsulta;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.Duration;
import java.time.LocalDateTime;

@Component
public class ValidadorCancelamentoDeConsultaComAntecedencia implements ValidadorCancelamentoDeConsulta{

    @Autowired
    private ConsultaRepository repository;

    public void cancelar(DadosCancelamentoConsulta dados){

        var consulta = repository.getReferenceById(dados.idConsulta());

        var dataConsulta = consulta.getData();

        var dataAgora = LocalDateTime.now();

        var diferencaEmHoras = Duration.between(dataAgora, dataConsulta).toHours();


        if(diferencaEmHoras < 24){
            throw new ValidacaoException("Uma consulta somente poderá ser cancelada com antecedência mínima de 24 horas.");
        }

    }
}
