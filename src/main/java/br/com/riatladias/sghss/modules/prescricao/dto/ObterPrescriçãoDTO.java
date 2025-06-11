package br.com.riatladias.sghss.modules.prescricao.dto;

import java.time.LocalDate;
import java.util.UUID;

import lombok.Data;

@Data
public class ObterPrescriçãoDTO {
    private UUID pacienteId;
    private UUID prescricaoId;
    private LocalDate data;
}
