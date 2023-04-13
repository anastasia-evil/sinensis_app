package com.example.sinensis;

import android.content.Context;
import android.content.Intent;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
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
    int grado = 2;
    public static int grado_datos = 2;
    public static int edad_datos;
    public static String nombre_datos;
    TextView n;
    Button btn;
    String txt;

    public static SharedPreferences.Editor editor;
    public static SharedPreferences sharedPreferences;





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


        txt = "Medio";
        n.setText("Nivel: " + txt);


        seekbar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener(){
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser){
                grado = seekbar.getProgress();

                if(progress == 0){
                    txt = "Bajo";

                }else if(progress == 1){
                    txt = "Medio bajo";

                }else if(progress == 2){
                    txt = "Medio";

                }else if(progress == 3 ){
                    txt = "Medio alto";

                }else if(progress == 4){
                    txt = "Alto";
                }
                n.setText("Nivel: " + txt);
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
                    Toast toast = Toast.makeText(activity_datos.this, "Rellena todos los datos", Toast.LENGTH_SHORT);
                    toast.show();
                }
                else{
                    String edad_conversor = edad.getText().toString();
                    edad_datos = Integer.parseInt(edad_conversor);
                    nombre_datos = nombre.getText().toString();
                    // Obtén una instancia de SharedPreferences
                    // Obtiene un editor de SharedPreferences para realizar modificaciones
                    sharedPreferences = getSharedPreferences("datos6", Context.MODE_PRIVATE);
                    editor = sharedPreferences.edit();

                    // Guarda los datos ingresados por el usuario en SharedPreferences
                    editor.putString("nombre", activity_datos.nombre_datos);
                    editor.putInt("edad", activity_datos.edad_datos);
                    editor.putInt("estres", level());


                    // Aplica los cambios
                    editor.apply();
                    Intent intent = new Intent(activity_datos.this, activity_mentores.class);
                    startActivity(intent);
                }

            }

        });












    }

    public int level(){
        if(grado_datos == 0 || grado_datos  == 1){
            return 0;
        }else if(grado_datos  == 2 || grado_datos  == 3){
            return 1;
        }else{
            return 2;
        }
    }

}