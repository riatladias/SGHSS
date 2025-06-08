package br.com.riatladias.sghss.modules.exame.useCase;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.riatladias.sghss.exceptions.ExameNotFoundException;
import br.com.riatladias.sghss.modules.exame.dto.ExamePutRequestDTO;
import br.com.riatladias.sghss.modules.exame.dto.ExameResponseDTO;
import br.com.riatladias.sghss.modules.exame.repository.ExameRepository;

@Service
public class AtualizarExameUseCase {

    @Autowired
    private ExameRepository exameRepository;

    public ExameResponseDTO execute(ExamePutRequestDTO dto) {
        var exame = this.exameRepository.findById(dto.getId())
                .orElseThrow(() -> {
                    throw new ExameNotFoundException();
                });
        
        exame.setDataHora(dto.getDataHora());
        exame.setResultado(dto.getResultado());

        this.exameRepository.save(exame);

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
