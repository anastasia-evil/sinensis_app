package com.example.sinensis;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.List;

public class activity_principal extends AppCompatActivity {

    private AppDatabase db; // una variable vacia donde podremos meter las actividades

    private ListView listView; //lista de actividades

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_principal);
        //mostramos el nombre que hemos introducido
        Intent intent = getIntent();
        String message = intent.getStringExtra("nombre");
        TextView textView = findViewById(R.id.textonombre);
        textView.setText(message);


        Log.d("MyApp", "Creando instancia de la base de datos");
        db = AppDatabase.getInstance(getApplicationContext()); // le damos valos de inicio a db




        listView = findViewById(R.id.list_view);


        int count = db.ActividadesDAO().count();




        Log.d("MyApp", "Buscando todas las actividades...");
        List<Actividades> actividadesList = db.ActividadesDAO().selectAll();
        if (count > 0) {
            Log.d("MyApp", "hay datos");
        } else {
            Log.d("MyApp", "No hay datos");
        }
        Log.d("MyApp", "actividadesList size: " + actividadesList.size());
        ArrayAdapter<Actividades> listAdapter = new ArrayAdapter<>(listView.getContext(),
                android.R.layout.simple_list_item_1, actividadesList);
        listView.setAdapter(listAdapter);


    }

}
