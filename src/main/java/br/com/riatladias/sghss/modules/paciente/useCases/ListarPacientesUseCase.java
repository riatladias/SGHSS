package br.com.riatladias.sghss.modules.paciente.useCases;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.riatladias.sghss.modules.paciente.domain.Paciente;
import br.com.riatladias.sghss.modules.paciente.repository.PacienteRepository;

@Service
public class ListarPacientesUseCase {

    @Autowired
    private PacienteRepository pacienteRepository;

    public List<Paciente> execute() {
        var pacientes = this.pacienteRepository.findAll();

        return pacientes;
    }
}
