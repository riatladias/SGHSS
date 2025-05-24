package br.com.riatladias.sghss.modules.prontuario.useCase;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.riatladias.sghss.exceptions.ConsultaNotfoundException;
import br.com.riatladias.sghss.exceptions.PacienteNotFoundException;
import br.com.riatladias.sghss.exceptions.ProfissionalNotFoundException;
import br.com.riatladias.sghss.modules.consulta.repository.ConsultaRepository;
import br.com.riatladias.sghss.modules.paciente.repository.PacienteRepository;
import br.com.riatladias.sghss.modules.profissional.repository.ProfissionalRepository;
import br.com.riatladias.sghss.modules.prontuario.domain.Prontuario;
import br.com.riatladias.sghss.modules.prontuario.dto.ProntuarioResponseDTO;
import br.com.riatladias.sghss.modules.prontuario.dto.ProntuarioResquestDTO;
import br.com.riatladias.sghss.modules.prontuario.repository.ProntuarioRepository;

@Service
public class CriarPronturarioUseCase {

    @Autowired
    private PacienteRepository pacienteRepository;

    @Autowired
    private ProfissionalRepository profissionalRepository;

    @Autowired
    private ProntuarioRepository prontuarioRepository;

    @Autowired
    private ConsultaRepository consultaRepository;

    public ProntuarioResponseDTO execute(ProntuarioResquestDTO dto) {

        var paciente = this.pacienteRepository.findById(dto.getPacienteId())
                .orElseThrow(() -> {
                    throw new PacienteNotFoundException();
                });

        var profissional = this.profissionalRepository.findById(dto.getProfissionalId())
                .orElseThrow(() -> {
                    throw new ProfissionalNotFoundException();
                });

        var consulta = this.consultaRepository.findById(dto.getConsultaId())
                .orElseThrow(() -> {
                    throw new ConsultaNotfoundException();
                });

        var prontuario = Prontuario.builder()
                .diagnostico(dto.getDiagnostico())
                .anotacoes(dto.getAnotacoes())
                .pacienteId(dto.getPacienteId())
                .profissionalId(dto.getProfissionalId())
                .consulta(consulta)
                .build();

        this.prontuarioRepository.save(prontuario);

        return ProntuarioResponseDTO.builder()
                .nomePaciente(paciente.getNome())
                .profissionalDeSaude(profissional.getNome())
                .diagnostico(prontuario.getDiagnostico())
                .anotacoes(prontuario.getAnotacoes())
                .consultaId(prontuario.getConsulta().getId())
                .build();

    }
}
