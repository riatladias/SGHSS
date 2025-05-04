package br.com.riatladias.sghss.modules.paciente.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.riatladias.sghss.modules.paciente.PacienteEntity;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/paciente")
public class PacienteController {

    @PostMapping("/")
    public ResponseEntity<Object> create(@Valid @RequestBody PacienteEntity patientEntity) {
        return ResponseEntity.ok().body(patientEntity);
    }
}
