package br.com.riatladias.sghss.modules.prontuario.dto;

import java.util.UUID;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ProntuarioResquestDTO {
    @NotNull
    private UUID pacienteId;

    @NotNull
    private UUID profissionalId;
    @NotNull

    private UUID consultaId;

    @NotNull
    private String anotacoes;

    @NotNull
    private String diagnostico;

}
