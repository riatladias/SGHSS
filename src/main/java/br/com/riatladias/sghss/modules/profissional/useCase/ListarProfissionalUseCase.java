package br.com.riatladias.sghss.modules.profissional.useCase;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.riatladias.sghss.modules.profissional.domain.ProfissionalDeSaude;
import br.com.riatladias.sghss.modules.profissional.repository.ProfissionalRepository;

@Service
public class ListarProfissionalUseCase {
    @Autowired
    private ProfissionalRepository profissionalRepository;

    public List<ProfissionalDeSaude> execute() {
        return this.profissionalRepository.findAll();
    }
}
