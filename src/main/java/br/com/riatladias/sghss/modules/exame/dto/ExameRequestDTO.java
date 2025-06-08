package br.com.riatladias.sghss.modules.exame.dto;

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
public class ExameRequestDTO {
    private UUID id;

    @NotNull
    private UUID pacienteId;

    @NotNull
    private String tipo;

    @NotNull
    private LocalDateTime dataHora;

    private String resultado;

}
