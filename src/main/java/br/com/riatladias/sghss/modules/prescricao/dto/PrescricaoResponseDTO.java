package br.com.riatladias.sghss.modules.prescricao.dto;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

import br.com.riatladias.sghss.modules.prescricao.domain.Medicamento;

public record PrescricaoResponseDTO(
        UUID id,
        String nomePaciente,
        String nomeProfissional,
        LocalDate dataPrescricao,
        String anotacoes,
        List<Medicamento> medicamentos,
        boolean assinadaDigitalmente

) {

}
