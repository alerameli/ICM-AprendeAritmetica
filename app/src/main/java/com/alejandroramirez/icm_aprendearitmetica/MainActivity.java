package com.alejandroramirez.icm_aprendearitmetica;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import java.util.HashMap;

public class MainActivity extends AppCompatActivity {

    Button btnJugar, btnReglas, btnSalir;
    public HashMap<String, Integer> mapa;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnJugar = (Button) findViewById(R.id.btnIniciar);
        btnReglas = (Button) findViewById(R.id.btnReglas);
        btnSalir = (Button) findViewById(R.id.btnSalir);

        mapa = new HashMap<String, Integer>();

        mapa.put("PS", 0);
        mapa.put("PR", 0);
        mapa.put("PM", 0);
        mapa.put("PD", 0);
        mapa.put("CS", 0);
        mapa.put("CR", 0);
        mapa.put("CM", 0);
        mapa.put("CD", 0);


        btnJugar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intento = new Intent(getApplicationContext(), MenuOperaciones.class);
                intento.putExtra("mapita", mapa);
                startActivity(intento);
            }
        });

        btnReglas.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intento = new Intent(getApplicationContext(), reglas.class);
                startActivity(intento);
            }
        });

        btnSalir.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                System.exit(0);
            }
        });
    }
}