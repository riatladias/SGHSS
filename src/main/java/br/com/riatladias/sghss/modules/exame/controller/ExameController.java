package br.com.riatladias.sghss.modules.exame.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.riatladias.sghss.modules.exame.dto.ExameRequestDTO;
import br.com.riatladias.sghss.modules.exame.useCase.ExameUseCase;
import jakarta.validation.Valid;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@RestController
@RequestMapping("/exame")
public class ExameController {

    @Autowired
    private ExameUseCase criarExameUseCase;

    @PostMapping("/")
    public ResponseEntity<Object> create(@Valid @RequestBody ExameRequestDTO dto) {
        try {
            var result = this.criarExameUseCase.execute(dto);
            return ResponseEntity.ok().body(result);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

}
