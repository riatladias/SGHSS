package br.com.riatladias.sghss.modules.profissional.repository;

import java.util.Optional;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.riatladias.sghss.modules.profissional.domain.ProfissionalDeSaude;

public interface ProfissionalRepository extends JpaRepository<ProfissionalDeSaude, UUID> {
    Optional<ProfissionalDeSaude> findByNomeOrRegistroProfissional(String nome, String registroProfissional);
}
