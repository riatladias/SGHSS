package br.com.riatladias.sghss.modules.consulta.useCase;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.riatladias.sghss.exceptions.PacienteNotFoundException;
import br.com.riatladias.sghss.exceptions.ProfissionalNotFoundException;
import br.com.riatladias.sghss.modules.consulta.domain.Consulta;
import br.com.riatladias.sghss.modules.consulta.dto.ConsultaRequestDTO;
import br.com.riatladias.sghss.modules.consulta.repository.ConsultaRepository;
import br.com.riatladias.sghss.modules.paciente.repository.PacienteRepository;
import br.com.riatladias.sghss.modules.profissional.repository.ProfissionalRepository;
import br.com.riatladias.sghss.modules.shared.domain.enums.StatusConsulta;

@Service
public class CriarConsultaUseCase {
    @Autowired
    private PacienteRepository pacienteRepository;

    @Autowired
    private ProfissionalRepository profissionalRepository;

    @Autowired
    private ConsultaRepository consultaRepository;

    // @Autowired
    // private AgendaRepositoy agendaRepositoy;
    
    public Consulta execute(ConsultaRequestDTO dto) {
        this.pacienteRepository.findById(dto.getPacienteId())
                .orElseThrow(() -> {
                    throw new PacienteNotFoundException();
                });

        this.profissionalRepository.findById(dto.getProfissionalId())
                .orElseThrow(() -> {
                    throw new ProfissionalNotFoundException();
                });

        var consulta = Consulta.builder()
                .pacienteId(dto.getPacienteId())
                .profissionalId(dto.getProfissionalId())
                .observacoes(dto.getObservacoes())
                .status(StatusConsulta.AGENDADA)
                .build();

        return this.consultaRepository.save(consulta);

    }
}
