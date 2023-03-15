package com.example.sinensis;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class activity_datos extends AppCompatActivity {

    static EditText nombre;
    EditText edad;
    SeekBar seekbar;
    int grado;
    public static int grado_datos;
    public static int edad_datos;
    TextView n;
    Button btn;

    private AppDatabase db;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_datos);
        nombre = (EditText) findViewById(R.id.nombre);
        edad = (EditText) findViewById(R.id.edad);
        seekbar = (SeekBar) findViewById(R.id.seekbar);
        n = (TextView) findViewById(R.id.textito);
        btn = (Button) findViewById(R.id.button);


        seekbar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener(){
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser){
                grado = seekbar.getProgress();
                n.setText("Nivel: " + progress);
                grado_datos = progress;
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }


        });

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(nombre.getText().toString().isEmpty() || edad.getText().toString().isEmpty()){
                    Toast toast = Toast.makeText(activity_datos.this, "Rellena los datos", Toast.LENGTH_SHORT);
                    toast.show();
                }
                else{
                    String edad_conversor = edad.getText().toString();
                    edad_datos = Integer.parseInt(edad_conversor);
                    Intent intent = new Intent(activity_datos.this, activity_mentores.class);
                    startActivity(intent);
                }

            }

        });


    }










}