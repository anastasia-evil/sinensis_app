package com.example.sinensis;

import static com.example.sinensis.activity_datos.nombre;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.List;

public class activity_mentores extends AppCompatActivity {

    public static List<Actividades> lista_actividades = new ArrayList<>();

    public static AppDatabase db; //base de datos en java
    Button boton,boton2,boton3;
    public static int mentor_datos;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mentores);

        boton = (Button) findViewById(R.id.button_ines);
        boton2 = (Button) findViewById(R.id.button_paula);
        boton3 = (Button) findViewById(R.id.button_jimena);

        db = AppDatabase.getInstance(getApplicationContext());
        Intent intent = new Intent(this, activity_principal.class);
        Intent intent1 = new Intent(this, activity_principal.class);
        intent1.putExtra("nombre", nombre.getText().toString());


        Gson gson = new Gson();

        // Convertir la lista de objetos a una cadena de texto JSON

        boton.setOnClickListener(new View.OnClickListener() {
            //Ines
            @Override
            public void onClick(View view) {
                mentor_datos = 0;
                lista_actividades = Getlista(MainActivity.sharedPreferences.getInt("estres", 0),0);
                String listaJson = gson.toJson(lista_actividades);

                // Guardar la cadena de texto JSON en SharedPreferences

                activity_datos.editor.putString("lista", listaJson);
                activity_datos.editor.apply();

                startActivity(intent);
                startActivity(intent1);
            }
        });


        boton2.setOnClickListener(new View.OnClickListener() {
            //Paula
            @Override
            public void onClick(View view) {
                mentor_datos = 1;
                lista_actividades = Getlista(MainActivity.sharedPreferences.getInt("estres", 0),1);
                String listaJson = gson.toJson(lista_actividades);

                // Guardar la cadena de texto JSON en SharedPreferences

                activity_datos.editor.putString("lista", listaJson);
                activity_datos.editor.apply();

                startActivity(intent);
                startActivity(intent1);

            }
        });

        boton3.setOnClickListener(new View.OnClickListener() {
            //Jimena
            @Override
            public void onClick(View view) {
                mentor_datos = 2;
                lista_actividades = Getlista(MainActivity.sharedPreferences.getInt("estres", 0),2);
                String listaJson = gson.toJson(lista_actividades);

                // Guardar la cadena de texto JSON en SharedPreferences

                activity_datos.editor.putString("lista", listaJson);
                activity_datos.editor.apply();

                startActivity(intent);
                startActivity(intent1);

            }
        });









    }
    private List<Actividades> Getlista(int nivel, int mentor) {
        List<Actividades> lista = new ArrayList<>();
        lista = db.ActividadesDAO().selectactividad(nivel,mentor);

        return lista;
    }


}