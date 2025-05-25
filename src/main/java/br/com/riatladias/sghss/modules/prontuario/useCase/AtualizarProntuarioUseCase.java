package br.com.riatladias.sghss.modules.prontuario.useCase;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.riatladias.sghss.exceptions.ProfissionalNotFoundException;
import br.com.riatladias.sghss.exceptions.ProntuarioNotFoundException;
import br.com.riatladias.sghss.modules.profissional.repository.ProfissionalRepository;
import br.com.riatladias.sghss.modules.prontuario.dto.ProntuarioPutRequestDTO;
import br.com.riatladias.sghss.modules.prontuario.dto.ProntuarioResponseDTO;
import br.com.riatladias.sghss.modules.prontuario.repository.ProntuarioRepository;

@Service
public class AtualizarProntuarioUseCase {

    @Autowired
    private ProfissionalRepository profissionalRepository;

    @Autowired
    private ProntuarioRepository prontuarioRepository;

    public ProntuarioResponseDTO execute(ProntuarioPutRequestDTO dto) {
        var prontuario = this.prontuarioRepository.findById(dto.getProntuarioId())
                .orElseThrow(() -> {
                    throw new ProntuarioNotFoundException();
                });

        var profissional = this.profissionalRepository.findById(dto.getProfissionalId())
                .orElseThrow(() -> {
                    throw new ProfissionalNotFoundException();
                });

        prontuario.setProfissionalId(dto.getProfissionalId());
        prontuario.setProfissionalDeSaude(profissional);
        prontuario.setAnotacoes(dto.getAnotacoes());
        prontuario.setDiagnostico(dto.getDiagnostico());
        this.prontuarioRepository.save(prontuario);

        return ProntuarioResponseDTO.builder()
                .id(prontuario.getId())
                .nomePaciente(prontuario.getPaciente().getNome())
                .profissionalDeSaude(profissional.getNome())
                .diagnostico(prontuario.getDiagnostico())
                .anotacoes(prontuario.getAnotacoes())
                .consultaId(prontuario.getConsulta().getId())
                .build();

    }
}
