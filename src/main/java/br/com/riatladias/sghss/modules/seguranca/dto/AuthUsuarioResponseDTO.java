package br.com.riatladias.sghss.modules.seguranca.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class AuthUsuarioResponseDTO {
    private String access_token;
    private Long expire_in;
}
