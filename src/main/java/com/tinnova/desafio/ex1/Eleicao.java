package com.tinnova.desafio.ex1;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Eleicao {

    private long totalEleitores;
    private long votosValidos;
    private long votosBrancos;
    private long votosNulos;

    public double percentualVotosValidos(){
        validate();
        return (double) votosValidos/totalEleitores;
    }
    public double percentualVotosBrancos(){
        validate();
        return (double) votosBrancos/totalEleitores;
    }
    public double percentualVotosNulos(){
        validate();
        return (double) votosNulos/totalEleitores;
    }

    private void validate(){
        if(totalEleitores == 0){
            throw new ArithmeticException("Necessário colocar quantidade de eleitores para calcular o porcentual dos votos válidos");
        }
    }


}
