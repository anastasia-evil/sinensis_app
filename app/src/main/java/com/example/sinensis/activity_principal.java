package com.example.sinensis;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.MediaController;
import android.widget.TextView;
import android.widget.VideoView;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class activity_principal extends AppCompatActivity {

    public static ListView listView; //lista de actividades
    public static AppDatabase db; //base de datos en java
    public static String s_nombre;//nombre de la actividad
    public static int currentDate;
    public static Calendar calendar;


    ImageButton btn_calendario,btn_anadir,btn_ajustes;

    public static List<String> listanombres = new ArrayList<>();
    public static List<String> listadescripcion = new ArrayList<>();
    public static Adaptadores adaptador;
    public static List<Actividades> lista;
    public static int token;

    Context context;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_principal);
        //Intent intent = getIntent();

        //mostramos el nombre que hemos introducido
        String mi_nombre = MainActivity.sharedPreferences.getString("nombre", "");
        TextView textView = findViewById(R.id.textonombre);
        textView.setText(getString(R.string.bienvenida,mi_nombre));
        //Botones
        btn_calendario = (ImageButton) findViewById(R.id.calendario);
        btn_ajustes = (ImageButton) findViewById(R.id.ajustes);

        Intent intentC = new Intent(this,activity_calendario.class);
        Intent intentA = new Intent(this, activity_ajustes.class);
        btn_calendario.setOnClickListener(new View.OnClickListener() {
            @Override
            //Lanzar actividad de calendario
            public void onClick(View view) {
                startActivity(intentC);
            }
        });
        btn_ajustes.setOnClickListener(new View.OnClickListener() {
            @Override
            //Lanzar actividad de ajustes
            public void onClick(View view) {
                startActivity(intentA);
            }
        });

        //db = AppDatabase.getInstance(getApplicationContext()); // le damos valores de inicio a db
        listView = findViewById(R.id.list_view);



        //Que salgan las listas de actividades dependiendo de nuestra edad, nivel (grado) y mentor
        int edad = activity_datos.edad_datos;
        int nivel = activity_datos.grado_datos;
        int mentor = activity_mentores.mentor_datos;
        int n = 0;

        //restriccion para el nivel

        if(MainActivity.p == 1){
            lista = activity_mentores.lista_actividades;
        }

        adaptador = new Adaptadores(this, lista);
        listView.setAdapter(adaptador);

        //que nos lleve a la acyividad_lista
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {
                SharedPreferences sharedPreferences = getSharedPreferences("check",MODE_PRIVATE);
                Actividades a = (Actividades) adaptador.getItem(position);
                Intent intentLista = new Intent(view.getContext(), activity_actividadLista.class);
                //parametros, nosotros tenemos titulo, imagen y descripcion(pongo de momento la de siempre)

                intentLista.putExtra("TIT", a.getNombre());
                intentLista.putExtra("DES", a.getDescripcion_larga());
                intentLista.putExtra("POS", position);
                startActivity(intentLista);

            }
        });


        btn_anadir = (ImageButton) findViewById(R.id.button_eleccion);
        Intent anadir = new Intent(this, activity_actividades.class);
        btn_anadir.setOnClickListener(new View.OnClickListener() {
            @Override
            //Lanzar actividad de lista de actividades
            public void onClick(View view) { startActivity(anadir); }
        });

        SharedPreferences prefs = getSharedPreferences("MyPref", MODE_PRIVATE);
        calendar = Calendar.getInstance();
        currentDate = calendar.get(Calendar.DAY_OF_MONTH);
        Intent intent_calendario = new Intent(this, activity_calendario.class);

        if(currentDate != prefs.getInt("lastShownDate", 0)){
            // Mostrar el AlertDialog
            AlertDialog.Builder builder = new AlertDialog.Builder(this);
            builder.setMessage(getString(R.string.mensaje))
                    .setPositiveButton(getString(R.string.aceptar), new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int id) {
                            SharedPreferences.Editor editor2 = MainActivity.sharedPreferences.edit();
                            int h = MainActivity.sharedPreferences.getInt("hojas",0);
                            h = h + 5;
                            editor2.putInt("hojas", h); // Guarda el nuevo valor de 'hojas'
                            editor2.apply();
                            activity_ajustes.m = Integer.toString(MainActivity.sharedPreferences.getInt("hojas",0));
                            // Guardar la fecha actual como última fecha mostrada
                            SharedPreferences.Editor editor = prefs.edit();
                            editor.putInt("lastShownDate", currentDate);
                            editor.apply();
                            startActivity(intent_calendario);
                        }
                    });
            AlertDialog alert = builder.create();
            alert.show();
        }


    }





}