package com.example.sinensis;

import android.os.Bundle;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.widget.AdapterView;
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

        //Creamos la lista de visualización
        listview_ajustes = (ListView) findViewById(R.id.listview_ajustes);
        //Rellenamos el array con las opciones
        ajustes = new ArrayList<String>();
        ajustes.add("Sobre la app");
        ajustes.add("Creadoras");
        ajustes.add("ODS");
        ajustes.add("Mis datos");
        ajustes.add("Notificaciones");

        Adaptadores adapter_ajustes = new Adaptadores(this, null, ajustes);

        listview_ajustes.setAdapter(adapter_ajustes);
        listview_ajustes.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long id) {

                String texto = "";

                switch(position){
                    case 0:
                        //Sobre la app
                        texto = getString(R.string.sobre_la_app);
                        break;
                    case 1:
                        //Creadoras
                        texto = getString(R.string.creadoras);
                        break;
                    case 2:
                        //ODS
                        texto = getString(R.string.ods);
                        break;
                    case 3:
                        //Mis datos
                        String mi_nombre = activity_datos.nombre_datos;
                        String mi_edad = String.valueOf(activity_datos.edad_datos);
                        String mi_grado_estres = String.valueOf(activity_datos.grado_datos);
                        texto = "Tu nombre: " + mi_nombre + "\nTu edad: " + mi_edad + "\nTu último grado de estrés: " + mi_grado_estres;
                        break;
                    case 4:
                        //Notificaciones
                        texto = getString(R.string.notificaciones);
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
