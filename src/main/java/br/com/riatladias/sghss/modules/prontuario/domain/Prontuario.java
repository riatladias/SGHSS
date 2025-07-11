package br.com.riatladias.sghss.modules.prontuario.domain;

import java.time.LocalDateTime;
import java.util.UUID;

import org.hibernate.annotations.CreationTimestamp;

import br.com.riatladias.sghss.modules.consulta.domain.Consulta;
import br.com.riatladias.sghss.modules.paciente.domain.Paciente;
import br.com.riatladias.sghss.modules.profissional.domain.ProfissionalDeSaude;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity(name = "prontuario")
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Prontuario {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    private String diagnostico;

    private String anotacoes;

    // PACIENTE
    @ManyToOne
    @JoinColumn(name = "paciente_id", insertable = false, updatable = false)
    private Paciente paciente;
    @Column(name = "paciente_id", nullable = false)
    private UUID pacienteId;

    // PROFISSIONAL DE SAUDE
    @ManyToOne
    @JoinColumn(name = "profissional_id", insertable = false, updatable = false)
    private ProfissionalDeSaude profissionalDeSaude;
    @Column(name = "profissional_id", nullable = false)
    private UUID profissionalId;

    // Consulta
    @OneToOne
    private Consulta consulta;

    // Evolução médica
    private String evolucao;
    private UUID profissionalEvolucao;

    @CreationTimestamp
    private LocalDateTime createdAt;
}
