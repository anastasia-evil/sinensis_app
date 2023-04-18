package com.example.sinensis;

import android.content.Context;
import android.content.DialogInterface;
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

import androidx.appcompat.app.AlertDialog;
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


        txt = getString(R.string.nivel_medio);
        n.setText(getString(R.string.nivel) + txt);


        seekbar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener(){
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser){
                grado = seekbar.getProgress();

                if(progress == 0){
                    txt = getString(R.string.nivel_bajo);

                }else if(progress == 1){
                    txt = getString(R.string.nivel_mediobajo);

                }else if(progress == 2){
                    txt = getString(R.string.nivel_medio);

                }else if(progress == 3 ){
                    txt = getString(R.string.nivel_medioalto);

                }else if(progress == 4){
                    txt = getString(R.string.nivel_alto);
                }
                n.setText(getString(R.string.nivel) + txt);
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
                    AlertDialog.Builder builder = new AlertDialog.Builder(activity_datos.this);
                    builder.setMessage(getString(R.string.rellenar_datos))
                            .setPositiveButton(getString(R.string.aceptar), new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialogInterface, int i) {
                                    dialogInterface.dismiss();
                                }
                            });
                    AlertDialog alertDialog = builder.create();
                    alertDialog.show();
                }
                else{
                    String edad_conversor = edad.getText().toString();
                    edad_datos = Integer.parseInt(edad_conversor);
                    nombre_datos = nombre.getText().toString();
                    // Obtén una instancia de SharedPreferences
                    // Obtiene un editor de SharedPreferences para realizar modificaciones
                    sharedPreferences = getSharedPreferences("datos12", Context.MODE_PRIVATE);
                    editor = sharedPreferences.edit();

                    // Guarda los datos ingresados por el usuario en SharedPreferences
                    editor.putString("nombre", activity_datos.nombre_datos);
                    editor.putInt("edad", activity_datos.edad_datos);
                    editor.putInt("estres",level());


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