package com.alejandroramirez.icm_aprendearitmetica;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.content.Context;
import android.content.Intent;
import android.graphics.*;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import java.util.HashMap;


public class ActivityDivision extends AppCompatActivity {

    private int num1, num2, resultadoRes, resultadoCos, respuestaRes, respuestaCos;
    private ImageView imgN1, imgN2, imgRes;
    private Button btnEnviar, btnCambio, btnOtra;
    private EditText fieldC, fieldR;
    private Intent envia, recibe;
    private HashMap<String,Integer> datos;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_division);

        imgN1 = (ImageView) findViewById(R.id.imgN1D);
        imgN2 = (ImageView) findViewById(R.id.imgN2D);
        imgRes = (ImageView) findViewById(R.id.imgResD);
        btnEnviar = (Button) findViewById(R.id.btnComprobarD);
        btnCambio = (Button) findViewById(R.id.btnCambioD);
        btnOtra = (Button) findViewById(R.id.btnOtraD);
        fieldC = (EditText) findViewById(R.id.tfCociente);
        fieldR = (EditText) findViewById(R.id.tfResiduo);

        recibe=getIntent();
        datos=(HashMap<String, Integer>) recibe.getSerializableExtra("mapita");
        generacion();
        asignacionNum(num1, num2);

        btnEnviar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                verificar();
            }
        });

        btnOtra.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                num1=0;
                num2=0;
                resultadoCos=0;
                respuestaCos=0;
                respuestaRes=0;
                respuestaRes=0;
                generacion();
                asignacionNum(num1, num2);

                fieldR.setText("");
                fieldC.setText("");

                imgRes.setVisibility(View.INVISIBLE);
                btnEnviar.setVisibility(View.VISIBLE);
                btnCambio.setVisibility(View.INVISIBLE);
                btnOtra.setVisibility(View.INVISIBLE);
            }
        });


        btnCambio.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                envia = new Intent(getApplicationContext(), MenuOperaciones.class);
                envia.putExtra("mapita",datos);
                startActivity(envia);
            }
        });



    }

    private void verificar() {
        if ((fieldR.getText().toString() == null)&&fieldC.getText().toString()==null) {
            Toast.makeText(getApplicationContext(), "¡Ingresa tu resultado!", Toast.LENGTH_LONG).show();
        } else {
            respuestaRes = Integer.parseInt(fieldR.getText().toString());
            respuestaCos=Integer.parseInt(fieldC.getText().toString());
            if ((resultadoRes == respuestaRes)&&(resultadoCos==respuestaCos)) {
                imgRes.setImageResource(R.drawable.correcto);
                imgRes.setVisibility(View.VISIBLE);
                int x=0,y=0;
                x=datos.get("CD");
                y=datos.get("PD");
                x++;
                y++;
                datos.put("CD",x);
                datos.put("PD",y);
            } else {
                Toast.makeText(getApplicationContext(),
                        "¡Incorrecto, la respuesta es: Cociente= " + resultadoCos+ " Residuo= "+resultadoRes, Toast.LENGTH_SHORT).show();
                imgRes.setImageResource(R.drawable.incorrecto);
                imgRes.setVisibility(View.VISIBLE);
                int x=0;
                x=datos.get("CD");
                x++;
                datos.put("CD",x);
            }
            if(datos.get("CD")>=3){
                btnOtra.setVisibility(View.INVISIBLE);
            }else{
                btnOtra.setVisibility(View.VISIBLE);
            }
            btnEnviar.setVisibility(View.INVISIBLE);
            btnCambio.setVisibility(View.VISIBLE);
        }
        num1=0;
        num2=0;
        resultadoCos=0;
        respuestaCos=0;
        respuestaRes=0;
        respuestaRes=0;
    }


    private void generacion() {
        while (true) {
            num1 = (int) (Math.random() * 9 + 1);
            num2 = (int) (Math.random() * 9 + 1);
            if (num1 > num2) {
                resultadoCos = (int) num1 / num2;
                resultadoRes = num1 - (resultadoCos * num2);
                break;
            }
        }

    }

    private void asignacionNum(int a, int b) {
        switch (a) {
            case 1:
                imgN1.setImageResource(R.drawable.num1);
                break;
            case 2:
                imgN1.setImageResource(R.drawable.num2);
                break;
            case 3:
                imgN1.setImageResource(R.drawable.num3);
                break;
            case 4:
                imgN1.setImageResource(R.drawable.num4);
                break;
            case 5:
                imgN1.setImageResource(R.drawable.num5);
                break;
            case 6:
                imgN1.setImageResource(R.drawable.num6);
                break;
            case 7:
                imgN1.setImageResource(R.drawable.num7);
                break;
            case 8:
                imgN1.setImageResource(R.drawable.num8);
                break;
            case 9:
                imgN1.setImageResource(R.drawable.num9);
                break;
        }
        switch (b) {
            case 1:
                imgN2.setImageResource(R.drawable.num1);
                break;
            case 2:
                imgN2.setImageResource(R.drawable.num2);
                break;
            case 3:
                imgN2.setImageResource(R.drawable.num3);
                break;
            case 4:
                imgN2.setImageResource(R.drawable.num4);
                break;
            case 5:
                imgN2.setImageResource(R.drawable.num5);
                break;
            case 6:
                imgN2.setImageResource(R.drawable.num6);
                break;
            case 7:
                imgN2.setImageResource(R.drawable.num7);
                break;
            case 8:
                imgN2.setImageResource(R.drawable.num8);
                break;
            case 9:
                imgN2.setImageResource(R.drawable.num9);
                break;
        }
    }
}

