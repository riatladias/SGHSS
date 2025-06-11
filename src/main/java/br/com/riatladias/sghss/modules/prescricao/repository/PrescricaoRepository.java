package br.com.riatladias.sghss.modules.prescricao.repository;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.riatladias.sghss.modules.prescricao.domain.Prescricao;
import java.util.List;

public interface PrescricaoRepository extends JpaRepository<Prescricao, UUID> {
    List<Prescricao> findByPacienteId(UUID pacienteId);
}