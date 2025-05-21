package br.com.riatladias.sghss.modules.shared.domain;

import jakarta.persistence.Embeddable;
import lombok.Data;

@Data
@Embeddable
public class Endereco {
    private String rua;
    private String cidade;
    private String cep;

}
