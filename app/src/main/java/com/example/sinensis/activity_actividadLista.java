package com.example.sinensis;

import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.provider.CalendarContract;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.ImageButton;
import android.widget.ImageView;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

public class activity_actividadLista extends AppCompatActivity {

    private Button btn_fechaI,btn_fechaF;
    private Calendar fechaInicio, fechaFinal;
    private Button btn_anadirA;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_actividad_lista);
        btn_fechaI = (Button) findViewById(R.id.btn_fechaI);
        btn_fechaF = (Button) findViewById(R.id.btn_fechaF);
        btn_anadirA = (Button) findViewById(R.id.btn_anadirA);
        fechaInicio = Calendar.getInstance();
        fechaFinal = Calendar.getInstance();

        btn_fechaI.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                elegirFecha(btn_fechaI,fechaInicio);
            }
        });

        btn_fechaF.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                elegirFecha(btn_fechaF,fechaFinal);

            }
        });

        btn_anadirA.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Obtener valores de los botones
                String date1 = btn_fechaI.getText().toString();
                String date2 = btn_fechaF.getText().toString();
                try {
                    addEvent(date1,date2);
                } catch (ParseException e) {
                    throw new RuntimeException(e);
                }
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
            public void onClick(View view) {startActivity(intentH); }
        });
        btn_ajustes.setOnClickListener(new View.OnClickListener() {
            @Override
            //Lanzar actividad de ajustes
            public void onClick(View view) {
                startActivity(intentA);
            }
        });

    }

    public void elegirFecha(Button boton, Calendar calendar){
        DatePickerDialog dpd = new DatePickerDialog(this, new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker datePicker, int day, int month, int year) {
                calendar.set(Calendar.YEAR, year);
                calendar.set(Calendar.MONTH, month);
                calendar.set(Calendar.DAY_OF_MONTH, day);

                SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
                String dateString = sdf.format(calendar.getTime());
                boton.setText(dateString);
            }
        }, calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH), calendar.get(Calendar.DAY_OF_MONTH));
        dpd.show();
    }

    public void addEvent(String beginDate, String endDate) throws ParseException {
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        Date date = sdf.parse(beginDate);
        Date date1 = sdf.parse(endDate);
        long startDateMillis = date.getTime();
        long endDateMillis = date1.getTime();

        // Crear Intent para agregar evento al calendario
        Intent intent = new Intent(Intent.ACTION_INSERT)
                .setData(CalendarContract.Events.CONTENT_URI)
                .putExtra(CalendarContract.EXTRA_EVENT_BEGIN_TIME,startDateMillis)
                .putExtra(CalendarContract.EXTRA_EVENT_END_TIME,endDateMillis);
        if (intent.resolveActivity(getPackageManager()) != null) {
            startActivity(intent);
        }

    }



}