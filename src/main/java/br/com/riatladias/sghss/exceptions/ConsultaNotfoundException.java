package br.com.riatladias.sghss.exceptions;

public class ConsultaNotfoundException extends RuntimeException {
    public ConsultaNotfoundException(){
        super("Consulta não encontrada");
    }
}
