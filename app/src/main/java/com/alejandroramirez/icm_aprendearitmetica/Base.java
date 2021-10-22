package com.alejandroramirez.icm_aprendearitmetica;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import java.util.HashMap;

public class Base extends AppCompatActivity {

    private int num1, num2, resultado, respuesta;
    private ImageView imgN1, imgN2, imgRes, imgOp;
    private Button btnEnviar, btnCambio, btnOtra;
    private EditText field;
    private Intent envia, recibe;
    private String operacion;
    public HashMap<String, Integer> datos;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_base);

        imgN1 = (ImageView) findViewById(R.id.imgN1B);
        imgN2 = (ImageView) findViewById(R.id.imgN2B);
        imgRes = (ImageView) findViewById(R.id.imgResB);
        imgOp = (ImageView) findViewById(R.id.imgOp);
        btnEnviar = (Button) findViewById(R.id.btnComprobarB);
        btnCambio = (Button) findViewById(R.id.btnCambioB);
        btnOtra = (Button) findViewById(R.id.btnOtraB);
        field = (EditText) findViewById(R.id.tfResB);

        recibe = getIntent();
        operacion = recibe.getStringExtra("operacion");
        datos = (HashMap<String, Integer>) recibe.getSerializableExtra("mapita");

        generacion(operacion);
        asignacionNum(num1, num2);
        textoOtra(operacion);

        btnEnviar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                verificar(operacion);
            }
        });

        btnOtra.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                num1 = 0;
                num2 = 0;
                resultado = 0;
                respuesta = 0;
                generacion(operacion);
                asignacionNum(num1, num2);

                field.setText("");

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
                envia.putExtra("mapita", datos);
                startActivity(envia);
            }
        });
    }

    private void textoOtra(String valor) {
        switch (valor) {
            case "suma":
                btnOtra.setText(R.string.txtOtraSuma);
                break;
            case "resta":
                btnOtra.setText(R.string.txtOtraResta);
                break;
            case "multi":
                btnOtra.setText(R.string.txtOtraMulti);
                break;
        }
    }

    private void generacion(String valor) {
        switch (valor) {
            case "suma":
                imgOp.setImageResource(R.drawable.mas);
                num1 = (int) (Math.random() * 9 + 1);
                num2 = (int) (Math.random() * 9 + 1);
                resultado = num1 + num2;
                break;
            case "resta":
                imgOp.setImageResource(R.drawable.menos);
                while (true) {
                    num1 = (int) (Math.random() * 9 + 1);
                    num2 = (int) (Math.random() * 9 + 1);
                    if (num2 < num1) {
                        break;
                    }
                }
                resultado = num1 - num2;
                break;
            case "multi":
                imgOp.setImageResource(R.drawable.por);
                num1 = (int) (Math.random() * 9 + 1);
                num2 = (int) (Math.random() * 9 + 1);
                resultado = num1 * num2;
                break;
        }
    }


    private void verificar(String valor) {
        if (field.getText().toString().equals(""))
            Toast.makeText(getApplicationContext(), "¡Ingresa tu resultado!", Toast.LENGTH_SHORT).show();
        else {
            respuesta = Integer.parseInt(field.getText().toString());
            if (resultado == respuesta) {
                switch (valor) {
                    case "suma": {
                        int x = datos.get("CS");
                        int y = datos.get("PS");
                        x = x + 1;
                        y = y + 1;
                        datos.put("CS", x);
                        datos.put("PS", y);
                        break;
                    }
                    case "resta": {
                        int x = datos.get("CR");
                        int y = datos.get("PR");
                        x++;
                        y++;
                        datos.put("CR", x);
                        datos.put("PR", y);
                        break;
                    }
                    case "multi": {
                        int x = datos.get("CM");
                        int y = datos.get("PM");
                        x++;
                        y++;
                        datos.put("CM", x);
                        datos.put("PM", y);
                        break;
                    }
                }
                imgRes.setImageResource(R.drawable.correcto);
                imgRes.setVisibility(View.VISIBLE);
            } else {
                Toast.makeText(getApplicationContext(), "¡Incorrecto, la respuesta es: " + resultado, Toast.LENGTH_SHORT).show();
                imgRes.setImageResource(R.drawable.incorrecto);
                imgRes.setVisibility(View.VISIBLE);
                switch (valor) {
                    case "suma": {
                        int x = datos.get("CS");
                        x++;
                        datos.put("CS", x);
                        break;
                    }
                    case "resta": {
                        int x = datos.get("CR");
                        x++;
                        datos.put("CR", x);
                        break;
                    }
                    case "multi": {
                        int x = datos.get("CM");
                        x++;
                        datos.put("CM", x);
                        break;
                    }
                }
            }
            btnEnviar.setVisibility(View.INVISIBLE);
            btnCambio.setVisibility(View.VISIBLE);
            if ((valor.equals("suma")) && (datos.get("CS") >= 3)) {
                btnOtra.setVisibility(View.INVISIBLE);
            } else if ((valor.equals("resta")) && (datos.get("CR") == 3)) {
                btnOtra.setVisibility(View.INVISIBLE);
            } else if ((valor.equals("multi")) && (datos.get("CM") == 3)) {
                btnOtra.setVisibility(View.INVISIBLE);
            } else {
                btnOtra.setVisibility(View.VISIBLE);
            }
        }
        num1 = 0;
        num2 = 0;
        resultado = 0;
        respuesta = 0;
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