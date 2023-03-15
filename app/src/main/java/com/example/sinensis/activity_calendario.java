package com.example.sinensis;

import static com.example.sinensis.activity_datos.nombre;

import android.app.TimePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.CalendarView;
import android.widget.ImageButton;
import android.widget.TimePicker;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import java.util.Calendar;
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
        Intent intent1 = new Intent(this, activity_principal.class);
        intent1.putExtra("nombre", nombre.getText().toString());
        btn_calendario.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(activity_calendario.this, activity_principal.class);
                startActivity(intent);
                startActivity(intent1);
                finish();
            }
        });

        //calendario
        calendar = (CalendarView) findViewById(R.id.calendarView);

        calendar.setOnDateChangeListener(new CalendarView.OnDateChangeListener() {
            @Override
            public void onSelectedDayChange(@NonNull CalendarView calendarView, int day, int month, int year) {
                String fecha = year + "/" + month + "/" + day;
                Toast.makeText(activity_calendario.this, fecha, Toast.LENGTH_SHORT).show();
            }
        });

    }







}