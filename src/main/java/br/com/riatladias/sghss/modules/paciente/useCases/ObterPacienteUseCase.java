package br.com.riatladias.sghss.modules.paciente.useCases;

import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.riatladias.sghss.exceptions.PacienteNotFoundException;
import br.com.riatladias.sghss.modules.paciente.domain.Paciente;
import br.com.riatladias.sghss.modules.paciente.repository.PacienteRepository;

@Service
public class ObterPacienteUseCase {

    @Autowired
    private PacienteRepository pacienteRepository;

    public Paciente execute(UUID id) {
        var paciente = this.pacienteRepository.findById(id)
        .orElseThrow(()->{
            throw new PacienteNotFoundException();
        });

        return paciente;
    }
}
