package com.example.sinensis;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.List;

public class Adaptadores extends ArrayAdapter<Actividades> {
    public Adaptadores(Context context, int resource) {
        super(context, resource);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.listas_items, parent, false);
        }



        TextView nombreTextView = convertView.findViewById(R.id.tituloActividad);
        nombreTextView.setText(activity_principal.listanombres.get(position));

        TextView descripcionTextView = convertView.findViewById(R.id.descripcionActividad);
        descripcionTextView.setText(activity_principal.listadescripcion.get(position));

        return convertView;
    }
}
