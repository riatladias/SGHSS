package br.com.riatladias.sghss.modules.prontuario.repository;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.riatladias.sghss.modules.prontuario.domain.Prontuario;

public interface ProntuarioRepository extends JpaRepository<Prontuario, UUID> {

}
