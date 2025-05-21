package br.com.riatladias.sghss.modules.consulta.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.riatladias.sghss.modules.consulta.dto.ConsultaRequestDTO;
import br.com.riatladias.sghss.modules.consulta.useCase.CriarConsultaUseCase;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/consulta")
public class ConsultaController {

    @Autowired
    private CriarConsultaUseCase criarConsultaUseCase;

    @PostMapping("/")
    public ResponseEntity<Object> create(@Valid @RequestBody ConsultaRequestDTO consultaRequestDTO) {
        try {
            var result = this.criarConsultaUseCase.execute(consultaRequestDTO);
            return ResponseEntity.ok().body(result);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

}
