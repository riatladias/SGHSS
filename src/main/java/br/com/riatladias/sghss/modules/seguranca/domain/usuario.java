package br.com.riatladias.sghss.modules.seguranca.domain;

import java.time.LocalDateTime;
import java.util.Set;
import java.util.UUID;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.validator.constraints.Length;

import br.com.riatladias.sghss.modules.profissional.domain.ProfissionalDeSaude;
import br.com.riatladias.sghss.modules.shared.domain.enums.PerfilUsuario;
import jakarta.persistence.Column;
import jakarta.persistence.ElementCollection;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity(name = "usuario")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Usuario {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @NotBlank
    @Pattern(regexp = "\\S+", message = "O campo [username] não deve conter espaço")
    @Column(unique = true)
    private String username;

    @Email(message = "O campo [email] deve conter um e-mail válido")
    private String email;

    @Length(min = 10, max = 100, message = "A senha deve conter entre (10) e (100) caracteres")
    private String password;

    // PROFISSIONAL DE SAUDE
    @ManyToOne
    @JoinColumn(name = "profissionalDeSaude_id", insertable = false, updatable = false)
    private ProfissionalDeSaude profissionalDeSaude;
    @Column(name = "profissionalDeSaude_id", nullable = false)
    private UUID profissionalId;

    @ElementCollection(fetch = FetchType.EAGER)
    @Enumerated(EnumType.STRING)
    @Column(name = "perfil")
    private Set<PerfilUsuario> perfis;

    @CreationTimestamp
    private LocalDateTime createdAt;
}
