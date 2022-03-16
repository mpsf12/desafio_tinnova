package com.tinnova.desafio.ex5.exception;

public class VeiculoNotFoundException extends RuntimeException{

    public VeiculoNotFoundException() {
    }

    public VeiculoNotFoundException(String message) {
        super(message);
    }

    public VeiculoNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }

    public VeiculoNotFoundException(Throwable cause) {
        super(cause);
    }
}
