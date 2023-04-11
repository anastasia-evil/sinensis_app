package com.example.sinensis;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class Adaptadores extends BaseAdapter {
    private List<Actividades> lista_act = new ArrayList<>();

    private Context context;
    public static int id;


    public Adaptadores(Context context, List<Actividades> lista_act) {
        this.context= context;
        this.lista_act = lista_act;

    }
    public int getCount(){

            return lista_act.size();

    }
    public Actividades getItem(int position){ return lista_act.get(position); }



    @Override
    public long getItemId(int i) {
        return 0;
    }

    public void actualizarActividades(List<Actividades> actividades) {
        lista_act = actividades;
        notifyDataSetChanged();
    }

    @Override

    public View getView(int position, View convertView, ViewGroup parent) {


            Actividades a = (Actividades) getItem(position);

            convertView = LayoutInflater.from(context).inflate(R.layout.listas_items, null);

            TextView nombreTextView = (TextView) convertView.findViewById(R.id.tituloActividad);
            nombreTextView.setText(a.getNombre());

            TextView descripcionTextView = (TextView) convertView.findViewById(R.id.descripcionActividad);
            descripcionTextView.setText(a.getDescripcion());

            String ruta = a.getfoto(); // Devuelve el nombre de archivo de la imagen
            String nombreArchivo = ruta.substring(0, ruta.lastIndexOf(".")); // Elimina la extensión ".png" del nombre de archivo
            id = context.getResources().getIdentifier(nombreArchivo, "drawable", context.getPackageName()); // Obtiene el ID de recurso de la imagen sin la extensión
            ImageView imagenDraw = (ImageView) convertView.findViewById(R.id.imageactividad);
            imagenDraw.setImageResource(id);


            return convertView;


    }




}