package br.com.riatladias.sghss.modules.exame.useCase;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.riatladias.sghss.exceptions.PacienteNotFoundException;
import br.com.riatladias.sghss.exceptions.ProfissionalNotFoundException;
import br.com.riatladias.sghss.modules.exame.domain.Exame;
import br.com.riatladias.sghss.modules.exame.dto.ExameRequestDTO;
import br.com.riatladias.sghss.modules.exame.repository.ExameRepository;
import br.com.riatladias.sghss.modules.paciente.repository.PacienteRepository;
import br.com.riatladias.sghss.modules.profissional.repository.ProfissionalRepository;

@Service
public class ExameUseCase {

    @Autowired
    private PacienteRepository pacienteRepository;

    @Autowired
    private ProfissionalRepository profissionalRepository;

    @Autowired
    private ExameRepository exameRepository;

    public Exame execute(ExameRequestDTO dto) {
        this.pacienteRepository.findById(dto.getPacienteId())
                .orElseThrow(() -> {
                    throw new PacienteNotFoundException();
                });

        this.profissionalRepository.findById(dto.getProfissionalId())
                .orElseThrow(() -> {
                    throw new ProfissionalNotFoundException();
                });

        var exame = Exame.builder()
                .pacienteId(dto.getPacienteId())
                .profissionalId(dto.getProfissionalId())
                .tipo(dto.getTipo())
                .dataHora(dto.getDataHora())
                .resultado(dto.getResultado())
                .build();

        return this.exameRepository.save(exame);
    }


   
    
}
