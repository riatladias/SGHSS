package br.com.riatladias.sghss.modules.agenda.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.riatladias.sghss.modules.agenda.dto.AgendaRequestDTO;
import br.com.riatladias.sghss.modules.agenda.useCase.CriarAgendaUseCase;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/agenda")
public class AgendaController {

    @Autowired
    private CriarAgendaUseCase criarAgendaUseCase;

    @PostMapping("/criar")
    public ResponseEntity<Object> gerarAgenda(@Valid @RequestBody AgendaRequestDTO dto) {
        try {
            var result = criarAgendaUseCase.execute(dto);
            return ResponseEntity.ok().body(result);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    // @GetMapping("/disponiveis")
    // public ResponseEntity<List<AgendaMedica>> listarDisponiveis(
    // @RequestParam Long profissionalId,
    // @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate data
    // ) {
    // List<AgendaMedica> disponiveis =
    // agendaService.listarDisponiveis(profissionalId, data);
    // return ResponseEntity.ok(disponiveis);
    // }
}
