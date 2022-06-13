package com.example.osp2.adapter;

import android.content.SharedPreferences;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.Spinner;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.osp2.R;
import com.example.osp2.pojo.Car;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.List;

import static android.content.Context.MODE_PRIVATE;

public class CarsAdapter extends RecyclerView.Adapter<CarsAdapter.CarsViewHolder> {

    private List<Car> cars;

    public CarsAdapter(ArrayList<Car> cars) {
        this.cars = cars;
    }
    private OnCarClickListener onCarClickListener;

    public void setOnCarClickListener(OnCarClickListener onCarClickListener) {
        this.onCarClickListener = onCarClickListener;
    }

    public interface OnCarClickListener {
        void onCarClick(int position);
    }


    @NonNull
    @Override
    public CarsViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.car_item, parent, false);
        return new CarsViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CarsViewHolder holder, int position) {
        Car car = cars.get(position);
        holder.textViewCarName.setText(car.getModel());
        holder.textViewVIN.setText(car.getVin());
        holder.textViewColor.setText(car.getColor());
        holder.checkBox1.setChecked(false);
        holder.checkBox2.setChecked(false);
        holder.checkBox3.setChecked(false);
        holder.checkBox4.setChecked(false);

    }


    @Override
    public int getItemCount() {
        return cars.size();
    }


    class CarsViewHolder extends RecyclerView.ViewHolder {

        private TextView textViewCarName;
        private TextView textViewVIN;
        private TextView textViewColor;
        private CheckBox checkBox1;
        private CheckBox checkBox2;
        private CheckBox checkBox3;
        private CheckBox checkBox4;
        private ImageView imageViewLogo;

        public CarsViewHolder(@NonNull View itemView) {
            super(itemView);
            textViewCarName = itemView.findViewById(R.id.textViewCarName);
            textViewVIN = itemView.findViewById(R.id.textViewVIN);
            textViewColor = itemView.findViewById(R.id.textViewColor);
            checkBox1 = itemView.findViewById(R.id.checkbox1);
            checkBox2 = itemView.findViewById(R.id.checkbox2);
            checkBox3 = itemView.findViewById(R.id.checkbox3);
            checkBox4 = itemView.findViewById(R.id.checkbox4);
            imageViewLogo = itemView.findViewById(R.id.imageViewLogo);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if (onCarClickListener != null) {
                        onCarClickListener.onCarClick(getAdapterPosition());
                    }
                }
            });
        }
    }

    public void setCars(List<Car> cars) {
        this.cars = cars;
        notifyDataSetChanged();
    }

    public List<Car> getCars() {
        return cars;
    }
}
