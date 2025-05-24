package br.com.riatladias.sghss.modules.agenda.useCase;

import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

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
    public List<AgendaMedica> execute(AgendaRequestDTO dto) {
        var profissional = this.profissionalRepository.findById(dto.getProfissionalId())
                .orElseThrow(() -> {
                    throw new ProfissionalNotFoundException();
                });

        List<AgendaMedica> slots = new ArrayList<>();

        LocalTime atual = dto.getHoraInicio();

        while (atual.plusMinutes(dto.getDuracaoMinutos()).compareTo(dto.getHoraFim()) <= 0) {

            LocalTime slotFim = atual.plusMinutes(dto.getDuracaoMinutos());
            // Verifica se já existe um slot ou consulta marcada com conflito neste período
            var conflito = agendaMedicaRepositoy
                    .existsByProfissionalIdAndDataAndHoraInicioLessThanEqualAndHoraFimGreaterThanEqual(
                            profissional.getId(), dto.getData(), slotFim, atual);
            // Se não houver conflito, cria o slot
            if (!conflito) {
                AgendaMedica slot = AgendaMedica.builder()
                        .profissionalId(profissional.getId())
                        .data(dto.getData())
                        .horaInicio(atual)
                        .horaFim(slotFim)
                        .disponivel(true)
                        .build();
                slots.add(slot);
            }
            // Incrementa para o próximo slot
            atual = slotFim;
        }


        
        return this.agendaMedicaRepositoy.saveAll(slots);
    }
}