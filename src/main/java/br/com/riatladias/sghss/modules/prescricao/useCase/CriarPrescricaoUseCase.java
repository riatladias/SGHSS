package br.com.riatladias.sghss.modules.prescricao.useCase;

import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.riatladias.sghss.exceptions.PacienteNotFoundException;
import br.com.riatladias.sghss.modules.paciente.repository.PacienteRepository;
import br.com.riatladias.sghss.modules.prescricao.domain.Prescricao;
import br.com.riatladias.sghss.modules.prescricao.dto.PrescricaoRequestDTO;
import br.com.riatladias.sghss.modules.prescricao.repository.PrescricaoRepository;

@Service
public class CriarPrescricaoUseCase {

    @Autowired
    private PrescricaoRepository prescricaoRepository;

    @Autowired
    private PacienteRepository pacienteRepository;

    public Prescricao execute(PrescricaoRequestDTO dto, UUID profissionalId) {
        var paciente = this.pacienteRepository.findById(dto.pacienteId())
                .orElseThrow(() -> {
                    throw new PacienteNotFoundException();
                });

        var prescricao = Prescricao.builder()
                .pacienteId(paciente.getId())
                .profissionalId(profissionalId)
                .anotacoes(dto.anotacoes())
                .medicamentos(dto.medicamentos())
                .assinadaDigitalmente(false)
                .build();

        return this.prescricaoRepository.save(prescricao);

    }
}
