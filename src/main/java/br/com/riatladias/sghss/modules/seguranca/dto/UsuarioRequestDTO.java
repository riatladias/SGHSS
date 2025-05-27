package br.com.riatladias.sghss.modules.seguranca.dto;

import java.util.Set;

public record UsuarioRequestDTO(
    String nome,
    String email,
    String username,
    String senha,
    Set<Long> perfisIds
) {}