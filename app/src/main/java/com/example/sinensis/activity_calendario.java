package com.example.sinensis;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.CalendarView;
import android.widget.ImageButton;
import android.widget.TimePicker;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import java.util.List;
import java.util.concurrent.TimeoutException;

public class activity_calendario extends AppCompatActivity {

    ImageButton btn_calendario;
    CalendarView calendar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calendario);

        btn_calendario = (ImageButton) findViewById(R.id.flechaC);
        btn_calendario.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(activity_calendario.this, activity_principal.class);
                startActivity(intent);
                finish();
            }
        });

        calendar = (CalendarView) findViewById(R.id.calendarView);
        calendar.setOnDateChangeListener(new CalendarView.OnDateChangeListener() {
            @Override
            public void onSelectedDayChange(@NonNull CalendarView calendarView, int day, int month, int year) {
                String fecha = year + "/" + month + "/" + day;
                Toast.makeText(activity_calendario.this, fecha, Toast.LENGTH_LONG).show();
            }
        });

    }

    /*public void flechaC(View view){
        btn_calendario.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(activity_calendario.this, activity_principal.class);
                startActivity(intent);
                finish();
            }
        });
    }*/




}