package com.example.sinensis;

import static android.provider.AlarmClock.EXTRA_MESSAGE;

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
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mentores);

        boton = (Button) findViewById(R.id.button_ines);
        boton2 = (Button) findViewById(R.id.button_paula);
        boton3 = (Button) findViewById(R.id.button_jimena);
        boton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                Intent intent = new Intent(activity_mentores.this, activity_principal.class);
                startActivity(intent);

            }

        });
        boton2.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {

                Intent intent = new Intent(activity_mentores.this,activity_principal.class);
                startActivity(intent);

            }
        });

        boton3.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                Intent intent = new Intent(activity_mentores.this, activity_principal.class);
                startActivity(intent);

            }
        });
    }

    /*public void onClick(View view){
        boolean visto = ((Button)view).isClickable();
        Button boton1,boton2,boton3;

        switch (view.getId()){

            case R.id.button_ines:
                if(visto){

                }
                break;

            case R.id.button_paula:
                if(visto){
                    Snackbar mySnackbar = Snackbar.make(view,"Has elegido a Paula", 100);
                    mySnackbar.show();

                }
                break;

            case R.id.button_jimena:
                if(visto){
                    Snackbar mySnackbar = Snackbar.make(view,"Has elegido a Jimena", 100);
                    mySnackbar.show();

                }
                break;
        }
    }*/



}