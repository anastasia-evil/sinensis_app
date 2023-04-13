package com.example.sinensis;

import android.os.Bundle;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.PopupWindow;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;

public class activity_ajustes extends AppCompatActivity {

    public Button nosotras, app, ods, datos, progreso;

    public String texto,texto2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ajustes);


        //Creamos la lista de visualización

        app = (Button) findViewById(R.id.sobre_app);
        nosotras = (Button) findViewById(R.id.sobre_nosotras);
        ods = (Button) findViewById(R.id.ods);
        datos = (Button) findViewById(R.id.misdatos);
        progreso = (Button) findViewById(R.id.progreso);


        app.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                texto = getString(R.string.sobre_la_app);
                showPopUp(view, texto, null,0, 0);
            }

        });
        nosotras.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                texto = getString(R.string.creadoras);
                showPopUp(view, texto, null,0, 0);
            }

        });
        ods.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                texto = getString(R.string.ods);
                showPopUp(view, texto, null,1, 0);
            }

        });
        datos.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String mi_nombre = MainActivity.sharedPreferences.getString("nombre", "");;
                String mi_edad = String.valueOf(activity_datos.edad_datos);
                String mi_grado_estres = String.valueOf(activity_datos.grado_datos);
                texto = getString(R.string.datos,mi_nombre,mi_edad,mi_grado_estres);
                showPopUp(view, texto, null,0, 0);
            }

        });
        progreso.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                texto = getString(R.string.progreso);
                texto2= getString(R.string.progreso2);
                showPopUp(view, null, texto2,0, 1);
            }

        });


    }

    public void showPopUp(View view, String texto, String texto2, int img_ods, int progreso) {

        LayoutInflater inflater = (LayoutInflater) getSystemService(LAYOUT_INFLATER_SERVICE);
        View popupView = inflater.inflate(R.layout.popup_ajustes, null);

        final PopupWindow popupWindow = new PopupWindow(popupView, LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT, true );

        popupWindow.showAtLocation(view, Gravity.CENTER, 0, 0);

        TextView contenido = (TextView) popupView.findViewById(R.id.contenido);
        contenido.setText(texto);
        TextView contenido2 = (TextView)popupView.findViewById(R.id.contenido2);
        contenido2.setText(texto2);
        Button cerrar = (Button) popupView.findViewById(R.id.cerrar);

        if (img_ods != 1){
            ImageView ods = (ImageView) popupView.findViewById(R.id.imagen_ods);
            ods.setVisibility( View.GONE );
        }

        if(progreso !=1){
            LinearLayout check = (LinearLayout) popupView.findViewById(R.id.token);
            LinearLayout check2 = (LinearLayout) popupView.findViewById(R.id.moneda);
            check2.setVisibility(View.GONE);
            check.setVisibility(View.GONE);
        }
        if(progreso==1){
            TextView text = (TextView) popupView.findViewById(R.id.progreso);
            text.setVisibility(View.VISIBLE);
        }

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