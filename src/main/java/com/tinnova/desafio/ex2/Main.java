package com.tinnova.desafio.ex2;

import java.util.ArrayList;
import java.util.Arrays;

public class Main {
    public static void main(String[] args) {

        long[] v = {5,3,2,4,7,1,0,6};
        printArray(v);
        printArray(BubbleSort.sort(v));
    }

    private static void printArray(long[] array){
        System.out.print("Array: {");
        for (int i = 0; i < array.length; i++) {
            System.out.print(array[i]);
            if(i != array.length-1){
                System.out.print(" ,");
            }
        }
        System.out.println("}");
    }




}
