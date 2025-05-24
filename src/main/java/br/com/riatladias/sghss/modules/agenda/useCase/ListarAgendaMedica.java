package br.com.riatladias.sghss.modules.agenda.useCase;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.riatladias.sghss.exceptions.ProfissionalNotFoundException;
import br.com.riatladias.sghss.modules.agenda.domain.AgendaMedica;
import br.com.riatladias.sghss.modules.agenda.dto.AgendaRequestDTO;
import br.com.riatladias.sghss.modules.agenda.repository.AgendaMedicaRepositoy;
import br.com.riatladias.sghss.modules.profissional.repository.ProfissionalRepository;

@Service
public class ListarAgendaMedica {

    @Autowired
    private ProfissionalRepository profissionalRepository;

    @Autowired
    private AgendaMedicaRepositoy agendaMedicaRepositoy;

    public Optional<List<AgendaMedica>> execute(AgendaRequestDTO dto) {

        this.profissionalRepository.findById(dto.getProfissionalId())
                .orElseThrow(() -> {
                    throw new ProfissionalNotFoundException();
                });

        return this.agendaMedicaRepositoy.findByProfissionalIdAndDisponivelTrueAndData(dto.getProfissionalId(),
                dto.getData());

    }
}
