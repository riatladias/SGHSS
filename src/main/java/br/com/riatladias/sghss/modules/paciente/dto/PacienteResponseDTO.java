package br.com.riatladias.sghss.modules.paciente.dto;

import java.time.LocalDate;
import java.util.UUID;

import br.com.riatladias.sghss.modules.shared.domain.Endereco;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PacienteResponseDTO {
    private UUID pacienteId;
    private String nome;
    private LocalDate dataNascimento;
    private String email;
    private Endereco endereco;
}
