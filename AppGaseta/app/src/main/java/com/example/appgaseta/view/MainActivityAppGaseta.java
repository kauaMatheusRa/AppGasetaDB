package com.example.appgaseta.view;

import static java.lang.String.format;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.appgaseta.R;
import com.example.appgaseta.controller.Calculargaseta;
import com.example.appgaseta.controller.CombustivelController;
import com.example.appgaseta.model.Combustiveis;

import java.util.Locale;

public class MainActivityAppGaseta extends AppCompatActivity {

    Combustiveis combustiveis;

    CombustivelController controller;

    EditText editPrecogasolina;
    EditText editPrecoetanol;

    Button btnCalcular;
    Button btnResetar;
    Button btnFinalizar;

    TextView resultadoGasEta;

    @SuppressLint("WrongViewCast")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gaseta);

        combustiveis = new Combustiveis();
        controller = new CombustivelController(MainActivityAppGaseta.this);
        controller.toString();

        controller.buscar(combustiveis);

        editPrecogasolina = findViewById(R.id.Text_edit_valor_gasolina);
        editPrecoetanol = findViewById(R.id.Text_edit_valor_etanol);
        resultadoGasEta = findViewById(R.id.text_resultado);

        btnCalcular = findViewById(R.id.button_Salvar);
        btnResetar = findViewById(R.id.button_Limpar);
        btnFinalizar = findViewById(R.id.button_Finalizar);

        btnResetar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                editPrecogasolina.setText("");
                editPrecoetanol.setText("");
                resultadoGasEta.setText("Resultado:");
                controller.limpar();

                btnResetar.setEnabled(false);
                btnFinalizar.setEnabled(false);
            }
        });

        btnFinalizar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(MainActivityAppGaseta.this, "Encerrando..", Toast.LENGTH_LONG).show();
                finish();
            }
        });

        btnCalcular.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                boolean dadosOK = true;

                if(TextUtils.isEmpty(editPrecogasolina.getText())){
                    editPrecogasolina.setError("Obrigatório");
                    editPrecogasolina.requestFocus();
                    dadosOK = false;
                }

                if(TextUtils.isEmpty(editPrecoetanol.getText())){
                    editPrecoetanol.setError("Obrigatório");
                    editPrecoetanol.requestFocus();
                    dadosOK = false;
                }


                if(dadosOK){
                    float gas = combustiveis.setGasolina(Float.parseFloat(editPrecogasolina.getText().toString()));
                    float eta = combustiveis.setEtanol(Float.parseFloat(editPrecoetanol.getText().toString()));

                    String resultado = Calculargaseta.calculargaseta(gas,eta);

                    resultadoGasEta.setText(resultado);
                    Toast.makeText(MainActivityAppGaseta.this, "Calculando...", Toast.LENGTH_LONG).show();
                    controller.salvar(combustiveis);

                    Combustiveis combustiveis = new Combustiveis();
                    combustiveis.setGasolina(Float.parseFloat(editPrecogasolina.getText().toString()));
                    combustiveis.setEtanol(Float.parseFloat(editPrecoetanol.getText().toString()));
                    combustiveis.setResultado(resultado);

                    controller.salvarBD(combustiveis);


                    btnFinalizar.setEnabled(true);
                    btnResetar.setEnabled(true);
                }else{
                    btnFinalizar.setEnabled(false);
                    btnResetar.setEnabled(false);
                }



            }
        });
    }

    /*private void calcularGasEta(){

        float GasolinaValor;
        float EtanolValor;
        double calculoGasEta;
        String calculoGasEtaFormatado;


        try{
            GasolinaValor = Float.parseFloat(editPrecogasolina.getText().toString());
            EtanolValor = Float.parseFloat(editPrecoetanol.getText().toString());

            if(GasolinaValor > 0 && EtanolValor > 0){
                combustiveis = new Combustiveis(GasolinaValor, EtanolValor);

                calculoGasEta = controller.calcularGasEta(combustiveis);
                calculoGasEtaFormatado = format(Locale.ENGLISH,"Resultado: %.2f%n", calculoGasEta);

                resultadoGasEta.setText(calculoGasEtaFormatado);
                if (calculoGasEta <= 0.7){
                    calculoGasEtaFormatado = "Resultado: Abasteça com etanol";
                    resultadoGasEta.setText(calculoGasEtaFormatado);
                }else{
                    calculoGasEtaFormatado = "Resultado: Abasteça com gasolina";
                    resultadoGasEta.setText(calculoGasEtaFormatado);
                }
            } else {
                throw new NumberFormatException();
            }

        } catch (NumberFormatException e){
            Toast.makeText(MainActivityAppGaseta.this, "Valor inválido", Toast.LENGTH_LONG).show();
        }

    }*/
}