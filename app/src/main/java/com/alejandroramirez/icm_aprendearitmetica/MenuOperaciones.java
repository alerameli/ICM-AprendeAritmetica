package com.alejandroramirez.icm_aprendearitmetica;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.HashMap;

public class MenuOperaciones extends AppCompatActivity {


    public ImageView img1,img2,img3;
    private Button btnSuma,btnResta,btnMulti, btnDivision,btnTerminar;
    private TextView avanceS,avanceR,avanceM,avanceD;
    private Intent recibe,envia,terminar;
    public String operacion;
    public HashMap<String,Integer> datos;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu_operaciones);

        btnSuma = (Button) findViewById(R.id.btnSuma);
        btnResta=(Button) findViewById(R.id.btnResta);
        btnMulti=(Button) findViewById(R.id.btnMulti);
        btnDivision=(Button) findViewById(R.id.btnDivision);
        btnTerminar=(Button)findViewById(R.id.btnTerminar);

        avanceS=(TextView)findViewById(R.id.tvAvanceS);
        avanceR=(TextView)findViewById(R.id.tvAvanceR);
        avanceM=(TextView)findViewById(R.id.tvAvanceM);
        avanceD=(TextView)findViewById(R.id.tvAvanceD);

        recibe=getIntent();
        datos=(HashMap<String,Integer>) recibe.getSerializableExtra("mapita");
        recibe=null;

        actualizaAvance();

        btnSuma.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                operacion="suma";
                vamonos();
            }
        });
        btnResta.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                operacion="resta";
                vamonos();
            }
        });
        btnDivision.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                envia =new Intent(getApplicationContext(),ActivityDivision.class);
                envia.putExtra("mapita",datos);
                startActivity(envia);
            }
        });
        btnMulti.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                operacion="multi";
                vamonos();
            }
        });

        btnTerminar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                terminar=new Intent(getApplicationContext(),Puntuacion.class);
                terminar.putExtra("mapita",datos);
                startActivity(terminar);
            }
        });

    }

    private void actualizaAvance(){
        int a,b,c,d;
        a=datos.get("CS");
        b=datos.get("CR");
        c=datos.get("CM");
        d=datos.get("CD");
        avanceS.setText("Intentos: "+a+"/3");
        avanceR.setText("Intentos: "+b+"/3");
        avanceM.setText("Intentos: "+c+"/3");
        avanceD.setText("Intentos: "+d+"/3");
        if(a>=3) btnSuma.setEnabled(false);
        if(b>=3) btnResta.setEnabled(false);
        if(c>=3) btnMulti.setEnabled(false);
        if(d>=3) btnDivision.setEnabled(false);
        if((a==b)&&(b==c)&&(c==d)&&(a==3)){
            terminar=new Intent(getApplicationContext(),Puntuacion.class);
            terminar.putExtra("mapita",datos);
            startActivity(terminar);
        }
    }

    private void vamonos(){
        envia =new Intent(getApplicationContext(),Base.class);
        envia.putExtra("operacion",operacion);
        envia.putExtra("mapita",datos);
        startActivity(envia);
    }



}