package com.tinnova.desafio.ex1;

public class MainEx1 {

    public static void main(String[] args) {

        Eleicao eleicao = new Eleicao(1000,800,150,50);
        try {
            double teste = eleicao.percentualVotosValidos();
            System.out.println("Votos validos: " + teste);
            teste = eleicao.percentualVotosBrancos();
            System.out.println("Votos brancos: " + teste);
            teste = eleicao.percentualVotosNulos();
            System.out.println("Votos nulos: " + teste);
        } catch (Exception e){
            System.out.println(e.getMessage());
        }

    }
}
