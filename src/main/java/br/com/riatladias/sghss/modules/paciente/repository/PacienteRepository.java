package br.com.riatladias.sghss.modules.paciente.repository;

import java.util.Optional;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.riatladias.sghss.modules.paciente.domain.Paciente;

public interface PacienteRepository extends JpaRepository<Paciente, UUID> {
    Optional<Paciente> findByNomeOrCpf(String nome, String cpf);
}
