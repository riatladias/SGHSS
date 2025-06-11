package br.com.riatladias.sghss.modules.prescricao.domain;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

import org.hibernate.annotations.CreationTimestamp;

import br.com.riatladias.sghss.modules.paciente.domain.Paciente;
import br.com.riatladias.sghss.modules.profissional.domain.ProfissionalDeSaude;
import jakarta.persistence.Column;
import jakarta.persistence.ElementCollection;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity(name = "prescricao")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Prescricao {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;
    private String anotacoes;

    @ElementCollection(fetch = FetchType.EAGER)
    private List<Medicamento> medicamentos;

    @CreationTimestamp
    private LocalDate dataPrescricao;

    private boolean assinadaDigitalmente;

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
