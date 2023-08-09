package com.example.appgaseta.model;

public class Combustiveis {
    private float gasolina;
    private float etanol;
    private String resultado;

    public Combustiveis(){

    }

    public Combustiveis(float gasolina, float etanol, String resultado) {
        this.gasolina = gasolina;
        this.etanol = etanol;
        this.resultado = resultado;
    }

    public Combustiveis(float gasolina, float etanol) {
        this.gasolina = gasolina;
        this.etanol = etanol;
    }

    public String getResultado() {
        return resultado;
    }

    public void setResultado(String resultado) {
        this.resultado = resultado;
    }

    public float getGasolina() {
        return gasolina;
    }

    public float setGasolina(float gasolina) {
        this.gasolina = gasolina;
        return gasolina;
    }

    public float getEtanol() {
        return etanol;
    }

    public float setEtanol(float etanol) {
        this.etanol = etanol;
        return etanol;
    }


    @Override
    public String toString() {
        return "Combustiveis{" +
                "gasolina='" + gasolina + '\'' +
                ", etanol='" + etanol + '\'' +
                '}';
    }
}
