package br.com.riatladias.sghss.modules.prontuario.controller;

import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.riatladias.sghss.modules.prontuario.dto.ProntuarioPutRequestDTO;
import br.com.riatladias.sghss.modules.prontuario.dto.ProntuarioResquestDTO;
import br.com.riatladias.sghss.modules.prontuario.useCase.AtualizarProntuarioUseCase;
import br.com.riatladias.sghss.modules.prontuario.useCase.CriarPronturarioUseCase;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/prontuario")
public class ProntuarioController {

    @Autowired
    private CriarPronturarioUseCase criarPronturarioUseCase;

    @Autowired
    private AtualizarProntuarioUseCase atualizarProntuarioUseCase;

    @PostMapping("/")
    @PreAuthorize("hasAnyRole('ADMIN', 'MEDICO', 'TECNICO')")
    public ResponseEntity<Object> create(@Valid @RequestBody ProntuarioResquestDTO dto) {
        try {
            var result = this.criarPronturarioUseCase.execute(dto);
            return ResponseEntity.ok().body(result);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @PutMapping("/")
    @PreAuthorize("hasAnyRole('ADMIN', 'MEDICO', 'TECNICO')")
    public ResponseEntity<Object> atulaizarProntuario(@Valid @RequestBody ProntuarioPutRequestDTO dto,
            HttpServletRequest request) {
        try {
            var profissionalId = request.getAttribute("profissional_id").toString();
            var result = this.atualizarProntuarioUseCase.execute(dto, UUID.fromString(profissionalId));
            return ResponseEntity.ok().body(result);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
}
