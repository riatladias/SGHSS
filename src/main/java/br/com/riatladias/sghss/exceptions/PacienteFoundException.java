package br.com.riatladias.sghss.exceptions;

public class PacienteFoundException extends RuntimeException {
    public PacienteFoundException() {
        super("Paciente já está cadastrado");
    }
}
