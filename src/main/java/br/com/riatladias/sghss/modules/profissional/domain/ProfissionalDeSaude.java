package br.com.riatladias.sghss.modules.profissional.domain;

import java.time.LocalDateTime;
import java.util.UUID;

import org.hibernate.annotations.CreationTimestamp;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
@Entity(name = "profissional_de_saude")
public class ProfissionalDeSaude {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @NotBlank
    private String nome;

    @NotBlank
    private String registroProfissional;

    @NotBlank
    private String especialidade;

    @Email
    private String email;
    private String telefone;

    @CreationTimestamp 
    private LocalDateTime createdAt;

}
