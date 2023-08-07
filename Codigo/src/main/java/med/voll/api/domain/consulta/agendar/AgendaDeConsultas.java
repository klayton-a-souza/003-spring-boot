package med.voll.api.domain.consulta.agendar;

import med.voll.api.domain.ValidacaoException;
import med.voll.api.domain.consulta.Consulta;
import med.voll.api.domain.consulta.ConsultaRepository;
import med.voll.api.domain.consulta.DadosCadastrConsulta;
import med.voll.api.domain.consulta.validacoes.agendar.ValidadorAgendamentoDeConsulta;
import med.voll.api.domain.medico.Medico;
import med.voll.api.domain.medico.MedicoRepository;
import med.voll.api.domain.paciente.PacienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AgendaDeConsultas {

    @Autowired
    ConsultaRepository consultaRepository;

    @Autowired
    MedicoRepository medicoRepository;

    @Autowired
    PacienteRepository pacienteRepository;

    @Autowired
    private List<ValidadorAgendamentoDeConsulta> validadores;

    public DadosDetalhamentoConsulta agendar(DadosAgendamentoConsulta dados){

        if(!pacienteRepository.existsById(dados.idPaciente())){
            throw new ValidacaoException("Id do paciente informado não existe");
        }

        if(dados.idMedico() != null && !medicoRepository.existsById(dados.idMedico())){
            throw new ValidacaoException("Id do médico informado não existe");
        }

        validadores.forEach(v -> v.validar(dados));

        var medico = escolherMedico(dados);
        if(medico == null){
            throw new ValidacaoException("Não existe médico disponível nessa data");
        }
        var paciente = pacienteRepository.getReferenceById(dados.idPaciente());

        var novaConsulta = new DadosCadastrConsulta(null,medico,paciente,dados.data());

        var consulta = new Consulta(novaConsulta);
        consultaRepository.save(consulta);

        return new DadosDetalhamentoConsulta(consulta);
    }

    private Medico escolherMedico(DadosAgendamentoConsulta dados) {
        if(dados.idMedico() != null){
            return  medicoRepository.getReferenceById(dados.idMedico());
        }

        if(dados.especialidade() == null){
            throw new ValidacaoException("Especialidade é obrigatoria quando médico não for escolhido!");
        }

        return medicoRepository.escolherMedicoAleatorioLivreNaData(dados.especialidade(),dados.data());
    }
}
