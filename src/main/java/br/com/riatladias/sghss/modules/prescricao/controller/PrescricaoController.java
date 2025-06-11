package br.com.riatladias.sghss.modules.prescricao.controller;

import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.riatladias.sghss.modules.prescricao.dto.PrescricaoRequestDTO;
import br.com.riatladias.sghss.modules.prescricao.useCase.CriarPrescricaoUseCase;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/prescricao")
public class PrescricaoController {
    @Autowired
    private CriarPrescricaoUseCase criarPrescricaoUseCase;

    @PostMapping("/")
    @PreAuthorize("hasAnyRole('ADMIN', 'MEDICO')")
    public ResponseEntity<Object> criarPrescricao(@Valid @RequestBody PrescricaoRequestDTO dto, HttpServletRequest request) {
        try {
            var profissionalId = request.getAttribute("profissional_id").toString();
            var result = this.criarPrescricaoUseCase.execute(dto, UUID.fromString(profissionalId));
            return ResponseEntity.ok().body(result);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
}
