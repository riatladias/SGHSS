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
public class CancelarConsultaResponseDTO {
    private UUID id;
    private LocalDateTime dataHora;
    private String observacoes;
    private StatusConsulta status;
    private String nomePaciente;
    private String profissionalDeSaude;
    private String motivoDoCancelamento;
    private LocalDateTime dataDoCancelamento;
}
