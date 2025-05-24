package br.com.riatladias.sghss.modules.consulta.controller;

import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
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
import br.com.riatladias.sghss.modules.consulta.useCase.ListagemDeConsultaUseCase;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/consulta")
public class ConsultaController {

    @Autowired
    private CriarConsultaUseCase consultaUseCase;

    @Autowired
    private ListagemDeConsultaUseCase listagemDeConsultaUseCase;

    @Autowired
    private CancelarConsultaUseCase cancelarConsultaUseCase;

    @PostMapping("/")
    public ResponseEntity<Object> create(@Valid @RequestBody ConsultaRequestDTO consultaRequestDTO) {
        try {
            var result = this.consultaUseCase.execute(consultaRequestDTO);
            return ResponseEntity.ok().body(result);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @GetMapping("/listagem")
    public ResponseEntity<Object> listagemDasConsultas(@RequestBody UUID pacienteId) {
        try {
            var listagem = this.listagemDeConsultaUseCase.execute(pacienteId);
            return ResponseEntity.ok().body(listagem);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @PatchMapping("/cancelar")
    public ResponseEntity<Object> cancelarConsulta(@Valid @RequestBody CancelarConsultaRequestDTO dto) {
        try {
            var consulta = this.cancelarConsultaUseCase.execute(dto);
            return ResponseEntity.ok().body(consulta);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

}
