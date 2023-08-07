package med.voll.api.domain.consulta.validacoes.cancelar;

import med.voll.api.domain.consulta.cancelar.DadosCancelamentoConsulta;

public interface ValidadorCancelamentoDeConsulta {

    void cancelar(DadosCancelamentoConsulta dados);
}
