package br.com.riatladias.sghss.modules.seguranca.dto;

import java.util.List;
import java.util.UUID;

import br.com.riatladias.sghss.modules.shared.domain.enums.PerfilUsuario;

public record UsuarioResponseDTO(
        UUID id,
        String username,
        List<PerfilUsuario> perfis) {
}
