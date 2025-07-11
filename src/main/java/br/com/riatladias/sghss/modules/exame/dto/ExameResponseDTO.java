package br.com.riatladias.sghss.modules.exame.dto;


import java.time.LocalDateTime;
import java.util.UUID;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ExameResponseDTO {
    private UUID id;
    private String nomePaciente;
    private String nomeProfissional;
    private String tipo;
    private LocalDateTime dataHora;
    private String resultado;

}
