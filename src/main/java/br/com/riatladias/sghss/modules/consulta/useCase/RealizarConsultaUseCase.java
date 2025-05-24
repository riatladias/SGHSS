package br.com.riatladias.sghss.modules.consulta.useCase;

import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.riatladias.sghss.exceptions.ConsultaNotfoundException;
import br.com.riatladias.sghss.modules.consulta.dto.ConsultaResponseDTO;
import br.com.riatladias.sghss.modules.consulta.repository.ConsultaRepository;
import br.com.riatladias.sghss.modules.shared.domain.enums.StatusConsulta;

@Service
public class RealizarConsultaUseCase {
    @Autowired
    private ConsultaRepository consultaRepository;

    public ConsultaResponseDTO execute(UUID id) {
        var consulta = this.consultaRepository.findById(id)
                .orElseThrow(() -> {
                    throw new ConsultaNotfoundException();
                });

        consulta.setStatus(StatusConsulta.REALIZADA);

        this.consultaRepository.save(consulta);

        return ConsultaResponseDTO.builder()
                .nomePaciente(consulta.getPaciente().getNome())
                .nomeProfissional(consulta.getProfissionalDeSaude().getNome())
                .pronturaId(consulta.getProntuario().getId())
                .agendaId(consulta.getProntuario().getId())
                .status(consulta.getStatus())
                .observacoes(consulta.getObservacoes())
                .build();
    }
}
