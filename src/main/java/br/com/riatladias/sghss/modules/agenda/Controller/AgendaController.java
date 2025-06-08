package br.com.riatladias.sghss.modules.agenda.Controller;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.riatladias.sghss.modules.agenda.domain.AgendaMedica;
import br.com.riatladias.sghss.modules.agenda.dto.AgendaRequestDTO;
import br.com.riatladias.sghss.modules.agenda.useCase.CriarAgendaUseCase;
import br.com.riatladias.sghss.modules.agenda.useCase.ListarAgendaMedica;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/agenda")
public class AgendaController {

    @Autowired
    private CriarAgendaUseCase criarAgendaUseCase;

    @Autowired
    private ListarAgendaMedica listarAgendaMedica;

    @PostMapping("/criar")
    @PreAuthorize("hasAnyRole('ADMIN', 'MEDICO')")
    public ResponseEntity<Object> criarAgenda(@Valid @RequestBody AgendaRequestDTO dto, HttpServletRequest request) {
        try {
            var profissionalId = request.getAttribute("profissional_id");
            var result = criarAgendaUseCase.execute(dto, UUID.fromString(profissionalId.toString()));
            return ResponseEntity.ok().body(result);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @GetMapping("/disponiveis")
    @PreAuthorize("hasAnyRole('ADMIN', 'RECEPCIONISTA', 'MEDICO')")
    public ResponseEntity<Optional<List<AgendaMedica>>> listarDisponiveis(@Valid @RequestBody AgendaRequestDTO dto,
            HttpServletRequest request) {
        var profissionalId = request.getAttribute("profissional_id").toString();
        var disponiveis = this.listarAgendaMedica.execute(dto, UUID.fromString(profissionalId.toString()));
        return ResponseEntity.ok().body(disponiveis);
    }
}
