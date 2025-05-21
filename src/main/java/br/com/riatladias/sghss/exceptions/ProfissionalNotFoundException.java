package br.com.riatladias.sghss.exceptions;

public class ProfissionalNotFoundException extends RuntimeException {
    public ProfissionalNotFoundException() {
        super("Profissional de saúde  não encontrado");
    }

}
