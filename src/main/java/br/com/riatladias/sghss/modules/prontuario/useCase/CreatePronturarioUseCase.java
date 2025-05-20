package br.com.riatladias.sghss.modules.prontuario.useCase;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.riatladias.sghss.modules.prontuario.domain.Prontuario;
import br.com.riatladias.sghss.modules.prontuario.repository.ProntuarioRepository;

@Service
public class CreatePronturarioUseCase {

    @Autowired
    private ProntuarioRepository prontuarioRepository;

    public void execute(Prontuario prontuario) {

        this.prontuarioRepository.save(prontuario);
    }
}
