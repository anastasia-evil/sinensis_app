package com.example.sinensis;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.List;

public class activity_principal extends AppCompatActivity {

    private ListView listView; //lista de actividades
    public static AppDatabase db; //base de datos en java

    ImageButton btn_calendario,btn_home,btn_ajustes;
    public static List<Actividades> lista_actividades = new ArrayList<>();
    public static List<String> listanombres = new ArrayList<>();
    public static List<String> listadescripcion = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_principal);
        //Intent intent = getIntent();

        //mostramos el nombre que hemos introducido
        String mi_nombre = activity_datos.nombre_datos;
        TextView textView = findViewById(R.id.textonombre);
        textView.setText("Hola " + mi_nombre + "!");

        //Botones
        btn_calendario = (ImageButton) findViewById(R.id.calendario);
        btn_home = (ImageButton) findViewById(R.id.home);
        btn_ajustes = (ImageButton) findViewById(R.id.ajustes);

        Intent intentH = new Intent(this, activity_actividades.class);
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

        db = AppDatabase.getInstance(getApplicationContext()); // le damos valores de inicio a db
        listView = findViewById(R.id.list_view);

        listView.setOnItemClickListener((adapter, view, pos, id) -> {

            //Esta sección se utilizará posteriormente para que al hacer click en una, se inicie el ejercicio

            Actividades actividades = (Actividades) adapter.getAdapter().getItem(pos);

            //Intent intent_mapa = new Intent(view.getContext(), activity_mapa.class);
            //intent.putExtra("id", actividades.id);
            //startActivity(intent_mapa);

            Intent intent_activity = new Intent(view.getContext(),activity_actividadLista.class);
            startActivity(intent_activity);
        });

        //Que salgan las listas de actividades dependiendo de nuestra edad, nivel (grado) y mentor
        int edad = activity_datos.edad_datos;
        int nivel = activity_datos.grado_datos;
        int mentor = activity_mentores.mentor_datos;
        int e = 0;
        int n = 0;

        //restriccion de edad
        if(edad<= 50){
            e = 2; //niños y adultos
        }else if(edad> 50 && edad<=99){
            e = 1; //gente mayor
        }else if(edad > 0 && edad<99){
            e = 2; //todos
        }

        //restriccion para el nivel
        if(nivel == 0 || nivel == 1){
            n = 0;
        }else if(nivel == 2 || nivel == 3){
            n = 1;
        }else{
            n = 2;
        }

        //lista_actividades = db.ActividadesDAO().selectactividad(e,n,mentor); coment
        /*ArrayAdapter<Actividades> listAdapter = new ArrayAdapter<>(listView.getContext(),
                android.R.layout.simple_list_item_1, lista_actividades);
        listView.setAdapter(listAdapter);*/

        //listanombres = db.ActividadesDAO().getNombresActividades(e,n,mentor);
        //listadescripcion = db.ActividadesDAO().getDescripcionActividades(e,n,mentor);

        Adaptadores adaptador = new Adaptadores(this, Getlista(e,n,mentor), null);
        listView.setAdapter(adaptador);
    }
    private List<Actividades> Getlista(int edad, int nivel, int mentor) {
        List<Actividades> lista = new ArrayList<>();
        lista = db.ActividadesDAO().selectactividad(edad,nivel,mentor);

        return lista;
    }

}
