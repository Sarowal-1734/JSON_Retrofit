package com.sarowal.jsonretrofit;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Toast;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Handler
        Retrofit retrofit = RetrofitClientInstance.getRetrofitInstance();
        DataServices dataServices = retrofit.create(DataServices.class);
        Call<DailyForecast> call = dataServices.getDailyForecast("Dhaka","62f6de3f7c0803216a3a13bbe4ea9914",7);

       //Sending request Asynchronously
        call.enqueue(new Callback<DailyForecast>() {
            @Override
            public void onResponse(Call<DailyForecast> call, Response<DailyForecast> response) {
                DailyForecast dailyForecast = response.body();
                if(response.isSuccessful())
                    Toast.makeText(MainActivity.this,dailyForecast.getList().size()+"",Toast.LENGTH_SHORT).show();
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