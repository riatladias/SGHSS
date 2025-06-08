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
public class ExamePutRequestDTO {
    @NotNull
    private UUID id;
    private UUID pacienteId;
    private String tipo;
    private LocalDateTime dataHora;
    private String resultado;

}
