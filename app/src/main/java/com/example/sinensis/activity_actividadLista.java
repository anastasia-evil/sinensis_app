package com.example.sinensis;

import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.ImageButton;
import android.widget.ImageView;

import java.util.Calendar;

public class activity_actividadLista extends AppCompatActivity {



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_actividad_lista);
        Button btn_fechaI = (Button) findViewById(R.id.btn_fechaI);
        Button btn_fechaF = (Button) findViewById(R.id.btn_fechaF);
        Button btn_anadirA = (Button) findViewById(R.id.btn_anadirA);

        btn_fechaI.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });

        btn_fechaF.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });

        btn_anadirA.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });

       ImageButton btn_calendario = (ImageButton) findViewById(R.id.calendario);
       ImageButton btn_home = (ImageButton) findViewById(R.id.home);
       ImageButton btn_ajustes = (ImageButton) findViewById(R.id.ajustes);

        Intent intentH = new Intent(this, activity_principal.class);
        Intent intentA = new Intent(this, activity_ajustes.class);
        btn_calendario.setOnClickListener(new View.OnClickListener() {
            @Override
            //Lanzar actividad de calendario
            public void onClick(View view) {
                Intent intent = new Intent(Intent.ACTION_VIEW);
                intent.setData(Uri.parse("https://calendar.google.com"));
                startActivity(intent);
            }
        });
        btn_home.setOnClickListener(new View.OnClickListener() {
            @Override
            //Lanzar actividad de lista de actividades
            public void onClick(View view) {

                startActivity(intentH); }
        });
        btn_ajustes.setOnClickListener(new View.OnClickListener() {
            @Override
            //Lanzar actividad de ajustes
            public void onClick(View view) {
                startActivity(intentA);
            }
        });

    }



}