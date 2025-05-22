package br.com.riatladias.sghss.modules.prontuario.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.riatladias.sghss.modules.prontuario.dto.ProntuarioResquestDTO;
import br.com.riatladias.sghss.modules.prontuario.useCase.CriarPronturarioUseCase;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/prontuario")
public class ProntuarioController {

    @Autowired
    private CriarPronturarioUseCase criarPronturarioUseCase;

    @PostMapping("/")
    public ResponseEntity<Object> create(@Valid @RequestBody ProntuarioResquestDTO prontuarioResquestDTO) {

        try {
            var result = this.criarPronturarioUseCase.execute(prontuarioResquestDTO);
            return ResponseEntity.ok().body(result);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }

    }
}
