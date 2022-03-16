package com.tinnova.desafio.ex2;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;

public class BubbleSort {

    public static long[] sort(long[] array){
        if(array == null){
            throw new IllegalArgumentException("Array nao pode ser null.");
        }
        boolean modificado = false;
        for (int i = 1; i < array.length; i++) {
            for (int j = 0; j < array.length - 1; j++) {
                if (array[j] > array[j + 1]) {
                    long x = array[j];
                    array[j] = array[j + 1];
                    array[j + 1] = x;
                    modificado = true;
                }
            }
            if(modificado){
                modificado = false;
            } else {
                return array;
            }
        }

        return array;
    }
}
