package com.example.appgaseta.controller;

import static java.lang.String.format;

public class Calculargaseta {

    public static String calculargaseta(float precogasolina, float precoetanol){

        float res;
        String resultado;

        res = precoetanol / precogasolina;

        if (res <= 0.7){
            resultado = "Abasteça com etanol";
        }else {
            resultado = "Abasteça com gasolina";
        }

        return resultado;
    }
}
