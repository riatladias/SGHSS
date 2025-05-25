package br.com.riatladias.sghss.exceptions;

public class ProntuarioNotFoundException extends RuntimeException {
    public ProntuarioNotFoundException() {
        super("Prontuario não encontrada");
    }
}
