package br.com.riatladias.sghss.modules.consulta.dto;

import java.time.LocalDateTime;
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
public class ConsultaRequestDTO {
    @NotNull
    private UUID pacienteId;

    @NotNull
    private UUID profissionalId;

    @NotNull
    private LocalDateTime dataHora;

    private String observacoes;
}
