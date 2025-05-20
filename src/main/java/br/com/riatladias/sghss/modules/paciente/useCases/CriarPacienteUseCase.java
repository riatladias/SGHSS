package br.com.riatladias.sghss.modules.paciente.useCases;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.riatladias.sghss.exceptions.PacienteFoundException;
import br.com.riatladias.sghss.modules.paciente.domain.Paciente;
import br.com.riatladias.sghss.modules.paciente.repository.PacienteRepository;

@Service
public class CriarPacienteUseCase {

    @Autowired
    private PacienteRepository pacienteRepository;

    public Paciente execute(Paciente pacienteEntity) {
        this.pacienteRepository.findByNomeOrCpf(pacienteEntity.getNome(), pacienteEntity.getCpf())
                .ifPresent(paciente -> {
                    throw new PacienteFoundException();
                });

        return this.pacienteRepository.save(pacienteEntity);
    }
}
