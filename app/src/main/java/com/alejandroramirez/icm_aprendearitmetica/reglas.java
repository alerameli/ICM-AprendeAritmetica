package com.alejandroramirez.icm_aprendearitmetica;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class reglas extends AppCompatActivity {

    private Button btn;
    public Intent envia;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reglas);

        btn=(Button) findViewById(R.id.button);

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                envia=new Intent(getApplicationContext(),MainActivity.class);
                startActivity(envia);
            }
        });



    }
}