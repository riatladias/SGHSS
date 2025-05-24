package br.com.riatladias.sghss.modules.historico.useCase;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.riatladias.sghss.exceptions.PacienteNotFoundException;
import br.com.riatladias.sghss.modules.consulta.repository.ConsultaRepository;
import br.com.riatladias.sghss.modules.exame.repository.ExameRepository;
import br.com.riatladias.sghss.modules.historico.dto.ConsultaDTO;
import br.com.riatladias.sghss.modules.historico.dto.ExameDTO;
import br.com.riatladias.sghss.modules.historico.dto.HistoricoClinicoDTO;
import br.com.riatladias.sghss.modules.historico.dto.HistoricoClinicoRequestDTO;
import br.com.riatladias.sghss.modules.historico.dto.ProntuarioDTO;
import br.com.riatladias.sghss.modules.paciente.repository.PacienteRepository;
import br.com.riatladias.sghss.modules.prontuario.repository.ProntuarioRepository;

@Service
public class HistoricoClinicoUseCase {

    @Autowired
    private PacienteRepository pacienteRepository;

    @Autowired
    private ConsultaRepository consultaRepository;

    @Autowired
    private ProntuarioRepository prontuarioRepository;

    @Autowired
    private ExameRepository exameRepository;

    public HistoricoClinicoDTO execute(HistoricoClinicoRequestDTO dto) {
        // Paciente
        var paciente = this.pacienteRepository.findById(dto.getPacienteId())
                .orElseThrow(() -> {
                    throw new PacienteNotFoundException();
                });

        // Consulta
        var consultas = this.consultaRepository.findByPacienteId(dto.getPacienteId())
                .stream().map(consulta -> {
                    return ConsultaDTO.builder()
                            .id(consulta.getId())
                            .profissional(consulta.getProfissionalDeSaude().getNome())
                            .status(consulta.getStatus())
                            .build();
                }).toList();

        // Prontuario
        var prontuarios = this.prontuarioRepository.findByPacienteId(dto.getPacienteId())
                .stream().map(prontuario -> {
                    return ProntuarioDTO.builder()
                            .createdAt(prontuario.getCreatedAt())
                            .anotacoes(prontuario.getAnotacoes())
                            .profissional(prontuario.getProfissionalDeSaude().getNome())
                            .diagnosticos(prontuario.getDiagnostico())
                            .build();
                }).toList();

        // Exames
        var exames = this.exameRepository.findByPacienteId(dto.getPacienteId())
                .stream().map(exame -> {
                    return ExameDTO.builder()
                            .tipo(exame.getTipo())
                            .dataHora(exame.getDataHora())
                            .resultado(exame.getResultado())
                            .build(); 

                }).toList();

        var historico = HistoricoClinicoDTO.builder()
                .pacienteId(dto.getPacienteId())
                .nomePaciente(paciente.getNome())
                .consultas(consultas)
                .prontuarios(prontuarios)
                .exames(exames)
                .build();

        return historico;

    }
}
