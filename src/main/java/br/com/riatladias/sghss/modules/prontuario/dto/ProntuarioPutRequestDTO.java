package br.com.riatladias.sghss.modules.prontuario.dto;

import java.util.UUID;

import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class ProntuarioPutRequestDTO {
    @NotNull
    private UUID prontuarioId;
    private String anotacoes;
    private String diagnostico;
    private String evolucao;
}
