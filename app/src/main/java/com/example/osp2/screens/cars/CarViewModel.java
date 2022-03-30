package com.example.osp2.screens.cars;

import android.app.Application;
import android.os.AsyncTask;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.example.osp2.data.CarsDatabase;
import com.example.osp2.pojo.Car;

import java.util.List;

public class CarViewModel extends AndroidViewModel {

    private static CarsDatabase carsDatabase; // создаем БД
    private LiveData<List<Car>> cars; // создаем обьект LiveData (хранит лист заметок)

    public CarViewModel(@NonNull Application application) {
        super(application);
        // инициализируем в конструкторе
        carsDatabase = CarsDatabase.getInstance(getApplication());
        cars = carsDatabase.carsDao().getAllCars();
    }

    // создали геттер на заметки
    public LiveData<List<Car>> getCars() {
        return cars;
    }



    // создаем методы для вставки и удаления элементов
    public void deleteCar(Car car) {
        new DeleteTask().execute(car);
    }
    private static class DeleteTask extends AsyncTask<Car, Void, Void> {
        @Override
        protected Void doInBackground(Car... cars) {
            if (cars != null && cars.length > 0) {
                carsDatabase.carsDao().deleteCar(cars[0]);
            }
            return null;
        }
    }

    public void addCar(Car car) {
        new InsertTask().execute(car);
    }
    private static  class InsertTask extends AsyncTask<Car, Void, Void> {
        @Override
        protected Void doInBackground(Car... cars) {
            if (cars != null && cars.length > 0) {
                carsDatabase.carsDao().addCar(cars[0]);
            }
            return null;
        }
    }

    public void deleteAllCar() {
        new DeleteAllTask().execute();
    }
    private static class  DeleteAllTask extends AsyncTask<Void, Void, Void> {
        @Override
        protected Void doInBackground(Void... cars) {
            carsDatabase.carsDao().deleteAllCars();
            return null;
        }
    }



}
