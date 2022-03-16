package com.tinnova.desafio.ex4;

public class SomaMultiplo {

    public static long CalcularSomaMultiplos3e5(long max){
        if(max < 0){
            throw new IllegalArgumentException("Numero maximo para o calculo nao pode ser menos que 0");
        }
        long soma = 0;
        for (int i = 0; i <= max; i++) {
            if(i % 3 == 0 || i % 5 == 0){
                soma += i;
            }
        }
        return soma;
    }
}
