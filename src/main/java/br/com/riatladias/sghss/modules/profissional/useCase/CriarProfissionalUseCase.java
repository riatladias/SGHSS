package br.com.riatladias.sghss.modules.profissional.useCase;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.riatladias.sghss.exceptions.UserFoundException;
import br.com.riatladias.sghss.modules.profissional.domain.ProfissionalDeSaude;
import br.com.riatladias.sghss.modules.profissional.repository.ProfissionalRepository;

@Service
public class CriarProfissionalUseCase {

    @Autowired
    private ProfissionalRepository profissionalRepository;

    public ProfissionalDeSaude execute(ProfissionalDeSaude profissionalDeSaude) {
        this.profissionalRepository.findByNomeOrRegistroProfissional(profissionalDeSaude.getNome(),
                profissionalDeSaude.getRegistroProfissional())
                .ifPresent(profissional -> {
                    throw new UserFoundException("Profissional já existe");
                });

        return this.profissionalRepository.save(profissionalDeSaude);

    }

}
