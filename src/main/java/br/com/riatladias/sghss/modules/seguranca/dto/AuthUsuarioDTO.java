package br.com.riatladias.sghss.modules.seguranca.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class AuthUsuarioDTO {
    @NotBlank
    private String username;

    @NotBlank
    private String password;
}
