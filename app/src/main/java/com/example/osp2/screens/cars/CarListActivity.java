package com.example.osp2.screens.cars;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.Menu;
import android.view.View;
import android.view.WindowManager;
import android.widget.Adapter;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.osp2.R;
import com.example.osp2.adapter.CarsAdapter;
import com.example.osp2.data.CarsDatabase;
import com.example.osp2.pojo.Car;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.List;
import java.util.SplittableRandom;

public class CarListActivity extends AppCompatActivity {

    private RecyclerView recyclerViewCars;
    private ImageView imageViewButton;
    public ArrayList<Car> cars = new ArrayList<>();
    private CarsAdapter adapter;
    private CarViewModel viewModel;
    private CheckBox checkBox1;
    private CheckBox checkBox2;
    private CheckBox checkBox3;
    private CheckBox checkBox4;
    private static final String CHECK_BOX_1 = "ckeckBox1";
    private static final String CHECK_BOX_2 = "ckeckBox2";
    private static final String CHECK_BOX_3 = "ckeckBox3";
    private static final String CHECK_BOX_4 = "ckeckBox4";
    //имя файла настроек
    private static final String NAME = "preferenceName";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_main);
        checkBox1 = findViewById(R.id.checkbox1);
        checkBox2 = findViewById(R.id.checkbox2);
        checkBox3 = findViewById(R.id.checkbox3);
        checkBox4 = findViewById(R.id.checkbox4);
        imageViewButton = findViewById(R.id.imageViewButton);
        recyclerViewCars = findViewById(R.id.recyclerViewCars);
        viewModel = ViewModelProviders.of(this).get(CarViewModel.class);
        getData();
        adapter = new CarsAdapter(cars);
        recyclerViewCars.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));
        recyclerViewCars.setAdapter(adapter);

        adapter.setOnCarClickListener(new CarsAdapter.OnCarClickListener() {
            @Override
            public void onCarClick(int position) {
            }
        });


        imageViewButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), AddCarActivity.class);
                startActivity(intent);
            }
        });

        ItemTouchHelper itemTouchHelper = new ItemTouchHelper(new ItemTouchHelper.SimpleCallback(0, ItemTouchHelper.LEFT | ItemTouchHelper.RIGHT) {
            @Override
            public boolean onMove(@NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder, @NonNull RecyclerView.ViewHolder target) {
                return false;
            }

            @Override
            public void onSwiped(@NonNull RecyclerView.ViewHolder viewHolder, int direction) {
                remove(viewHolder.getAdapterPosition());
            }

        });
        itemTouchHelper.attachToRecyclerView(recyclerViewCars);

    }

    public void getData() {
        LiveData<List<Car>> carFromDB = viewModel.getCars();
        carFromDB.observe(this, new Observer<List<Car>>() {
            @Override
            public void onChanged(List<Car> carsFromLiveData) {
                adapter.setCars(carsFromLiveData);
            }
        });

    }

    public void remove(int position) {
        Car car = adapter.getCars().get(position);
        viewModel.deleteCar(car);
    }

}
