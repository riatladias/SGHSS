package br.com.riatladias.sghss.modules.agenda.useCase;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.riatladias.sghss.exceptions.ProfissionalNotFoundException;
import br.com.riatladias.sghss.modules.agenda.domain.AgendaMedica;
import br.com.riatladias.sghss.modules.agenda.dto.AgendaRequestDTO;
import br.com.riatladias.sghss.modules.agenda.repository.AgendaMedicaRepositoy;
import br.com.riatladias.sghss.modules.profissional.repository.ProfissionalRepository;
import jakarta.transaction.Transactional;

@Service
public class CriarAgendaUseCase {

    @Autowired
    private ProfissionalRepository profissionalRepository;

    @Autowired
    private AgendaMedicaRepositoy agendaMedicaRepositoy;

    @Transactional
    public AgendaMedica execute(AgendaRequestDTO dto) {
        var profissional = this.profissionalRepository.findById(dto.getProfissionalId())
                .orElseThrow(() -> {
                    throw new ProfissionalNotFoundException();
                });

        List<AgendaMedica> slots = new ArrayList<>();

        LocalTime atual = dto.getHoraInicio();

        AgendaMedica slot = null;

        while (atual.plusMinutes(dto.getDuracaoMinutos()).compareTo(dto.getHoraFim()) <= 0) {

            LocalTime slotFim = atual.plusMinutes(dto.getDuracaoMinutos());
            // Verifica se já existe um slot ou consulta marcada com conflito neste período
            var conflito = agendaMedicaRepositoy
                    .existsByProfissionalIdAndDataAndHoraInicioLessThanEqualAndHoraFimGreaterThanEqual(
                            profissional.getId(), dto.getData(), slotFim, atual);
            // Se não houver conflito, cria o slot
            if (!conflito) {
                slot = AgendaMedica.builder()
                        .profissionalId(profissional.getId())
                        .data(dto.getData())
                        .horaInicio(atual)
                        .horaFim(slotFim)
                        .disponivel(true)
                        .build();

            }
            // Incrementa para o próximo slot
            atual = slotFim;
            
        }

        this.agendaMedicaRepositoy.saveAll(slots);

        return slot;
    }

    public List<AgendaMedica> listarDisponiveis(UUID profissionalId, LocalDate data) {
        return this.agendaMedicaRepositoy.findByProfissionalIdAndDisponivelTrueAndData(profissionalId, data);
    }
}