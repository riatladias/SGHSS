package br.com.riatladias.sghss.modules.paciente;

import java.time.LocalDate;
import java.util.UUID;

import org.hibernate.validator.constraints.br.CPF;

import com.fasterxml.jackson.annotation.JsonFormat;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class PacienteEntity {

    private UUID id;
    @NotBlank
    private String nome;
    @CPF(message = "Cpf inválido")
    private String cpf;
    @JsonFormat(pattern = "dd-MM-yyyy")
    private LocalDate dataNascimento;
    @Email(message = "O campo [email] deve conter um e-mail válido")
    private String email;

}
