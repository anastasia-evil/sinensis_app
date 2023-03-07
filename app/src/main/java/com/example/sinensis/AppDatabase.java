package com.example.sinensis;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

@Database(entities = { Actividades.class }, version = 1)
public abstract class AppDatabase extends RoomDatabase{

        private static final String DB_NAME = "actividades.sqbpro";
        private static volatile AppDatabase instance;

        public abstract ActividadesDAO ActividadesDAO();

        public static synchronized AppDatabase getInstance(Context context) {
            if (instance == null) {
                instance = create(context);
            }
            return instance;
        }

        private static AppDatabase create(Context context) {
            return Room.databaseBuilder(context, AppDatabase.class, DB_NAME)
                    .allowMainThreadQueries()
                    .build();
        }


}
