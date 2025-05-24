package br.com.riatladias.sghss.modules.agenda.domain;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.UUID;

import br.com.riatladias.sghss.modules.consulta.domain.Consulta;
import br.com.riatladias.sghss.modules.profissional.domain.ProfissionalDeSaude;
import jakarta.persistence.CascadeType;
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

@Entity(name = "agenda")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class AgendaMedica {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    // PROFISSIONAL
    @ManyToOne
    @JoinColumn(name = "profissional_id", insertable = false, updatable = false)
    private ProfissionalDeSaude profissionalDeSaude;
    @Column(name = "profissional_id", nullable = false)
    private UUID profissionalId;

    private LocalDate data;

    private LocalTime horaInicio;

    private LocalTime horaFim;

    private boolean disponivel;

    @OneToOne(mappedBy = "agenda", cascade = CascadeType.ALL)
    private Consulta consulta; // consulta marcada neste horário (se houver)
    
}
