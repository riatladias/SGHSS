package br.com.riatladias.sghss.modules.historico.dto;

import java.time.LocalDate;

import lombok.Data;

@Data
public class ExameDTO {
    
    private String tipo;
    private LocalDate dataHora;
    private String resultado;
}
