package br.com.riatladias.sghss.modules.agenda.repository;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.riatladias.sghss.modules.agenda.domain.AgendaMedica;

@Repository
public interface AgendaMedicaRepositoy extends JpaRepository<AgendaMedica, UUID>  {
    List<AgendaMedica> findByProfissionalIdAndData(UUID profissionalId, LocalDate data);

    List<AgendaMedica> findByProfissionalIdAndDisponivelTrueAndData(UUID profissionalId, LocalDate data);

    // Método para verificar conflito entre horários:
    boolean existsByProfissionalIdAndDataAndHoraInicioLessThanEqualAndHoraFimGreaterThanEqual(
            UUID profissionalId,
            LocalDate data,
            LocalTime horaFim,
            LocalTime horaInicio);
}
