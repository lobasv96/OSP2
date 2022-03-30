package com.example.osp2.data;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.example.osp2.pojo.Car;

import java.util.List;

@Dao
public interface CarsDao {

    @Insert
     void addCar(Car car);

    @Delete
     void deleteCar(Car car);

    @Query("SELECT * FROM cars")
     LiveData<List<Car>> getAllCars();

    @Query("DELETE FROM cars")
     void deleteAllCars();

}
