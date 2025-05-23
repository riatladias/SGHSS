package br.com.riatladias.sghss.modules.historico.dto;

import java.time.LocalDateTime;

import br.com.riatladias.sghss.modules.shared.domain.enums.StatusConsulta;
import lombok.Data;

@Data
public class ConsultaDTO {
    private Long id;
    private LocalDateTime dataHora;
    private String profissional;
    private String tipoConsulta;
    private StatusConsulta status;
}
