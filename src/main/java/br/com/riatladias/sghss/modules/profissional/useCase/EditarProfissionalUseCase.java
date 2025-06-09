package br.com.riatladias.sghss.modules.profissional.useCase;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.riatladias.sghss.exceptions.ProfissionalNotFoundException;
import br.com.riatladias.sghss.modules.profissional.dto.ProfissionalDTO;
import br.com.riatladias.sghss.modules.profissional.repository.ProfissionalRepository;

@Service
public class EditarProfissionalUseCase {
    @Autowired
    private ProfissionalRepository profissionalRepository;

    public ProfissionalDTO execute(ProfissionalDTO dto) {
        var profissional = this.profissionalRepository.findById(dto.getProfissionalId())
                .orElseThrow(() -> {
                    throw new ProfissionalNotFoundException();
                });
                
        profissional.setNome(dto.getNome());
        profissional.setEspecialidade(dto.getEspecialidade());
        profissional.setEmail(dto.getEmail());
        profissional.setTelefone(dto.getTelefone());
        profissional.setRegistroProfissional(dto.getRegistroProfissional());

        this.profissionalRepository.save(profissional);

        return dto;
    }
}
