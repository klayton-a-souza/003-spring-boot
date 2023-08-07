package med.voll.api.domain.consulta;

import med.voll.api.domain.medico.Medico;
import med.voll.api.domain.medico.MedicoRepository;
import med.voll.api.domain.paciente.PacienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AgendaDeConsultas {

    @Autowired
    ConsultaRepository consultaRepository;

    @Autowired
    MedicoRepository medicoRepository;

    @Autowired
    PacienteRepository pacienteRepository;

    public void agendar(DadosAgendamentoConsulta dados){

        var medico = medicoRepository.getReferenceById(dados.idMedico());
        var paciente = pacienteRepository.getReferenceById(dados.idPaciente());


        var consulta = new Consulta(null,medico,paciente,dados.data());
        consultaRepository.save(consulta);
    }
}
