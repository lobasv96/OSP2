package com.example.osp2.presentation.cars.screens;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.CheckBox;
import android.widget.ImageView;
import com.example.osp2.R;
import com.example.osp2.presentation.cars.adapter.CarsAdapter;
import com.example.osp2.domain.pojo.Car;
import com.example.osp2.presentation.cars.CarViewModel;

import java.util.ArrayList;
import java.util.List;


public class CarListActivity extends AppCompatActivity {

    private RecyclerView recyclerViewCars;
    private ImageView imageViewButton;
    public ArrayList<Car> cars = new ArrayList<>();
    private CarsAdapter adapter;
    private CarViewModel viewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_main);
        imageViewButton = findViewById(R.id.imageViewButton);
        recyclerViewCars = findViewById(R.id.recyclerViewCars);
        viewModel = ViewModelProviders.of(this).get(CarViewModel.class);
        getData();
        adapter = new CarsAdapter(cars);
        recyclerViewCars.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));
        recyclerViewCars.setAdapter(adapter);

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
