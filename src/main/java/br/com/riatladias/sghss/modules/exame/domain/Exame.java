package br.com.riatladias.sghss.modules.exame.domain;

import java.time.LocalDateTime;
import java.util.UUID;

import br.com.riatladias.sghss.modules.paciente.domain.Paciente;
import br.com.riatladias.sghss.modules.profissional.domain.ProfissionalDeSaude;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity(name = "exame")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Exame {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    private String tipo;

    private LocalDateTime dataHora;

    private String resultado;
    
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
}
