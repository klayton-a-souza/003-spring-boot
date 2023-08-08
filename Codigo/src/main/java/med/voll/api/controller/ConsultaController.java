package med.voll.api.controller;

import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.validation.Valid;
import med.voll.api.domain.consulta.ConsultaRepository;
import med.voll.api.domain.consulta.agendar.AgendaDeConsultas;
import med.voll.api.domain.consulta.agendar.DadosAgendamentoConsulta;
import med.voll.api.domain.consulta.cancelar.CancelarConsulta;
import med.voll.api.domain.consulta.cancelar.DadosCancelamentoConsulta;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("consultas")
@SecurityRequirement(name = "bearer-key")
public class ConsultaController {


    @Autowired
    private ConsultaRepository consultaRepository;

    @Autowired
    private AgendaDeConsultas agenda;

    @Autowired
    private CancelarConsulta cancelar;

    @PostMapping
    @Transactional
    public ResponseEntity agendar(@RequestBody @Valid DadosAgendamentoConsulta dados){
        var dto = agenda.agendar(dados);
        return ResponseEntity.ok(dto);

    }

    @DeleteMapping
    @Transactional
    public ResponseEntity cancelar(@RequestBody @Valid DadosCancelamentoConsulta dados) {
        var dto = cancelar.cancelar(dados);
        var consulta = consultaRepository.getReferenceById(dados.idConsulta());
        consulta.excluir();
        return ResponseEntity.noContent().build();
    }





}
