package br.com.riatladias.sghss.modules.consulta.dto;

import java.time.LocalDateTime;
import java.util.UUID;

import br.com.riatladias.sghss.modules.shared.domain.enums.StatusConsulta;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ConsultaResponseDTO {
    private String nomePaciente;
    private String nomeProfissional;
    private UUID pronturaId;
    private UUID agendaId;
    private StatusConsulta status;
    private String motivoDoCancelamento;
    private LocalDateTime dataDoCancelamento;
    private String observacoes;
}
