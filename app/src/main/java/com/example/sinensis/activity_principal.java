package com.example.sinensis;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.List;

public class activity_principal extends AppCompatActivity {

    private AppDatabase db; // una variable vacia donde podremos meter las actividades

    private ListView listView; //lista de actividades

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_principal);

        db = AppDatabase.getInstance(getApplicationContext()); // le damos valos de inicio a db

        listView = findViewById(R.id.list_view);


        listView.setOnItemClickListener((adapter, view, pos, id) -> {
            Actividades actividades = (Actividades) adapter.getAdapter().getItem(pos);
        });

        displayList();

    }

    private void displayList() {
        List<Actividades> actividadesList = db.ActividadesDAO().selectAll();
        ArrayAdapter<Actividades> listAdapter = new ArrayAdapter<>(listView.getContext(),
                android.R.layout.simple_list_item_1, actividadesList);
        listView.setAdapter(listAdapter);
    }



}
