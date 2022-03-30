package com.example.osp2.screens.cars;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProviders;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.osp2.R;
import com.example.osp2.data.CarsDatabase;
import com.example.osp2.pojo.Car;
import com.example.osp2.screens.cars.CarListActivity;

public class AddCarActivity extends AppCompatActivity {

    private EditText editTextModel;
    private EditText editTextVIN;
    private Spinner spinnerColors;
    private Button buttonSave;
    private Button buttonReturn;
    private CarViewModel viewModel;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);
        editTextModel = findViewById(R.id.editTextModel);
        editTextVIN = findViewById(R.id.editTextVIN);
        spinnerColors = findViewById(R.id.spinnerColors);
        buttonSave = findViewById(R.id.buttonSave);
        buttonReturn = findViewById(R.id.buttonReturn);
        viewModel = ViewModelProviders.of(this).get(CarViewModel.class);

        buttonSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String model = editTextModel.getText().toString().trim();
                String vin = editTextVIN.getText().toString().trim();
                String colors = spinnerColors.getSelectedItem().toString();
                if (!model.isEmpty() || !vin.isEmpty()) {
                    Car car = new Car(model, vin, colors);
                    viewModel.addCar(car);
                    Intent intentSave = new Intent(getApplicationContext(), CarListActivity.class);
                    startActivity(intentSave);
                } else {
                    Toast.makeText(getApplicationContext(),R.string.error_write_all_fields, Toast.LENGTH_LONG).show();
                }
            }
        });

        buttonReturn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), CarListActivity.class);
                startActivity(intent);
            }
        });
    }

}