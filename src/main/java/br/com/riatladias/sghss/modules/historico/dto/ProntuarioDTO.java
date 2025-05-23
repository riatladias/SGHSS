package br.com.riatladias.sghss.modules.historico.dto;

import java.time.LocalDate;
import java.util.List;

import lombok.Data;

@Data
public class ProntuarioDTO {
    private LocalDate dataRegistro;
    private String anotacoes;
    private String profissional;
    private List<String> diagnosticos;
}
