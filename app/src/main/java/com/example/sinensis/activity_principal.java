package com.example.sinensis;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;

public class activity_principal extends AppCompatActivity {

    Button boton_cambiarM;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_principal);

        Intent intent = getIntent();
        String s = intent.getStringExtra("mentor");
        TextView texto= (TextView) findViewById(R.id.texto_mentor);
        texto.setText(s);

        boton_cambiarM = (Button) findViewById(R.id.boton_cambiar);

        boton_cambiarM.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });


    }
}
