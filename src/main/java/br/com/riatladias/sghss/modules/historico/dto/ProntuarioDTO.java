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
public class ProntuarioDTO {
    private LocalDateTime createdAt;
    private String anotacoes;
    private String profissional;
    private String diagnosticos;
    
}
