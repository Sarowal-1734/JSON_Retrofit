package com.sarowal.jsonretrofit;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.widget.Toast;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class MainActivity extends AppCompatActivity {

    RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        recyclerView = findViewById(R.id.recyclerVisw);
        //Handler
        Retrofit retrofit = RetrofitClientInstance.getRetrofitInstance();
        DataServices dataServices = retrofit.create(DataServices.class);
        Call<DailyForecast> call = dataServices.getDailyForecast("Dhaka","62f6de3f7c0803216a3a13bbe4ea9914",7);

       //Sending request Asynchronously
        call.enqueue(new Callback<DailyForecast>() {
            @Override
            public void onResponse(Call<DailyForecast> call, Response<DailyForecast> response) {
                if(response.isSuccessful()){
                    DailyForecast dailyForecast = response.body();
                    List<DayList> dayLists = dailyForecast.getList();
                    WeatherAdapter weatherAdapter = new WeatherAdapter(MainActivity.this,dayLists);
                    recyclerView.setAdapter(weatherAdapter);
                    //Set RecyclerView's Orientation
                    LinearLayoutManager linearLayoutManager = new LinearLayoutManager(MainActivity.this);
                    linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
                    recyclerView.setLayoutManager(linearLayoutManager);
                }
                else
                    Toast.makeText(MainActivity.this,"Something is wrong! Please try again.",Toast.LENGTH_SHORT).show();
            }
            @Override
            public void onFailure(Call<DailyForecast> call, Throwable t) {
                Toast.makeText(MainActivity.this,"Failed!",Toast.LENGTH_SHORT).show();
            }
        });
    }
}