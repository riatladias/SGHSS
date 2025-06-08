package br.com.riatladias.sghss.modules.prontuario.useCase;

import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.riatladias.sghss.exceptions.ProntuarioNotFoundException;
import br.com.riatladias.sghss.modules.prontuario.dto.ProntuarioPutRequestDTO;
import br.com.riatladias.sghss.modules.prontuario.dto.ProntuarioResponseDTO;
import br.com.riatladias.sghss.modules.prontuario.repository.ProntuarioRepository;

@Service
public class AtualizarProntuarioUseCase {

    @Autowired
    private ProntuarioRepository prontuarioRepository;

    public ProntuarioResponseDTO execute(ProntuarioPutRequestDTO dto, UUID profissionalId) {
        var prontuario = this.prontuarioRepository.findById(dto.getProntuarioId())
                .orElseThrow(() -> {
                    throw new ProntuarioNotFoundException();
                });

        prontuario.setAnotacoes(dto.getAnotacoes());
        prontuario.setDiagnostico(dto.getDiagnostico());
        prontuario.setProfissionalEvolucao(profissionalId);
        prontuario.setEvolucao(dto.getEvolucao());

        this.prontuarioRepository.save(prontuario);

        return ProntuarioResponseDTO.builder()
                .id(prontuario.getId())
                .nomePaciente(prontuario.getPaciente().getNome())
                .profissionalDeSaude(prontuario.getProfissionalDeSaude().getNome())
                .diagnostico(prontuario.getDiagnostico())
                .anotacoes(prontuario.getAnotacoes())
                .evolucao(dto.getEvolucao())
                .profissionalResponsavelEvolucao(profissionalId)
                .consultaId(prontuario.getConsulta().getId())
                .build();

    }
}
