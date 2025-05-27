package br.com.riatladias.sghss.modules.shared.domain.enums;

public enum PerfilUsuario {
    ADMIN("Administrador"),
    MEDICO("Médico"),
    ENFERMEIRO("Enfermeiro"),
    RECEPCIONISTA("Recepcionista"),
    TECNICO("Técnico"),
    PACIENTE("Paciente");

    private String description;

    PerfilUsuario(String description) {
        this.description = description;
    }

    public String getDescription() {
        return description;
    }
}
