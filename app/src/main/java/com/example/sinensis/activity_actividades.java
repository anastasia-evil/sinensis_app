package com.example.sinensis;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.gson.Gson;

import java.util.List;

public class activity_actividades extends AppCompatActivity {

    private ListView listView;
    public static AppDatabase db2; //base de datos en java

    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_actividades);

        listView = findViewById(R.id.list_view_actividades);
        db2 = AppDatabase.getInstance(getApplicationContext());

        List<Actividades> lista_completa = db2.ActividadesDAO().selectAll();
        Adaptadores adaptador2 = new Adaptadores(this, lista_completa);
        listView.setAdapter(adaptador2);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {
                Actividades a = (Actividades) adaptador2.getItem(position);

                //parametros, nosotros tenemos titulo, imagen y descripcion(pongo de momento la de siempre)

                //intentLista.putExtra("TIT", a.getNombre());



                activity_principal.lista.add(a);
                SharedPreferences.Editor editor = MainActivity.sharedPreferences.edit();

                Gson gson = new Gson();
                // Convertir la lista en una representación JSON usando Gson
                String listaJson = gson.toJson(activity_principal.lista);

                // Guardar la lista actualizada en SharedPreferences
                editor.putString("lista", listaJson);

                // Aplicar los cambios
                editor.apply();

                activity_principal.adaptador.notifyDataSetChanged();
                Toast toast = Toast.makeText(activity_actividades.this, "Actividad seleccionada", Toast.LENGTH_SHORT);
                toast.show();



            }
        });
    }

    /*public void anadir(List<Actividades> parcial, Actividades a){

        activity_principal.db.ActividadesDAO().insert(a);



    }*/

}
