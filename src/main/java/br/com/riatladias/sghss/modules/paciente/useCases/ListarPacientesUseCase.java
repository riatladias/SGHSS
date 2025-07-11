package br.com.riatladias.sghss.modules.paciente.useCases;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.riatladias.sghss.modules.paciente.dto.PacienteResponseDTO;
import br.com.riatladias.sghss.modules.paciente.repository.PacienteRepository;

@Service
public class ListarPacientesUseCase {

    @Autowired
    private PacienteRepository pacienteRepository;

    public List<PacienteResponseDTO> execute() {
        var pacientes = this.pacienteRepository.findAll();

        return pacientes.stream().map(paciente -> {
            return PacienteResponseDTO.builder()
                    .pacienteId(paciente.getId())
                    .nome(paciente.getNome())
                    .dataNascimento(paciente.getDataNascimento())
                    .email(paciente.getEmail())
                    .build();
        }).toList();
    }
}
