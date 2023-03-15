package com.example.sinensis;

import static android.provider.AlarmClock.EXTRA_MESSAGE;

import static com.example.sinensis.activity_datos.nombre;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.google.android.material.snackbar.Snackbar;

public class activity_mentores extends AppCompatActivity {

    Button boton,boton2,boton3;
    public static int mentor_datos;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mentores);

        boton = (Button) findViewById(R.id.button_ines);
        boton2 = (Button) findViewById(R.id.button_paula);
        boton3 = (Button) findViewById(R.id.button_jimena);

        Intent intent = new Intent(this, activity_principal.class);
        Intent intent1 = new Intent(this, activity_principal.class);
        intent1.putExtra("nombre", nombre.getText().toString());
        boton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mentor_datos = 0;
                startActivity(intent);
                startActivity(intent1);
            }
        });
        boton2.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                mentor_datos = 1;

                startActivity(intent);
                startActivity(intent1);

            }
        });

        boton3.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                mentor_datos = 2;

                startActivity(intent);
                startActivity(intent1);

            }
        });
    }
}