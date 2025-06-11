package br.com.riatladias.sghss.modules.prescricao.domain;

import jakarta.persistence.Embeddable;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Embeddable
public class Medicamento {
    private String nome;
    private String frequencia;
    private String dosagem;
}
