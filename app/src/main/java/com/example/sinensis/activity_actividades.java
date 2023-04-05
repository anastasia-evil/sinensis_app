package com.example.sinensis;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import androidx.appcompat.app.AppCompatActivity;

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
        Adaptadores adaptador2 = new Adaptadores(this, lista_completa, null);
        listView.setAdapter(adaptador2);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {
                Actividades a = (Actividades) adaptador2.getItem(position);

                //parametros, nosotros tenemos titulo, imagen y descripcion(pongo de momento la de siempre)

                //intentLista.putExtra("TIT", a.getNombre());


                activity_principal.lista_actividades.add(a);

                activity_principal.adaptador.notifyDataSetChanged();



            }
        });
    }

    /*public void anadir(List<Actividades> parcial, Actividades a){

        activity_principal.db.ActividadesDAO().insert(a);



    }*/

}
