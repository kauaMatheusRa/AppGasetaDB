package com.example.appgaseta.controller;

import android.content.ContentValues;
import android.content.SharedPreferences;
import android.util.Log;

import androidx.annotation.NonNull;

import com.example.appgaseta.database.GasEtaDb;
import com.example.appgaseta.model.Combustiveis;
import com.example.appgaseta.view.MainActivityAppGaseta;

public class CombustivelController extends GasEtaDb {
    SharedPreferences preferences;

    SharedPreferences.Editor listaVip;

    public static final String NOME_PREFERENCES = "pref_listavip";

    public CombustivelController(MainActivityAppGaseta mainActivityAppGaseta) {
        super(mainActivityAppGaseta);
        preferences = mainActivityAppGaseta.getSharedPreferences(NOME_PREFERENCES, 0);
        listaVip = preferences.edit();
    }

    @NonNull
    @Override
    public String toString() {
        Log.d("MVC_Controller", "PessoaController iniciada");

        return super.toString();
    }

    public double calcularGasEta(Combustiveis combustiveis) {
        double precogasolina = combustiveis.getGasolina();
        double precoetanol = combustiveis.getEtanol();

        return (precoetanol / precogasolina);
    }

    public Combustiveis salvar(Combustiveis combustiveis) {
        Log.d("MVC_Controller","Salvo"+combustiveis.toString());

        listaVip.putFloat("precoGasolina", combustiveis.getGasolina());
        listaVip.putFloat("precoEtanol", combustiveis.getEtanol());
        listaVip.putString("resultado", Calculargaseta.calculargaseta(combustiveis.getGasolina(), combustiveis.getEtanol()));
        listaVip.apply();
        return combustiveis;
    }

    public void salvarBD(Combustiveis lista){
        Log.d("MVC_Controller", "Salvo");

        ContentValues dados = new ContentValues();
        dados.put("precoGasolina",lista.getGasolina());
        dados.put("precoEtanol",lista.getEtanol());
        dados.put("resultado", lista.getResultado());

        salvarDados("Combustivel",dados);
    }

    public Combustiveis buscar(Combustiveis combustiveis){
        float gas = combustiveis.setGasolina(preferences.getFloat("precoGasolina", 0.0F));
        float eta = combustiveis.setEtanol(preferences.getFloat("precoEtanol", 0.0F));

        return combustiveis;
    }

    public void limpar(){

        listaVip.clear();
        listaVip.apply();
    }
}
