package br.com.riatladias.sghss.modules.consulta.domain;

import java.time.LocalDateTime;
import java.util.UUID;

import org.hibernate.annotations.CreationTimestamp;

import br.com.riatladias.sghss.modules.paciente.domain.Paciente;
import br.com.riatladias.sghss.modules.profissional.domain.ProfissionalDeSaude;
import br.com.riatladias.sghss.modules.prontuario.domain.Prontuario;
import br.com.riatladias.sghss.modules.shared.domain.enums.StatusConsulta;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
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

@Entity(name = "consulta")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Consulta {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    private LocalDateTime dataHora;
    
    private String observacoes;

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

    @OneToOne(mappedBy = "consulta", cascade = CascadeType.ALL)
    private Prontuario prontuario;

    @Enumerated(EnumType.STRING)
    private StatusConsulta status;

    private String motivoDoCancelamento;
    private LocalDateTime dataDoCancelamento;

    @CreationTimestamp
    private LocalDateTime createdAt;
}
