package com.example.sinensis;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class activity_datos extends AppCompatActivity {

    EditText nombre, edad;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_datos);
        nombre = (EditText) findViewById(R.id.nombre);
        edad = (EditText) findViewById(R.id.edad);
    }



    public void validar(View v){
        if(nombre.getText().toString().isEmpty() || edad.getText().toString().isEmpty()){
            Toast toast = Toast.makeText(this, "Rellena todos los campos", Toast.LENGTH_SHORT);
            toast.show();
        }

    }



}