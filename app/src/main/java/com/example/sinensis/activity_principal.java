package com.example.sinensis;

import android.content.Context;
import android.content.Intent;
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

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.List;

public class activity_principal extends AppCompatActivity {

    public static ListView listView; //lista de actividades
    public static AppDatabase db; //base de datos en java
    public static String s_nombre;//nombre de la actividad


    ImageButton btn_calendario,btn_home,btn_ajustes;

    public static List<String> listanombres = new ArrayList<>();
    public static List<String> listadescripcion = new ArrayList<>();
    public static Adaptadores adaptador;
    public static List<Actividades> lista;
    Button btn_anadir;

    Context context;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_principal);
        //Intent intent = getIntent();

        //mostramos el nombre que hemos introducido
        String mi_nombre = activity_datos.nombre_datos;
        TextView textView = findViewById(R.id.textonombre);
        textView.setText("Hola " + mi_nombre + "!. Estas son las actividades añadidas a tu plan. Seleccione alguna de ellas para empezar y añadirlas a tus recordatorios");

        //Botones
        btn_calendario = (ImageButton) findViewById(R.id.calendario);
        btn_home = (ImageButton) findViewById(R.id.home);
        btn_ajustes = (ImageButton) findViewById(R.id.ajustes);

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
            public void onClick(View view) { startActivity(intentH); }
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

        listView.setOnItemClickListener((adapter, view, pos, id) -> {

            //Esta sección se utilizará posteriormente para que al hacer click en una, se inicie el ejercicio

            Actividades actividades = (Actividades) adapter.getAdapter().getItem(pos);

            //Intent intent_mapa = new Intent(view.getContext(), activity_mapa.class);
            //intent.putExtra("id", actividades.id);
            //startActivity(intent_mapa);

            //si pulsamos nos lleva a la descripcion mas clara de la actividad.


        });

        //Que salgan las listas de actividades dependiendo de nuestra edad, nivel (grado) y mentor
        int edad = activity_datos.edad_datos;
        int nivel = activity_datos.grado_datos;
        int mentor = activity_mentores.mentor_datos;
        int e = 0;
        int n = 0;

        //restriccion de edad
        if(edad<= 50){
            e = 0; //niños y adultos
        }else if(edad> 50 && edad<=99){
            e = 0; //gente mayor
        }else if(edad > 0 && edad<99){
            e = 0; //todos
        }

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
                Actividades a = (Actividades) adaptador.getItem(position);
                Intent intentLista = new Intent(view.getContext(), activity_actividadLista.class);
                //parametros, nosotros tenemos titulo, imagen y descripcion(pongo de momento la de siempre)

                intentLista.putExtra("TIT", a.getNombre());
                intentLista.putExtra("DES", a.getDescripcion_larga());
                startActivity(intentLista);
            }
        });


        btn_anadir = (Button) findViewById(R.id.button_eleccion);
        Intent anadir = new Intent(this, activity_actividades.class);
        btn_anadir.setOnClickListener(new View.OnClickListener() {
            @Override
            //Lanzar actividad de lista de actividades
            public void onClick(View view) { startActivity(anadir); }
        });


    }



}