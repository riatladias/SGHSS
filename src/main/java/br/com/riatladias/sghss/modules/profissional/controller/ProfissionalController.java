package br.com.riatladias.sghss.modules.profissional.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.riatladias.sghss.modules.profissional.domain.ProfissionalDeSaude;
import br.com.riatladias.sghss.modules.profissional.useCase.CriarProfissionalUseCase;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/profissional")
public class ProfissionalController {

    @Autowired
    private CriarProfissionalUseCase criarProfissionalUseCase;

    @PostMapping("/")
    public ResponseEntity<Object> create(@Valid @RequestBody ProfissionalDeSaude profissionalDeSaude) {

        try {
            var result = this.criarProfissionalUseCase.execute(profissionalDeSaude);
            return ResponseEntity.ok().body(result);

        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
}
