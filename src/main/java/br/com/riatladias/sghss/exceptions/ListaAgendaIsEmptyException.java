package br.com.riatladias.sghss.exceptions;

public class ListaAgendaIsEmptyException extends RuntimeException{
    public ListaAgendaIsEmptyException(){
        super("Lista vazia");
    }
}
