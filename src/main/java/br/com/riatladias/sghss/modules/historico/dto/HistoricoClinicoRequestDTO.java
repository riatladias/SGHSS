package br.com.riatladias.sghss.modules.historico.dto;

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
public class HistoricoClinicoRequestDTO {

    @NotNull
    private UUID pacienteId;
}
