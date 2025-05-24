package br.com.riatladias.sghss.modules.consulta.repository;

import java.util.List;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.riatladias.sghss.modules.consulta.domain.Consulta;

public interface ConsultaRepository extends JpaRepository<Consulta, UUID> {

    List<Consulta> findByPacienteId(UUID pacienteId);

    

}
