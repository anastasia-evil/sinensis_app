package com.example.sinensis;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlarmManager;
import android.app.DatePickerDialog;
import android.app.PendingIntent;
import android.app.TimePickerDialog;
import android.content.ContentResolver;
import android.content.ContentUris;
import android.content.Context;
import android.content.Intent;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.provider.CalendarContract;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.DatePicker;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.MediaController;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;
import android.widget.VideoView;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;

public class activity_actividadLista extends AppCompatActivity {

    private Button btn_fecha,btn_hora;

    private Calendar fecha, hora;
    private Button btn_anadirA;

    private TextView titulo;
    private TextView descripcion;

    private ImageButton btn_link;

    private SeekBar seekBarVol;
    Button btn_eliminar_actividad;



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
        //imagen = findViewById(R.id.imagen_activity);
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

        btn_eliminar_actividad = (Button) findViewById(R.id.boton_eliminar);
        btn_eliminar_actividad.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                eliminar(activity_principal.lista_actividades, titulo);
                activity_principal.adaptador.notifyDataSetChanged(); // para actualizar el adaptador
                Toast toast = Toast.makeText(activity_actividadLista.this, "Actividad eliminada", Toast.LENGTH_SHORT);
                toast.show();

            }
        });

        btn_link = (ImageButton) findViewById(R.id.boton_link);
        String nombreActividad = titulo.getText().toString();
        elegirFoto(btn_link);
        seekBarVol = (SeekBar) findViewById(R.id.seekBar_vol);

        if(nombreActividad.equals("Respiraciones guiadas") || nombreActividad.equals("Sonidos Relajantes") || nombreActividad.equals("Audioguia")){
            btn_link.setVisibility(View.VISIBLE);
            seekBarVol.setVisibility(View.VISIBLE);
            if(nombreActividad.equals("Respiraciones guiadas")){
                MediaPlayer mp = MediaPlayer.create(this, R.raw.respiraciones);
                audio(mp);
            }else if(nombreActividad.equals("Sonidos Relajantes")){
                MediaPlayer mp = MediaPlayer.create(this, R.raw.sonidosrelajantes);
                audio(mp);

            }else if(nombreActividad.equals("Audioguia")){
                MediaPlayer mp = MediaPlayer.create(this, R.raw.audioguia);
                audio(mp);

            }



        }








    }

    public void eliminar(List<Actividades> a, TextView t){
        String nombreActividad = t.getText().toString();
        for(int i = 0; i<a.size(); i++){
            Actividades actividad = a.get(i);
            if (actividad.getNombre().equals(nombreActividad)) {
                //activity_principal.db.ActividadesDAO().delete(actividad); si lo hago con esto se borra de todo.
                a.remove(actividad);
                break;
            }
        }


    }

    public void audio(MediaPlayer mp){
        AudioManager audioManager = (AudioManager) getSystemService(AUDIO_SERVICE);
        int max_vol = audioManager.getStreamMaxVolume(AudioManager.STREAM_MUSIC);
        int currentVolume = audioManager.getStreamVolume(AudioManager.STREAM_MUSIC);



        btn_link.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(mp.isPlaying()){
                    mp.pause();
                    Toast.makeText(activity_actividadLista.this, "Pausa", Toast.LENGTH_SHORT).show();
                }else{
                    mp.start();
                    Toast.makeText(activity_actividadLista.this, "Play", Toast.LENGTH_SHORT).show();
                }
            }
        });

        seekBarVol.setMax(max_vol);
        seekBarVol.setProgress(currentVolume);
        seekBarVol.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int i, boolean b) {
                //papa
                audioManager.setStreamVolume(AudioManager.STREAM_MUSIC, i ,0 );

            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

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
        CheckBox check = activity_ajustes.checkSi;
        // Convertir fechas y horas a milisegundos
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy HH:mm");
        Date startDate = null;
        try {
            startDate = sdf.parse(beginDate + " " + beginHour);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        long startTimeInMillis = startDate.getTime();
        TextView titulo = findViewById(R.id.titulo_activity);
        // Crear Intent para agregar evento al calendario
        Intent intent = new Intent(Intent.ACTION_INSERT)
                .setData(CalendarContract.Events.CONTENT_URI)
                .putExtra(CalendarContract.Events.TITLE, titulo.getText().toString())
                .putExtra(CalendarContract.EXTRA_EVENT_BEGIN_TIME,startTimeInMillis)
                .putExtra(CalendarContract.Reminders.METHOD, CalendarContract.Reminders.METHOD_ALERT); // Notificación de alerta
        if(check.isChecked()){
            intent.putExtra(CalendarContract.Reminders.MINUTES, 5);
        }
        if (intent.resolveActivity(getPackageManager()) != null) {
            startActivity(intent);

        }

    }
    public void elegirFoto(ImageButton botonLink){
        String nombreActividad = titulo.getText().toString();
        LinearLayout linearLayout = (LinearLayout) findViewById(R.id.linear1);
        int id = 0;
        String url = "";
        switch (nombreActividad){
            case "Correr":
            case "Caminar":
            case "Ir a un spa":
                id = R.drawable.googlemaps;
                url="mapa";
                linearLayout.removeView(seekBarVol);
                break;
            case "Estiramientos":
                id = R.drawable.youtube;
                url = "https://www.youtube.com/watch?v=BaPLtt2w3AM&list=PLNH7cFJ42PKgirbDO9op6TMj8nvjaqlvJ" ;
                linearLayout.removeView(seekBarVol);
                break;
            case "Yoga":
                id = R.drawable.youtube;
                url = "https://www.youtube.com/watch?v=a01D1PzTVFc&list=PLNH7cFJ42PKi-Gziz-jaNHj-JgLWrLt0r" ;
                linearLayout.removeView(seekBarVol);
                break;
            case "Escuchar música":
                id = R.drawable.spotify;
                url = "https://open.spotify.com/";
                linearLayout.removeView(seekBarVol);
                break;
            case "Libros de autoayuda":
                id = R.drawable.libro;
                url = "https://lamenteesmaravillosa.com/los-9-mejores-libros-de-autoayuda-y-superacion-personal/";
                linearLayout.removeView(seekBarVol);
                break;
            case "Respiración con postura de Loto":
                id = R.drawable.loto;
                url = "https://www.youtube.com/watch?v=5LBtBzi8Djg";
                linearLayout.removeView(seekBarVol);
                break;
            case "Cocinar":
                id = R.drawable.hornear;
                url = "https://www.recetasgratis.net/";
                linearLayout.removeView(seekBarVol);
                break;
            case "Ir al cine o al teatro":
                id = R.drawable.cartelera;
                url = "https://www.filmaffinity.com/es/cat_new_th_es.html";
                linearLayout.removeView(seekBarVol);
                break;
            case "Respiraciones guiadas":
            case "Sonidos Relajantes":
            case "Audioguia":
                id = R.drawable.play;
                break;
            default:
                LinearLayout linearLayout1 = (LinearLayout) findViewById(R.id.linear);
                linearLayout1.removeView(btn_link);
                linearLayout.removeView(seekBarVol);
        }
        if (id != 0 || !url.isEmpty()) {
            botonLink.setVisibility(View.VISIBLE);
            botonLink.setImageResource(id);
            String finalUrl = url;
            botonLink.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(finalUrl));
                    startActivity(intent);
                }
            });
        }
        if(url.equals("mapa")){
            botonLink.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent intent = new Intent(view.getContext(), activity_mapa.class);
                    startActivity(intent);
                }
            });
        }

    }




}