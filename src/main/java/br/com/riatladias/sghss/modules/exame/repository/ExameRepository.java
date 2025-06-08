package br.com.riatladias.sghss.modules.exame.repository;

import java.util.List;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.riatladias.sghss.modules.exame.domain.Exame;

public interface ExameRepository extends JpaRepository<Exame, UUID> {
    List<Exame> findByPacienteId(UUID pacienteId);

}
