package br.com.riatladias.sghss.modules.historico.dto;

import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ExameDTO {

    private String tipo;
    private LocalDateTime dataHora;
    private String resultado;
}
