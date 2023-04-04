package com.example.sinensis;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

@Entity
public class Actividades {
    @PrimaryKey(autoGenerate = true)
    public long id;

    @ColumnInfo(name = "nombre")
    public String nombre;

    @ColumnInfo(name = "nivel")
    public int nivel;

    @ColumnInfo(name = "descripcion")
    public String descripcion;

    @ColumnInfo(name = "edad")
    public int edad;

    @ColumnInfo(name = "mentor")
    public int mentor;

    @ColumnInfo(name = "foto")
    public String  foto;

    @ColumnInfo(name = "descripcion_larga")
    public String  descripcion_larga;

    public Actividades(long id, String nombre, int nivel, String descripcion, int edad, int mentor, String foto, String descripcion_larga) {
        this.id = id;
        this.nombre = nombre;
        this.nivel = nivel;
        this.descripcion = descripcion;
        this.edad = edad;
        this.mentor = mentor;
        this.foto = foto;
        this.descripcion_larga = descripcion_larga;
    }

    @Ignore
    public Actividades(String nombre, int nivel, String descripcion, int edad, int mentor,String foto) {
        this.nombre = nombre;
        this.nivel = nivel;
        this.descripcion = descripcion;
        this.edad = edad;
        this.mentor = mentor;
        this.foto = foto;
    }


    public String getNombre() {
        return nombre;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public String getfoto() {
        return foto;
    }
}
