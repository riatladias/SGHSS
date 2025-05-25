package br.com.riatladias.sghss.modules.prontuario.dto;

import java.util.UUID;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ProntuarioResponseDTO {
    private UUID id;
    private String nomePaciente;
    private String profissionalDeSaude;
    private String diagnostico;
    private String anotacoes;
    private UUID consultaId;
}
