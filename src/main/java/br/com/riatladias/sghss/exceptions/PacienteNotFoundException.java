package br.com.riatladias.sghss.exceptions;

public class PacienteNotFoundException extends RuntimeException {
    public PacienteNotFoundException () {
        super("Paciente não encontrado");
    }
    
}
