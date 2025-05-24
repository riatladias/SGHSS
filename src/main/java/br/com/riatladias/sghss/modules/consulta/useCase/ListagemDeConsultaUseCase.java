package br.com.riatladias.sghss.modules.consulta.useCase;

import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.riatladias.sghss.exceptions.PacienteNotFoundException;
import br.com.riatladias.sghss.modules.consulta.repository.ConsultaRepository;
import br.com.riatladias.sghss.modules.historico.dto.ConsultaDTO;
import br.com.riatladias.sghss.modules.paciente.repository.PacienteRepository;

@Service
public class ListagemDeConsultaUseCase {

    @Autowired
    private ConsultaRepository consultaRepository;

    @Autowired
    private PacienteRepository pacienteRepository;

    public List<ConsultaDTO> execute(UUID pacienteId) {

        this.pacienteRepository.findById(pacienteId)
                .orElseThrow(() -> {
                    throw new PacienteNotFoundException();
                });

        // Consulta
        var consultas = this.consultaRepository.findByPacienteId(pacienteId)
                .stream().map(consulta -> {
                    return ConsultaDTO.builder()
                            .id(consulta.getId())
                            .profissional(consulta.getProfissionalDeSaude().getNome())
                            .status(consulta.getStatus())
                            .observacoes(consulta.getObservacoes())
                            .build();
                }).toList();

        return consultas;

    }
}
