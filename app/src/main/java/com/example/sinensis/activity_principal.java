package com.example.sinensis;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;

import java.util.List;

public class activity_principal extends AppCompatActivity {

    private AppDatabase db; // una variable vacia donde podremos meter las actividades

    private ListView listActividades; //lista de actividades

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_principal);

        db = AppDatabase.getInstance(getApplicationContext()); // le damos valos de inicio a db

        listActividades = findViewById(R.id.list_view);
        listActividades.setOnItemClickListener((adapter, view, pos, id) -> {
            Actividades actividades = (Actividades) adapter.getAdapter().getItem(pos);
        });

        displayList();



    }

    private void displayList() {
        List<Actividades> notesList = db.ActividadesDAO().selectAll();
        ArrayAdapter<Actividades> listAdapter = new ArrayAdapter<>(listActividades.getContext(),
                android.R.layout.simple_list_item_1, notesList);
        listActividades.setAdapter(listAdapter);
    }



}
