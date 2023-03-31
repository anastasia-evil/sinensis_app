package com.example.sinensis;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlarmManager;
import android.app.DatePickerDialog;
import android.app.PendingIntent;
import android.app.TimePickerDialog;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.provider.CalendarContract;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.TimePicker;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

public class activity_actividadLista extends AppCompatActivity {

    private Button btn_fecha,btn_hora;

    private Calendar fecha, hora;
    private Button btn_anadirA;

    private TextView titulo;
    private TextView descripcion;
    private ImageView imagen;

    private Context context;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_actividad_lista);
        btn_fecha = (Button) findViewById(R.id.btn_fecha);
        btn_hora = (Button) findViewById(R.id.btn_hora);
        btn_anadirA = (Button) findViewById(R.id.btn_anadirA);
        fecha = Calendar.getInstance();
        hora = Calendar.getInstance();

        btn_fecha.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                elegirFecha(btn_fecha,fecha);
            }
        });

        btn_hora.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                elegirHora(btn_hora,hora);

            }
        });

        btn_anadirA.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Obtener valores de los botones
                String date1 = btn_fecha.getText().toString();
                String date2 = btn_hora.getText().toString();
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

        //que se guarde el nombre y la descripcion

        titulo = findViewById(R.id.titulo_activity);
        descripcion = findViewById(R.id.descripcion_activity);
        imagen = findViewById(R.id.imagen_activity);
        Intent i = getIntent();
        Bundle b = i.getExtras();

        if(b!= null){
            titulo.setText(b.getString("TIT"));
            descripcion.setText(b.getString("DES"));
            //para la imagen lo haremos diferente:
            /*String ruta = activity_principal.db.ActividadesDAO().foto(activity_principal.s_nombre);
            String nombreArchivo = ruta.substring(0, ruta.lastIndexOf(".")); // Elimina la extensión ".png" del nombre de archivo
            int id = context.getResources().getIdentifier(nombreArchivo, "drawable", context.getPackageName()); // Obtiene el ID de recurso de la imagen sin la extensión
            imagen.setImageResource(id);*/

        }

    }






    public void elegirFecha(Button boton, Calendar calendar){
        DatePickerDialog dpd = new DatePickerDialog(this, new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker datePicker, int day, int month, int year) {
                calendar.set(Calendar.YEAR, year);
                calendar.set(Calendar.MONTH, month);
                calendar.set(Calendar.DAY_OF_MONTH, day);

                String date = year + "/" + (month+1) + "/" + day;
                boton.setText(date);
            }
        }, calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH), calendar.get(Calendar.DAY_OF_MONTH));
        dpd.show();
    }

    public void elegirHora(Button boton, Calendar calendar){
        TimePickerDialog tpd = new TimePickerDialog(this, new TimePickerDialog.OnTimeSetListener() {
            @Override
            public void onTimeSet(TimePicker timePicker, int hour, int minute) {
                calendar.set(Calendar.HOUR_OF_DAY,hour);
                calendar.set(Calendar.MINUTE,minute);

                SimpleDateFormat sdf = new SimpleDateFormat("HH:mm", Locale.getDefault());
                String timeString = sdf.format(calendar.getTime());
                boton.setText(timeString);
            }
        },calendar.get(Calendar.HOUR_OF_DAY), calendar.get(Calendar.MINUTE),true);
        tpd.show();
    }

    public void addEvent(String beginDate, String beginHour) throws ParseException {
        // Convertir fechas y horas a milisegundos
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy HH:mm");
        Date startDate = null;
        try {
            startDate = sdf.parse(beginDate + " " + beginHour);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        long startTimeInMillis = startDate.getTime();
        // Crear Intent para agregar evento al calendario
        Intent intent = new Intent(Intent.ACTION_INSERT)
                .setData(CalendarContract.Events.CONTENT_URI)
                .putExtra(CalendarContract.EXTRA_EVENT_BEGIN_TIME,startTimeInMillis);
        if (intent.resolveActivity(getPackageManager()) != null) {
            startActivity(intent);

        }

    }



}