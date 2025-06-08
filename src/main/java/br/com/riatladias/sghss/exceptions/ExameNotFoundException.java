package br.com.riatladias.sghss.exceptions;

public class ExameNotFoundException extends RuntimeException {
    public ExameNotFoundException() {
        super("Exame não encontrado");
    }
}
