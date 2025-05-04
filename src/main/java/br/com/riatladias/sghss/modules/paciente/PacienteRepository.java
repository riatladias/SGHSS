package br.com.riatladias.sghss.modules.paciente;

import java.util.Optional;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

public interface PacienteRepository extends JpaRepository<PacienteEntity, UUID> {
    public Optional<PacienteEntity> findByNomeOrCpf(String nome, String cpf);
}
