package com.tinnova.desafio.ex5.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NOT_FOUND)
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
