package br.com.riatladias.sghss.modules.seguranca.dto;

import java.util.Set;
import java.util.UUID;

public record UsuarioResponseDTO(
    UUID id,
    String nome,
    String email,
    Set<String> perfis
) {}
