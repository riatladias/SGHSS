package br.com.riatladias.sghss.modules.paciente.controllers;

import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.riatladias.sghss.modules.paciente.domain.Paciente;
import br.com.riatladias.sghss.modules.paciente.useCases.CriarPacienteUseCase;
import br.com.riatladias.sghss.modules.paciente.useCases.ListarPacientesUseCase;
import br.com.riatladias.sghss.modules.paciente.useCases.ObterPacienteUseCase;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/paciente")
public class PacienteController {

    @Autowired
    private CriarPacienteUseCase criarPacienteUseCase;

    @Autowired
    private ListarPacientesUseCase listarPacientesUseCase;

    @Autowired
    private ObterPacienteUseCase obterPacienteUseCase;

    @PostMapping("/")
    public ResponseEntity<Object> create(@Valid @RequestBody Paciente paciente) {
        try {
            var result = this.criarPacienteUseCase.execute(paciente);
            return ResponseEntity.ok().body(result);

        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @GetMapping("/listar")
    public ResponseEntity<List<Paciente>> listasPacientes() {
        var result = this.listarPacientesUseCase.execute();
        return ResponseEntity.ok().body(result);
    }

    @GetMapping("/obter")
    public ResponseEntity<Object> obterPaciente(@RequestBody UUID id) {
        try {
            var paciente = this.obterPacienteUseCase.execute(id);
            return ResponseEntity.ok().body(paciente);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }

    }

}
