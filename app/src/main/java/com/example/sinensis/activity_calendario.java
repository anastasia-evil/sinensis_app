package com.example.sinensis;

import androidx.appcompat.app.AppCompatActivity;

import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CalendarView;
import android.widget.ImageButton;

public class activity_calendario extends AppCompatActivity {

    ImageButton feliz, regular, mal;
    private CalendarView calendarView;

    private static int color;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calendario);

        feliz = findViewById(R.id.button_bien);
        regular = findViewById(R.id.button_regular);
        mal = findViewById(R.id.button_mal);
        calendarView = findViewById(R.id.calendario);
        long dia = calendarView.getDate();

        feliz.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                color = R.color.red;
                calendarView.setDateTextAppearance(color);




            }
        });
        regular.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                color = R.color.fondo_carga;
                calendarView.setDateTextAppearance(Long.valueOf(dia).intValue());
            }
        });
        mal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                color = R.color.red;
                calendarView.setDateTextAppearance(Long.valueOf(dia).intValue());
            }
        });

    }

}