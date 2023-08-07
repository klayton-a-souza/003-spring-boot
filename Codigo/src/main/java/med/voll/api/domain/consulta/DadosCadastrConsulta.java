package med.voll.api.domain.consulta;

import med.voll.api.domain.medico.Medico;
import med.voll.api.domain.paciente.Paciente;

import java.time.LocalDateTime;

public record DadosCadastrConsulta(Long id, Medico medico, Paciente paciente, LocalDateTime data) {
}
