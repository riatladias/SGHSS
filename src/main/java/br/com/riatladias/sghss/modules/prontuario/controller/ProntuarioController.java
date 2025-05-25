package br.com.riatladias.sghss.modules.prontuario.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.riatladias.sghss.modules.prontuario.dto.ProntuarioPutRequestDTO;
import br.com.riatladias.sghss.modules.prontuario.dto.ProntuarioResquestDTO;
import br.com.riatladias.sghss.modules.prontuario.useCase.AtualizarProntuarioUseCase;
import br.com.riatladias.sghss.modules.prontuario.useCase.CriarPronturarioUseCase;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/prontuario")
public class ProntuarioController {

    @Autowired
    private CriarPronturarioUseCase criarPronturarioUseCase;

    @Autowired
    private AtualizarProntuarioUseCase atualizarProntuarioUseCase;

    @PostMapping("/")
    public ResponseEntity<Object> create(@Valid @RequestBody ProntuarioResquestDTO dto) {

        try {
            var result = this.criarPronturarioUseCase.execute(dto);
            return ResponseEntity.ok().body(result);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @PutMapping("/")
    public ResponseEntity<Object> atulaizarProntuario(@Valid @RequestBody ProntuarioPutRequestDTO dto) {

        try {
            var result = this.atualizarProntuarioUseCase.execute(dto);
            return ResponseEntity.ok().body(result);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
}
