package com.example.sinensis;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
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
    private List<String> lista_ajus = new ArrayList<>();
    private Context context;


    public Adaptadores(Context context, List<Actividades> lista_act, List<String> lista_ajus) {
        this.context= context;
        this.lista_act = lista_act;
        this.lista_ajus = lista_ajus;
    }
    public int getCount(){
        if (lista_ajus == null){
            return lista_act.size();
        }else{
            return lista_ajus.size();
        }
    }
    public Actividades getItem(int position){ return lista_act.get(position); }
    public String getItem_ajus(int position){ return lista_ajus.get(position); }


    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override

    public View getView(int position, View convertView, ViewGroup parent) {

        if (lista_ajus == null){
            Actividades a = (Actividades) getItem(position);

            convertView = LayoutInflater.from(context).inflate(R.layout.listas_items, null);

            TextView nombreTextView = (TextView) convertView.findViewById(R.id.tituloActividad);
            nombreTextView.setText(a.getNombre());

            TextView descripcionTextView = (TextView) convertView.findViewById(R.id.descripcionActividad);
            descripcionTextView.setText(a.getDescripcion());

            String ruta = a.getfoto(); // Devuelve el nombre de archivo de la imagen
            String nombreArchivo = ruta.substring(0, ruta.lastIndexOf(".")); // Elimina la extensión ".png" del nombre de archivo
            int id = context.getResources().getIdentifier(nombreArchivo, "drawable", context.getPackageName()); // Obtiene el ID de recurso de la imagen sin la extensión
            ImageView imagenDraw = (ImageView) convertView.findViewById(R.id.imageactividad);
            imagenDraw.setImageResource(id);


            return convertView;

        }else{
//h
            String a = (String) getItem_ajus(position);

            convertView = LayoutInflater.from(context).inflate(R.layout.listas_items_ajus, null);

            TextView nombreTextView = (TextView) convertView.findViewById(R.id.tituloActividad);
            nombreTextView.setText(a);

            TextView descripcionTextView = (TextView) convertView.findViewById(R.id.descripcionActividad);
            descripcionTextView.setText("");

            return convertView;

        }
    }




}