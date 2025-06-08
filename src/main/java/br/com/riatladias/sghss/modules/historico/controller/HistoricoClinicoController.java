package br.com.riatladias.sghss.modules.historico.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.riatladias.sghss.modules.historico.dto.HistoricoClinicoRequestDTO;
import br.com.riatladias.sghss.modules.historico.useCase.HistoricoClinicoUseCase;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/paciente")
public class HistoricoClinicoController {

    @Autowired
    private HistoricoClinicoUseCase historicoClinicoUseCase;

    @GetMapping("/historico")
    @PreAuthorize("hasAnyRole('ADMIN', 'MEDICO', 'TECNICO')")
    public ResponseEntity<Object> obterHistorico(@Valid @RequestBody HistoricoClinicoRequestDTO dto) {
        try {
            var result = this.historicoClinicoUseCase.execute(dto);
            return ResponseEntity.ok().body(result);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
}
