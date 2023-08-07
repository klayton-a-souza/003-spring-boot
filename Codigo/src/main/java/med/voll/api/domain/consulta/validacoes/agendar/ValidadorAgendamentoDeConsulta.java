package med.voll.api.domain.consulta.validacoes.agendar;

import med.voll.api.domain.consulta.agendar.DadosAgendamentoConsulta;

public interface ValidadorAgendamentoDeConsulta {

    void validar(DadosAgendamentoConsulta dados);
}
