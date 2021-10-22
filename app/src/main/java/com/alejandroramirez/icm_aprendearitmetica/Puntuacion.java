package com.alejandroramirez.icm_aprendearitmetica;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.HashMap;

public class Puntuacion extends AppCompatActivity {


    private HashMap<String,Integer> datos;
    private Intent recibe,envia;
    private Button btn;
    private TextView txtPuntos,txtMensaje;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_puntuacion);

        recibe=getIntent();
        datos = (HashMap<String, Integer>) recibe.getSerializableExtra("mapita");

        btn=(Button) findViewById(R.id.btnHome);
        txtPuntos=(TextView) findViewById(R.id.tvPuntos);
        txtMensaje=(TextView) findViewById(R.id.tvMensaje);

        int puntos=datos.get("PS")+datos.get("PR")+datos.get("PM")+datos.get("PD");
        txtPuntos.setText("¡"+puntos+" puntos!");
        if(puntos==12){
            txtMensaje.setText(R.string.txtPerfecto);
        }else if(puntos<=11 && puntos>8){
            txtMensaje.setText(R.string.txtAlto);
        }else if(puntos<=8 && puntos>4){
            txtMensaje.setText(R.string.txtMedio);
        }else if(puntos<=4){
            txtMensaje.setText(R.string.txtBajo);
        }
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                envia=new Intent(getApplicationContext(),MainActivity.class);
                startActivity(envia);
            }
        });
    }
}