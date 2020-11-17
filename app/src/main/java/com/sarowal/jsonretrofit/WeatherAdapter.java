package com.sarowal.jsonretrofit;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.text.DateFormat;
import java.text.DecimalFormat;
import java.util.Date;
import java.util.List;

public class WeatherAdapter extends RecyclerView.Adapter<WeatherAdapter.MyViewHolder> {
    Context context;
    List<DayList> dayLists;
    public WeatherAdapter(Context context, List<DayList> dayLists) {
        this.context = context;
        this.dayLists = dayLists;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(context);
        View view = layoutInflater.inflate(R.layout.weather_single_row,parent,false);
        MyViewHolder myViewHolder = new MyViewHolder(view);
        return myViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        //int to Date formatter
        Date date = new Date((long)dayLists.get(position).getDt()*1000);
        String currentDate = DateFormat.getDateTimeInstance().format(date);
        //Calvin to Celcious formatter
        DecimalFormat decimalFormat = new DecimalFormat("#.#");
        String temp = decimalFormat.format(dayLists.get(position).getTemp().getDay()-273.15);
        String minTemp = decimalFormat.format(dayLists.get(position).getTemp().getMin()-273.15);
        String maxTemp = decimalFormat.format(dayLists.get(position).getTemp().getMax()-273.15);

        holder.tvDate.setText(currentDate);
        holder.tvDescription.setText("Description: "+dayLists.get(position).getWeather().get(0).getDescription());
        holder.tvWind.setText("Wind: "+dayLists.get(position).getDeg());
        holder.tvPressure.setText("Pressure: "+dayLists.get(position).getPressure());
        holder.tvHumidity.setText("Humodoty: "+dayLists.get(position).getHumidity());
        holder.tvTemp.setText(temp);
        holder.tvMinTemp.setText("Min: "+minTemp);
        holder.tvMaxTemp.setText("Max: "+maxTemp);
    }

    @Override
    public int getItemCount() {
        return dayLists.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder{
        TextView tvDate, tvDescription, tvWind, tvPressure, tvHumidity, tvTemp, tvMinTemp, tvMaxTemp;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            tvDate = itemView.findViewById(R.id.tvDate);
            tvDescription = itemView.findViewById(R.id.tvDescription);
            tvWind = itemView.findViewById(R.id.tvWind);
            tvPressure = itemView.findViewById(R.id.tvPressure);
            tvHumidity = itemView.findViewById(R.id.tvHumidity);
            tvTemp = itemView.findViewById(R.id.tvTemp);
            tvMaxTemp = itemView.findViewById(R.id.tvMaxTemp);
            tvMinTemp = itemView.findViewById(R.id.tvMinTemp);
        }
    }
    //to get current possition id
    @Override
    public long getItemId(int position) {
        return super.getItemId(position);
    }
}
