package br.com.riatladias.sghss.modules.consulta.useCase;

import java.time.LocalDateTime;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.riatladias.sghss.exceptions.ConsultaNotfound;
import br.com.riatladias.sghss.modules.consulta.dto.CancelarConsultaRequestDTO;
import br.com.riatladias.sghss.modules.consulta.dto.CancelarConsultaResponseDTO;
import br.com.riatladias.sghss.modules.consulta.repository.ConsultaRepository;
import br.com.riatladias.sghss.modules.shared.domain.enums.StatusConsulta;

@Service
public class CancelarConsultaUseCase {

    @Autowired
    private ConsultaRepository consultaRepository;

    public CancelarConsultaResponseDTO execute(CancelarConsultaRequestDTO dto) {
        var consulta = this.consultaRepository.findById(dto.getConsultaId())
                .orElseThrow(() -> {
                    throw new ConsultaNotfound();
                });

        if (consulta.getStatus() != StatusConsulta.AGENDADA) {
            throw new IllegalStateException("Apenas consultas agendadas podem ser canceladas.");
        }

        consulta.setStatus(StatusConsulta.CANCELADA);
        consulta.setMotivoDoCancelamento(dto.getMotivoDoCancelamento());
        consulta.setDataDoCancelamento(LocalDateTime.now());

        consulta = this.consultaRepository.save(consulta);

        return CancelarConsultaResponseDTO.builder()
                .id(consulta.getId())
                .dataHora(consulta.getDataHora())
                .profissionalDeSaude(consulta.getProfissionalDeSaude().getNome())
                .observacoes(consulta.getObservacoes())
                .status(consulta.getStatus())
                .nomePaciente(consulta.getPaciente().getNome())
                .motivoDoCancelamento(consulta.getMotivoDoCancelamento())
                .dataDoCancelamento(consulta.getDataDoCancelamento())
                .build();
    }
}
