package br.com.riatladias.sghss.modules.historico.dto;

import java.util.List;

import lombok.Data;

@Data
public class HistoricoClinicoDTO {

    private Long pacienteId;
    private String nomePaciente;

    private List<ConsultaDTO> consultas;
    private List<ExameDTO> exames;
    private List<ProntuarioDTO> prontuarios;
}