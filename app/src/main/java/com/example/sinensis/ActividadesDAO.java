package com.example.sinensis;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

@Dao
public interface ActividadesDAO {

    @Query("SELECT * FROM Actividades")
    List<Actividades> selectAll();

    @Query("SELECT * FROM Actividades WHERE id=:id")
    Actividades selectById(long id);

    @Query("SELECT * FROM Actividades WHERE nombre LIKE :nombre LIMIT 1")
    Actividades selectByNombre(String nombre);

    @Query("SELECT * FROM Actividades WHERE nivel=:nivel")
    Actividades selectByNivel(int nivel);

    @Query("SELECT * FROM Actividades WHERE descripcion LIKE :descripcion LIMIT 1")
    Actividades selectByDescripcion(String descripcion);

    @Query("SELECT * FROM Actividades WHERE edad=:edad")
    Actividades selectByEdad(int edad);

    @Query("SELECT * FROM Actividades WHERE mentorl=:mentor")
    Actividades selectByMentor(int mentor);

    @Insert
    long insert(Actividades actividades);

    @Update
    int update(Actividades actividades);

    @Delete
    int delete(Actividades actividades);
}
