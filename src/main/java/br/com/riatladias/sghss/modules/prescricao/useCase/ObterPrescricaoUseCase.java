package br.com.riatladias.sghss.modules.prescricao.useCase;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.riatladias.sghss.exceptions.PacienteNotFoundException;
import br.com.riatladias.sghss.modules.paciente.repository.PacienteRepository;
import br.com.riatladias.sghss.modules.prescricao.dto.ObterPrescriçãoDTO;
import br.com.riatladias.sghss.modules.prescricao.dto.PrescricaoResponseDTO;
import br.com.riatladias.sghss.modules.prescricao.repository.PrescricaoRepository;

@Service
public class ObterPrescricaoUseCase {
    @Autowired
    private PrescricaoRepository prescricaoRepository;

    @Autowired
    private PacienteRepository pacienteRepository;

    public PrescricaoResponseDTO execute(ObterPrescriçãoDTO dto) {
        this.pacienteRepository.findById(dto.getPacienteId())
                .orElseThrow(() -> {
                    throw new PacienteNotFoundException();
                });

        var prescricao = this.prescricaoRepository.findById(dto.getPrescricaoId())
                .orElseThrow(() -> {
                    throw new RuntimeException("Prescrição não encontrada");
                });

        return new PrescricaoResponseDTO(
                prescricao.getId(),
                prescricao.getPaciente().getNome(),
                prescricao.getProfissionalDeSaude().getNome(),
                prescricao.getDataPrescricao(),
                prescricao.getAnotacoes(),
                prescricao.getMedicamentos(),
                prescricao.isAssinadaDigitalmente());

    }
}
