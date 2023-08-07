package med.voll.api.domain.consulta.validacoes.cancelar;

import med.voll.api.domain.ValidacaoException;
import med.voll.api.domain.consulta.cancelar.DadosCancelamentoConsulta;
import org.springframework.stereotype.Component;

@Component
public class ValidadorDeCampoMotivoEstaPreenchido implements ValidadorCancelamentoDeConsulta{

    public void cancelar(DadosCancelamentoConsulta dados){
        if(dados.motivo() == null){
            throw new ValidacaoException("Campo motivo não pode esta vazio");
        }
    }
}
