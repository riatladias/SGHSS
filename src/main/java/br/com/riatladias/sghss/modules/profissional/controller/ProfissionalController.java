package br.com.riatladias.sghss.modules.profissional.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.riatladias.sghss.modules.profissional.domain.ProfissionalDeSaude;
import br.com.riatladias.sghss.modules.profissional.dto.ProfissionalDTO;
import br.com.riatladias.sghss.modules.profissional.useCase.CriarProfissionalUseCase;
import br.com.riatladias.sghss.modules.profissional.useCase.EditarProfissionalUseCase;
import br.com.riatladias.sghss.modules.profissional.useCase.ListarProfissionalUseCase;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/profissional")
public class ProfissionalController {

    @Autowired
    private CriarProfissionalUseCase criarProfissionalUseCase;

    @Autowired
    private ListarProfissionalUseCase listarProfissionalUseCase;

    @Autowired
    private EditarProfissionalUseCase editarProfissionalUseCase;

    @PostMapping("/")
    // @PreAuthorize("hasAnyRole('ADMIN')")
    public ResponseEntity<Object> create(@Valid @RequestBody ProfissionalDeSaude profissionalDeSaude) {
        try {
            var result = this.criarProfissionalUseCase.execute(profissionalDeSaude);
            return ResponseEntity.ok().body(result);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @GetMapping("/listar")
    @PreAuthorize("hasAnyRole('ADMIN', 'RECEPCIONISTA', 'MEDICO')")
    public ResponseEntity<List<ProfissionalDeSaude>> listarProfissional() {
        var result = this.listarProfissionalUseCase.execute();
        return ResponseEntity.ok().body(result);
    }

    @PutMapping("/editar")
    public ResponseEntity<Object> editarProfissional(@RequestBody ProfissionalDTO dto) {
        try {
            var result = this.editarProfissionalUseCase.execute(dto);
            return ResponseEntity.ok().body(result);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

}
