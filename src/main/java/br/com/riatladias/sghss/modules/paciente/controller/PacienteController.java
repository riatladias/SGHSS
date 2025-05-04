package br.com.riatladias.sghss.modules.paciente.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.riatladias.sghss.modules.paciente.PacienteEntity;
import br.com.riatladias.sghss.modules.paciente.useCases.CriarPacienteUseCase;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/paciente")
public class PacienteController {

    @Autowired
    private CriarPacienteUseCase criarPacienteUseCase;

    @PostMapping("/")
    public ResponseEntity<Object> create(@Valid @RequestBody PacienteEntity patientEntity) {
        try {
            var result = this.criarPacienteUseCase.execute(patientEntity);
            return ResponseEntity.ok().body(result);

        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
}
