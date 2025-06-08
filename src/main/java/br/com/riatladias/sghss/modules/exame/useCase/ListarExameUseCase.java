package br.com.riatladias.sghss.modules.exame.useCase;

import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.riatladias.sghss.exceptions.PacienteNotFoundException;
import br.com.riatladias.sghss.modules.exame.dto.ExameResponseDTO;
import br.com.riatladias.sghss.modules.exame.repository.ExameRepository;
import br.com.riatladias.sghss.modules.paciente.repository.PacienteRepository;

@Service
public class ListarExameUseCase {

    @Autowired
    private ExameRepository exameRepository;

    @Autowired
    private PacienteRepository pacienteRepository;

    public List<ExameResponseDTO> execute(UUID pacienteId) {

        this.pacienteRepository.findById(pacienteId)
                .orElseThrow(() -> {
                    throw new PacienteNotFoundException();
                });

        var exames = this.exameRepository.findByPacienteId(pacienteId)
                .stream().map(exame -> {
                    return ExameResponseDTO.builder()
                            .id(exame.getId())
                            .nomePaciente(exame.getPaciente().getNome())
                            .nomeProfissional(exame.getProfissionalDeSaude().getNome())
                            .tipo(exame.getTipo())
                            .dataHora(exame.getDataHora())
                            .resultado(exame.getResultado())
                            .build();
                }).toList();

        return exames;

    }
}
