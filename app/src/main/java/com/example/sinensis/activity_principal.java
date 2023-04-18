package com.example.sinensis;

import android.content.Context;
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

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.List;

public class activity_principal extends AppCompatActivity {

    public static ListView listView; //lista de actividades
    public static AppDatabase db; //base de datos en java
    public static String s_nombre;//nombre de la actividad


    ImageButton btn_calendario,btn_anadir,btn_ajustes;

    public static List<String> listanombres = new ArrayList<>();
    public static List<String> listadescripcion = new ArrayList<>();
    public static Adaptadores adaptador;
    public static List<Actividades> lista;

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

        Intent intentA = new Intent(this, activity_ajustes.class);
        btn_calendario.setOnClickListener(new View.OnClickListener() {
            @Override
            //Lanzar actividad de calendario
            public void onClick(View view) {
                Intent intent = new Intent(Intent.ACTION_VIEW);
                intent.setData(Uri.parse(getString(R.string.enlace_calendario)));
                startActivity(intent);
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


    }



}