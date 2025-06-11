package br.com.riatladias.sghss.modules.prescricao.dto;

import java.util.List;
import java.util.UUID;

import br.com.riatladias.sghss.modules.prescricao.domain.Medicamento;

public record PrescricaoRequestDTO(
        UUID pacienteId,
        List<Medicamento> medicamentos,
        String anotacoes) {
}