package br.com.riatladias.sghss.modules.paciente.domain;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.UUID;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.validator.constraints.br.CPF;

import com.fasterxml.jackson.annotation.JsonFormat;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
@Entity(name = "paciente")
public class Paciente {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @NotBlank
    private String nome;
    
    @CPF(message = "Cpf inválido")
    private String cpf;
    
    @JsonFormat(pattern = "dd-MM-yyyy")
    private LocalDate dataNascimento;
    
    @Email(message = "O campo [email] deve conter um e-mail válido")
    private String email;

    @CreationTimestamp
    private LocalDateTime createdAt;

}
