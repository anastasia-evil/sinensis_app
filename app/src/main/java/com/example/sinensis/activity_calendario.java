package com.example.sinensis;

import androidx.appcompat.app.AppCompatActivity;

import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.CalendarView;
import android.widget.ImageButton;
import android.widget.SeekBar;
import android.widget.TextView;

import java.util.Calendar;

public class activity_calendario extends AppCompatActivity {

    ImageButton feliz, regular, mal;

    SeekBar progreso;
    TextView texto_estado;
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
        texto_estado = findViewById(R.id.ficha_progreso);


        valorActual = progreso.getProgress();
        progreso.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                return true; // desactiva el control táctil de la SeekBar
            }
        });

        SharedPreferences sharedPreferences = getSharedPreferences("estado_animo", MODE_PRIVATE);
        Calendar calendar = Calendar.getInstance();
        int today = calendar.get(Calendar.DAY_OF_YEAR);

        feliz.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int lastDayClicked = sharedPreferences.getInt("ultimoDia", -1);
                if (lastDayClicked != today) {
                    valorActual++;
                    progreso.setProgress(valorActual);
                    texto_estado.setText(getString(R.string.estado_bien));
                    sharedPreferences.edit().putInt("ultimoDia", today).apply();
                } else {

                }
            }
        });
        regular.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int lastDayClicked = sharedPreferences.getInt("ultimoDia", -1);
                if (lastDayClicked != today) {
                    progreso.setProgress(valorActual);
                    texto_estado.setText(getString(R.string.estado_bien));
                    sharedPreferences.edit().putInt("ultimoDia", today).apply();
                } else {

                }
            }
        });
        mal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int lastDayClicked = sharedPreferences.getInt("ultimoDia", -1);
                if (lastDayClicked != today) {
                    valorActual++;
                    progreso.setProgress(valorActual);
                    texto_estado.setText(getString(R.string.estado_bien));
                    sharedPreferences.edit().putInt("ultimoDia", today).apply();
                } else {

                }

            }
        });

        if(valorActual == 30){
            SharedPreferences.Editor editor2 = MainActivity.sharedPreferences.edit();
            int h = MainActivity.sharedPreferences.getInt("hojas",0);
            h = h + 1;
            editor2.putInt("hojas", h); // Guarda el nuevo valor de 'hojas'
            editor2.apply();
            activity_ajustes.m = Integer.toString(MainActivity.sharedPreferences.getInt("hojas",0));
        }

    }

}