package br.com.riatladias.sghss.modules.exame.useCase;

import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.riatladias.sghss.exceptions.ExameNotFoundException;
import br.com.riatladias.sghss.modules.exame.dto.ExameResponseDTO;
import br.com.riatladias.sghss.modules.exame.repository.ExameRepository;

@Service
public class ObterExameUseCase {
    @Autowired
    private ExameRepository exameRepository;

    public ExameResponseDTO execute(UUID exameId) {
        var exame = this.exameRepository.findById(exameId)
                .orElseThrow(() -> {
                    throw new ExameNotFoundException();
                });

        return ExameResponseDTO.builder()
                .id(exame.getId())
                .nomePaciente(exame.getPaciente().getNome())
                .nomeProfissional(exame.getProfissionalDeSaude().getNome())
                .tipo(exame.getTipo())
                .dataHora(exame.getDataHora())
                .resultado(exame.getResultado())
                .build();
    }
}
