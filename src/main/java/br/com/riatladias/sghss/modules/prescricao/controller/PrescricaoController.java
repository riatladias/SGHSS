package br.com.riatladias.sghss.modules.prescricao.controller;

import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.riatladias.sghss.modules.prescricao.dto.ObterPrescriçãoDTO;
import br.com.riatladias.sghss.modules.prescricao.dto.PrescricaoRequestDTO;
import br.com.riatladias.sghss.modules.prescricao.useCase.CriarPrescricaoUseCase;
import br.com.riatladias.sghss.modules.prescricao.useCase.ListarPrescricaoUseCase;
import br.com.riatladias.sghss.modules.prescricao.useCase.ObterPrescricaoUseCase;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/prescricao")
public class PrescricaoController {
    @Autowired
    private CriarPrescricaoUseCase criarPrescricaoUseCase;

    @Autowired
    private ListarPrescricaoUseCase listarPrescricaoUseCase;

    @Autowired
    private ObterPrescricaoUseCase obterPrescricaoUseCase;

    @PostMapping("/")
    @PreAuthorize("hasAnyRole('ADMIN', 'MEDICO')")
    public ResponseEntity<Object> criarPrescricao(@Valid @RequestBody PrescricaoRequestDTO dto,
            HttpServletRequest request) {
        try {
            var profissionalId = request.getAttribute("profissional_id").toString();
            var result = this.criarPrescricaoUseCase.execute(dto, UUID.fromString(profissionalId));
            return ResponseEntity.ok().body(result);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @GetMapping("/listar")
    @PreAuthorize("hasAnyRole('ADMIN', 'MEDICO', 'ENFERMEIRO')")
    public ResponseEntity<Object> listarPrescricoes(@RequestBody UUID id) {
        try {
            var result = this.listarPrescricaoUseCase.execute(id);
            return ResponseEntity.ok().body(result);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @GetMapping("/")
    @PreAuthorize("hasAnyRole('ADMIN', 'MEDICO', 'ENFERMEIRO')")
    public ResponseEntity<Object> obterPrescricoes(@RequestBody ObterPrescriçãoDTO dto) {
        try {
            var result = this.obterPrescricaoUseCase.execute(dto);
            return ResponseEntity.ok().body(result);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
}
