package com.example.sinensis;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class Adaptadores extends BaseAdapter {
    private List<Actividades> lista = new ArrayList<>();
    private Context context;
    public Adaptadores(Context context, List<Actividades> lista) {
        this.context= context;
        this.lista = lista;
    }

    public int getCount(){
        return lista.size();
}
    public Actividades getItem(int position){
        return lista.get(position);
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override

    public View getView(int position, View convertView, ViewGroup parent) {

        Actividades a = (Actividades) getItem(position);

        convertView = LayoutInflater.from(context).inflate(R.layout.listas_items, null);

        TextView nombreTextView = (TextView) convertView.findViewById(R.id.tituloActividad);
        nombreTextView.setText(a.getNombre());

        TextView descripcionTextView = (TextView) convertView.findViewById(R.id.descripcionActividad);
        descripcionTextView.setText(a.getDescripcion());

        return convertView;
    }
}
