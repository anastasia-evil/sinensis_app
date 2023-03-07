package com.example.sinensis;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

public class activity_datos extends AppCompatActivity {

    EditText nombre, edad;
    SeekBar seekbar;
    int grado;
    TextView n;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_datos);
        nombre = (EditText) findViewById(R.id.nombre);
        edad = (EditText) findViewById(R.id.edad);
        seekbar = (SeekBar) findViewById(R.id.seekbar);
        n = (TextView) findViewById(R.id.textito);



        seekbar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener(){
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser){
                grado = seekbar.getProgress();
                n.setText("Nivel: " + progress);
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }


        });


    }


    public void validar(View v){
        if(nombre.getText().toString().isEmpty() || edad.getText().toString().isEmpty()){
            Toast toast = Toast.makeText(this, "Rellena todos los campos" + grado, Toast.LENGTH_SHORT);
            toast.show();
        }


    }








}