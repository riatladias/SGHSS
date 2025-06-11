package br.com.riatladias.sghss.modules.prescricao.useCase;

import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.riatladias.sghss.exceptions.PacienteNotFoundException;
import br.com.riatladias.sghss.modules.paciente.repository.PacienteRepository;
import br.com.riatladias.sghss.modules.prescricao.dto.PrescricaoResponseDTO;
import br.com.riatladias.sghss.modules.prescricao.repository.PrescricaoRepository;

@Service
public class ListarPrescricaoUseCase {

    @Autowired
    private PrescricaoRepository prescricaoRepository;

    @Autowired
    private PacienteRepository pacienteRepository;

    public List<PrescricaoResponseDTO> execute(UUID pacienteId) {
        this.pacienteRepository.findById(pacienteId)
                .orElseThrow(() -> {
                    throw new PacienteNotFoundException();
                });

        var prescricoes = this.prescricaoRepository.findByPacienteId(pacienteId)
                .stream().map((prescricao) -> {
                    return new PrescricaoResponseDTO(
                            prescricao.getId(),
                            prescricao.getPaciente().getNome(),
                            prescricao.getProfissionalDeSaude().getNome(),
                            prescricao.getDataPrescricao(),
                            prescricao.getAnotacoes(),
                            prescricao.getMedicamentos(),
                            prescricao.isAssinadaDigitalmente());
                }).toList();

        return prescricoes;
    }
}
