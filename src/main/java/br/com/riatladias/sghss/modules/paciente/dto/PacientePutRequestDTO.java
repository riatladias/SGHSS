package br.com.riatladias.sghss.modules.paciente.dto;

import java.time.LocalDate;
import java.util.UUID;

import br.com.riatladias.sghss.modules.shared.domain.Endereco;

public record PacientePutRequestDTO(
                UUID pacienteId,
                String nome,
                LocalDate dataNascimento,
                String email,
                Endereco endereco) {

}
