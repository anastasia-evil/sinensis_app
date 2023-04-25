package com.example.sinensis;

import androidx.appcompat.app.AppCompatActivity;

import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CalendarView;
import android.widget.ImageButton;
import android.widget.SeekBar;

public class activity_calendario extends AppCompatActivity {

    ImageButton feliz, regular, mal;

    SeekBar progreso;
    public static int valorActual;

    private static int color;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calendario);

        feliz = findViewById(R.id.button_bien);
        regular = findViewById(R.id.button_regular);
        mal = findViewById(R.id.button_mal);
        progreso = findViewById(R.id.seekBar_progreso);

        valorActual = progreso.getProgress();



        feliz.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                valorActual++;

            }
        });
        regular.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                valorActual=valorActual;
            }
        });
        mal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                valorActual--;


            }
        });

    }

}