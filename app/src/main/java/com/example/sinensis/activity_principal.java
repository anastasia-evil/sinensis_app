package com.example.sinensis;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.List;

public class activity_principal extends AppCompatActivity {

    private AppDatabase db; // una variable vacia donde podremos meter las actividades

    private ListView listView; //lista de actividades

    ImageButton btn_calendario,btn_home,btn_ajustes;
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
            }
        });
        btn_ajustes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(intentA);
            }
        });

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
