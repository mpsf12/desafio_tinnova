package com.tinnova.desafio.ex3;

public class Fatorial {

    public static long calcular(long x){
        if(x < 0){
            throw new IllegalArgumentException("Numero precisa ser positivo para calcular o fatorial.");
        }
        if(x == 0)
            return 1L;
        else{
            return x * calcular(x-1);
        }
    }
}
