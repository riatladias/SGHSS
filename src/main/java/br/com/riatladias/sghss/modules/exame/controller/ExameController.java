package br.com.riatladias.sghss.modules.exame.controller;

import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.riatladias.sghss.modules.exame.dto.ExamePutRequestDTO;
import br.com.riatladias.sghss.modules.exame.dto.ExameRequestDTO;
import br.com.riatladias.sghss.modules.exame.useCase.AtualizarExameUseCase;
import br.com.riatladias.sghss.modules.exame.useCase.CriarExameUseCase;
import br.com.riatladias.sghss.modules.exame.useCase.ListarExameUseCase;
import br.com.riatladias.sghss.modules.exame.useCase.ObterExameUseCase;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/exame")
public class ExameController {

    @Autowired
    private CriarExameUseCase criarExameUseCase;

    @Autowired
    private ListarExameUseCase listarExameUseCase;

    @Autowired
    private AtualizarExameUseCase atualizarExameUseCase;

    @Autowired
    private ObterExameUseCase obterExameUseCase;

    @PostMapping("/")
    @PreAuthorize("hasAnyRole('ADMIN', 'MEDICO', 'TECNICO')")
    public ResponseEntity<Object> create(@Valid @RequestBody ExameRequestDTO dto, HttpServletRequest request) {
        try {
            var profissionalId = request.getAttribute("profissional_id");
            var result = this.criarExameUseCase.execute(dto, UUID.fromString(profissionalId.toString()));
            return ResponseEntity.ok().body(result);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @GetMapping("/listar")
    @PreAuthorize("hasAnyRole('ADMIN', 'MEDICO', 'TECNICO')")
    public ResponseEntity<Object> listarExames(@RequestBody UUID id) {
        try {
            var result = this.listarExameUseCase.execute(id);
            return ResponseEntity.ok().body(result);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @GetMapping("/obter")
    @PreAuthorize("hasAnyRole('ADMIN', 'MEDICO', 'TECNICO')")
    public ResponseEntity<Object> obterExame(@RequestBody UUID id) {
        try {
            var result = this.obterExameUseCase.execute(id);
            return ResponseEntity.ok().body(result);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @PutMapping("/atualizar")
    @PreAuthorize("hasAnyRole('ADMIN', 'TECNICO')")
    public ResponseEntity<Object> atualizarExame(@Valid @RequestBody ExamePutRequestDTO dto) {
        try {
            var result = this.atualizarExameUseCase.execute(dto);
            return ResponseEntity.ok().body(result);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

}
