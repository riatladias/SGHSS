package br.com.riatladias.sghss.modules.prontuario.useCase;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.riatladias.sghss.exceptions.ConsultaNotfoundException;
import br.com.riatladias.sghss.modules.consulta.repository.ConsultaRepository;
import br.com.riatladias.sghss.modules.prontuario.domain.Prontuario;
import br.com.riatladias.sghss.modules.prontuario.dto.ProntuarioResponseDTO;
import br.com.riatladias.sghss.modules.prontuario.dto.ProntuarioResquestDTO;
import br.com.riatladias.sghss.modules.prontuario.repository.ProntuarioRepository;

@Service
public class CriarPronturarioUseCase {

    @Autowired
    private ProntuarioRepository prontuarioRepository;

    @Autowired
    private ConsultaRepository consultaRepository;

    public ProntuarioResponseDTO execute(ProntuarioResquestDTO dto) {
        var consulta = this.consultaRepository.findById(dto.getConsultaId())
                .orElseThrow(() -> {
                    throw new ConsultaNotfoundException();
                });

        var prontuario = Prontuario.builder()
                .diagnostico(dto.getDiagnostico())
                .anotacoes(dto.getAnotacoes())
                .pacienteId(consulta.getPacienteId())
                .profissionalId(consulta.getProfissionalId())
                .consulta(consulta)
                .build();

        this.prontuarioRepository.save(prontuario);

        return ProntuarioResponseDTO.builder()
                .id(prontuario.getId())
                .nomePaciente(consulta.getPaciente().getNome())
                .profissionalDeSaude(consulta.getProfissionalDeSaude().getNome())
                .diagnostico(prontuario.getDiagnostico())
                .anotacoes(prontuario.getAnotacoes())
                .consultaId(prontuario.getConsulta().getId())
                .build();

    }
}
