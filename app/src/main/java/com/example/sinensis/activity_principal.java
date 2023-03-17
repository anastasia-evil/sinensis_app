package com.example.sinensis;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.List;

public class activity_principal extends AppCompatActivity {

    private AppDatabase db; // una variable vacia donde podremos meter las actividades

    private ListView listView; //lista de actividades

    ImageButton btn_calendario,btn_home,btn_ajustes;
    public static List<Actividades> lista_actividades = new ArrayList<>();
    public static List<String> listanombres = new ArrayList<>();
    public static List<String> listadescripcion = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_principal);

        //mostramos el nombre que hemos introducido
        Intent intent = getIntent();
        String message = intent.getStringExtra("nombre");
        TextView textView = findViewById(R.id.textonombre);
        textView.setText("Hola " + message + "!");

        //Botones
        btn_calendario = (ImageButton) findViewById(R.id.calendario);
        btn_home = (ImageButton) findViewById(R.id.home);
        btn_ajustes = (ImageButton) findViewById(R.id.ajustes);

        Intent intentC = new Intent(this, activity_calendario.class);
        Intent intentH = new Intent(this, activity_principal.class);
        Intent intentA = new Intent(this, activity_ajustes.class);
        btn_calendario.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(intentC);
            }
        });
        btn_home.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                startActivity(intentH);
                startActivity(intent);
            }
        });
        btn_ajustes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(intentA);
            }
        });

        db = AppDatabase.getInstance(getApplicationContext()); // le damos valos de inicio a db
        listView = findViewById(R.id.list_view);

        listView.setOnItemClickListener((adapter, view, pos, id) -> {
            Actividades actividades = (Actividades) adapter.getAdapter().getItem(pos);

            Intent intent_mapa = new Intent(view.getContext(), activity_mapa.class);
            intent.putExtra("id", actividades.id);
            startActivity(intent_mapa);
        });


        //vamos a hacer que nos salgan las listas de actividades dependiendo de nuestra edad, nievl (grado) y mentor
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


        lista_actividades = db.ActividadesDAO().selectactividad(e,n,mentor);
        ArrayAdapter<Actividades> listAdapter = new ArrayAdapter<>(listView.getContext(),
                android.R.layout.simple_list_item_1, lista_actividades);
        listView.setAdapter(listAdapter);

        listanombres = db.ActividadesDAO().getNombresActividades(e,n,mentor);
        listadescripcion = db.ActividadesDAO().getDescripcionActividades(e,n,mentor);

        Adaptadores adaptador = new Adaptadores(this, R.layout.listas_items);





    }

}
