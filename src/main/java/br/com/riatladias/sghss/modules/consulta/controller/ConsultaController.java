package br.com.riatladias.sghss.modules.consulta.controller;

import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.riatladias.sghss.modules.consulta.dto.CancelarConsultaRequestDTO;
import br.com.riatladias.sghss.modules.consulta.dto.ConsultaRequestDTO;
import br.com.riatladias.sghss.modules.consulta.useCase.CancelarConsultaUseCase;
import br.com.riatladias.sghss.modules.consulta.useCase.CriarConsultaUseCase;
import br.com.riatladias.sghss.modules.consulta.useCase.ListarConsultaUseCase;
import br.com.riatladias.sghss.modules.consulta.useCase.ObterConsultaUseCase;
import br.com.riatladias.sghss.modules.consulta.useCase.RealizarConsultaUseCase;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/consulta")
public class ConsultaController {

    @Autowired
    private CriarConsultaUseCase criarConsultaUseCase;

    @Autowired
    private ObterConsultaUseCase obterConsultaUseCase;

    @Autowired
    private ListarConsultaUseCase listagemDeConsultaUseCase;

    @Autowired
    private CancelarConsultaUseCase cancelarConsultaUseCase;

    @Autowired
    private RealizarConsultaUseCase realizarConsultaUseCase;

    @PostMapping("/")
    @PreAuthorize("hasAnyRole('ADMIN', 'RECEPCIONISTA')")
    public ResponseEntity<Object> criarConsulta(@Valid @RequestBody ConsultaRequestDTO consultaRequestDTO) {
        try {
            var result = this.criarConsultaUseCase.execute(consultaRequestDTO);
            return ResponseEntity.ok().body(result);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @GetMapping("/")
    @PreAuthorize("hasAnyRole('ADMIN', 'RECEPCIONISTA', 'MEDICO')")
    public ResponseEntity<Object> obterConsulta(@Valid @RequestBody UUID id) {
        try {
            var result = this.obterConsultaUseCase.execute(id);
            return ResponseEntity.ok().body(result);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @GetMapping("/listar")
    @PreAuthorize("hasAnyRole('ADMIN', 'RECEPCIONISTA', 'MEDICO')")
    public ResponseEntity<Object> listarConsultas(@RequestBody UUID pacienteId) {
        try {
            var listagem = this.listagemDeConsultaUseCase.execute(pacienteId);
            return ResponseEntity.ok().body(listagem);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @PatchMapping("/cancelar")
    @PreAuthorize("hasAnyRole('ADMIN', 'RECEPCIONISTA', 'MEDICO')")
    public ResponseEntity<Object> cancelarConsulta(@Valid @RequestBody CancelarConsultaRequestDTO dto) {
        try {
            var consulta = this.cancelarConsultaUseCase.execute(dto);
            return ResponseEntity.ok().body(consulta);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @PostMapping("/realizar")
    @PreAuthorize("hasAnyRole('ADMIN', 'MEDICO')")
    public ResponseEntity<Object> realizarConsulta(@RequestBody UUID id) {
        try {
            var result = this.realizarConsultaUseCase.execute(id);
            return ResponseEntity.ok().body(result);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

}
