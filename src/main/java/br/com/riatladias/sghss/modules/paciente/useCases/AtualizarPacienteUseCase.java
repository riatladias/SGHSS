package br.com.riatladias.sghss.modules.paciente.useCases;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.riatladias.sghss.exceptions.PacienteNotFoundException;
import br.com.riatladias.sghss.modules.paciente.dto.PacientePutRequestDTO;
import br.com.riatladias.sghss.modules.paciente.dto.PacienteResponseDTO;
import br.com.riatladias.sghss.modules.paciente.repository.PacienteRepository;

@Service
public class AtualizarPacienteUseCase {
    @Autowired
    private PacienteRepository pacienteRepository;

    public PacienteResponseDTO execute(PacientePutRequestDTO dto) {
        var paciente = this.pacienteRepository.findById(dto.pacienteId())
                .orElseThrow(() -> {
                    throw new PacienteNotFoundException();
                });

        paciente.setNome(dto.nome());
        paciente.setDataNascimento(dto.dataNascimento());
        paciente.setEmail(dto.email());
        paciente.setEndereco(dto.endereco());

        this.pacienteRepository.save(paciente);

        return PacienteResponseDTO.builder()
                .pacienteId(dto.pacienteId())
                .nome(dto.nome())
                .dataNascimento(dto.dataNascimento())
                .email(dto.email())
                .endereco(dto.endereco())
                .build();
    }
}
