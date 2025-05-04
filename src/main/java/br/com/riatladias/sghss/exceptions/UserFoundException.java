package br.com.riatladias.sghss.exceptions;

public class UserFoundException extends RuntimeException {
    public UserFoundException(String err) {
        super(err);
    }
}
