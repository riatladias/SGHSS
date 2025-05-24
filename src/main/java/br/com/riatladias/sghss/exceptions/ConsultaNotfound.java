package br.com.riatladias.sghss.exceptions;

public class ConsultaNotfound extends RuntimeException {
    public ConsultaNotfound(){
        super("Consulta não encontrada");
    }
}
