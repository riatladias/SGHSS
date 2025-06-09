package br.com.riatladias.sghss.modules.profissional.dto;

import java.util.UUID;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ProfissionalDTO {
    private UUID profissionalId;
    private String nome;
    private String registroProfissional;
    private String especialidade;
    private String email;
    private String telefone;
}
