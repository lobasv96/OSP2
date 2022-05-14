package com.example.osp2.domain.pojo;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

@Entity (tableName = "cars")
public class Car {
    @PrimaryKey(autoGenerate = true)
    private int id;

    @ColumnInfo(name = "car_model")
    private String model;

    @ColumnInfo(name = "car_vin")
    private String vin;

    @ColumnInfo(name = "car_color")
    private String color;

    @Ignore
    public Car(String model, String vin, String color) {
        this.model = model;
        this.vin = vin;
        this.color = color;
    }

    public Car(int id, String model, String vin, String color) {
        this.id = id;
        this.model = model;
        this.vin = vin;
        this.color = color;
    }


    public String getModel() {
        return model;
    }

    public String getVin() {
        return vin;
    }

    public String getColor() {
        return color;
    }


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public void setVin(String vin) {
        this.vin = vin;
    }

    public void setColor(String color) {
        this.color = color;
    }
}
