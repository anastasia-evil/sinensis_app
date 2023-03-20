package com.example.sinensis;

import android.os.Bundle;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.PopupWindow;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;

public class activity_ajustes extends AppCompatActivity {
    private ListView listview_ajustes;
    private ArrayList<String> ajustes;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ajustes);

        listview_ajustes = (ListView) findViewById(R.id.listview_ajustes);
        ajustes = new ArrayList<String>();
        ajustes.add("Sobre la app");
        ajustes.add("Creadoras");
        ajustes.add("ODS");
        ajustes.add("Mis datos");
        ajustes.add("Idioma");
        ajustes.add("Notificaciones");

        ArrayAdapter<String> adapter_ajustes = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, ajustes);

        listview_ajustes.setAdapter(adapter_ajustes);
        listview_ajustes.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long id) {

                String texto = "";

                switch(position){
                    case 0:
                        //Sobre la app
                        texto = "Sinensis es una app enfocada en el alivio del estrés a través de la creatividad. Dispone de un amplio catálogo de actividades que te ayudarán a conectar tranquilamente con el momento presente.";
                        break;
                    case 1:
                        //Creadoras
                        texto = "Esta app fue desarrollada por tres estudiantes de Ingeniería de Sonido e Imagen de la Universidad Carlos III de Madrid: Inés Acebes, Paula Gallejones y Jimena Díaz.";
                        break;
                    case 2:
                        //ODS
                        texto = "La ODS (Objetivos y Metas de Desarrollo Sostenible) a la que está enfocada esta app es a Salud y Bienestar.";
                        break;
                    case 3:
                        //Mis datos
                        String mi_nombre = activity_datos.nombre_datos;
                        String mi_edad = String.valueOf(activity_datos.edad_datos);
                        String mi_grado_estres = String.valueOf(activity_datos.grado_datos);
                        texto = "Tu nombre: " + mi_nombre + "\nTu edad: " + mi_edad + "\nTu último grado de estrés: " + mi_grado_estres;
                        break;
                    case 4:
                        //Idioma
                        texto = "Aquí podrás escoger el idioma.";
                        break;
                    case 5:
                        //Notificaciones
                        texto = "Aquí podrás activar o desactivar las notificaciones.";
                        break;
                    default:
                        break;
                }

                showPopUp(view, texto);
            }
        });
    }

    public void showPopUp(View view, String texto) {

        LayoutInflater inflater = (LayoutInflater) getSystemService(LAYOUT_INFLATER_SERVICE);
        View popupView = inflater.inflate(R.layout.popup_ajustes, null);

        final PopupWindow popupWindow = new PopupWindow(popupView, LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT, true );

        popupWindow.showAtLocation(view, Gravity.CENTER, 0, 0);

        TextView contenido = (TextView) popupView.findViewById(R.id.contenido);
        contenido.setText(texto);
        Button cerrar = (Button) popupView.findViewById(R.id.cerrar);

        popupView.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                popupWindow.dismiss();
                return true;
            }
        });

        cerrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                popupWindow.dismiss();
            }
        });
    }

}
