package br.com.riatladias.sghss.modules.agenda.dto;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.UUID;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class AgendaRequestDTO {

    private UUID profissionalId;

    private LocalDate data;

    private LocalTime horaInicio;

    private LocalTime horaFim;

    private Long duracaoMinutos;
}
