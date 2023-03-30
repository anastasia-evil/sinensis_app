package com.example.sinensis;

import android.os.Bundle;
import android.widget.ListView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.List;

public class activity_actividades extends AppCompatActivity {

    private ListView listView;

    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_actividades);

        listView = findViewById(R.id.list_view_actividades);

        List<Actividades> lista = activity_principal.db.ActividadesDAO().selectAll();
        Adaptadores adaptador = new Adaptadores(this, lista, null);
        listView.setAdapter(adaptador);
    }

}
