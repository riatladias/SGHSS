package br.com.riatladias.sghss.modules.historico.dto;

import java.util.List;
import java.util.UUID;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class HistoricoClinicoDTO {

    private UUID pacienteId;
    private String nomePaciente;

    private List<ConsultaDTO> consultas;
    private List<ExameDTO> exames;
    private List<ProntuarioDTO> prontuarios;
}