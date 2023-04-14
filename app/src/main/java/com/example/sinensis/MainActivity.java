package com.example.sinensis;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;

import androidx.appcompat.app.AppCompatActivity;


import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    public static SharedPreferences sharedPreferences;
    public static int p;

    public static List<Actividades> listaRecuperada;

    @Override
    public void onCreate(Bundle savedInstanceState) {



        sharedPreferences = getSharedPreferences("datos33", Context.MODE_PRIVATE);

        int i = carga(sharedPreferences);

        //Implementación Splash Screen
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Handler handler = new Handler();

        handler.postDelayed(new Runnable() {
            @Override
            public void run() {

                if(i==1) {
                    Intent intent2 = new Intent(MainActivity.this, activity_principal.class);
                    startActivity(intent2);
                    finish();

                }else{
                    Intent intent = new Intent(MainActivity.this, activity_datos.class);
                    startActivity(intent);
                    finish();

                }


            }
        },2000);




    }

    public int carga(SharedPreferences sharedPreferences){
        // Recupera los datos guardados en SharedPreferences
        String nombre = sharedPreferences.getString("nombre", "");
        int edad = sharedPreferences.getInt("edad", 0);
        int gradoEstres = sharedPreferences.getInt("estres", 0);
        //int mentor = sharedPreferences.getInt("mentor",0);
        String listaJson = sharedPreferences.getString("lista", null);
        activity_ajustes.m = Integer.toString(sharedPreferences.getInt("hojas",0));
        Gson gson = new Gson();
        if (listaJson != null) {
            // Convertir la cadena de texto JSON a una lista de objetos "Item"
            Type tipoLista = new TypeToken<List<Actividades>>(){}.getType();
            List<Actividades> listaRecuperada = gson.fromJson(listaJson, tipoLista);
            activity_principal.lista = listaRecuperada;

            // Ahora puedes acceder a la lista de objetos "Item" recuperada
            // y utilizarla en tu aplicación
        }

        if(sharedPreferences.contains("nombre") && sharedPreferences.contains("edad") && sharedPreferences.contains("estres")) {

            return 1;

        }else{
            p = 1;
            return 0;
        }

    }









}