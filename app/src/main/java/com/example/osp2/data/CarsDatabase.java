package com.example.osp2.data;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Entity;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import com.example.osp2.pojo.Car;

@Database(entities = {Car.class}, version = 1, exportSchema = false)
public abstract class CarsDatabase extends RoomDatabase {
    private static CarsDatabase carsDatabase;
    private static final String DB_NAME = "cars.db";
    //чтобы при обращении к БД из разных потоков не было конфликтов создаем обьект синхронизации
    private static final Object LOCK = new Object();

    public static CarsDatabase getInstance(Context context) {
        synchronized (LOCK) {
            if (carsDatabase == null) {
                carsDatabase = Room.databaseBuilder(context, CarsDatabase.class, DB_NAME)
                        .build();
            }
        }
        return carsDatabase;
    }

    public abstract CarsDao carsDao();
}
