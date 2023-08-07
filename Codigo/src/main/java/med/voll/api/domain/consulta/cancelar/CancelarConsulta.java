package med.voll.api.domain.consulta.cancelar;


import med.voll.api.domain.ValidacaoException;
import med.voll.api.domain.consulta.Consulta;
import med.voll.api.domain.consulta.ConsultaRepository;
import med.voll.api.domain.consulta.agendar.DadosDetalhamentoConsulta;
import med.voll.api.domain.consulta.validacoes.cancelar.ValidadorCancelamentoDeConsulta;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CancelarConsulta {

    @Autowired
    private List<ValidadorCancelamentoDeConsulta> validadores;

    @Autowired
    private ConsultaRepository consultaRepository;

    public DadosCancelamentoConsulta cancelar(DadosCancelamentoConsulta dados){

        if(!consultaRepository.existsById(dados.idConsulta())){
            throw new ValidacaoException("Consulta com esse id não foi encontrada no banco de dados");
        }

        validadores.forEach(v -> v.cancelar(dados));
        return new DadosCancelamentoConsulta(dados.idConsulta(), dados.motivo());

    }

}
