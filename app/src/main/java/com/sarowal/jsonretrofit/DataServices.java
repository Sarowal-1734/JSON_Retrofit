package com.sarowal.jsonretrofit;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface DataServices {
    //end uel: data/2.5/forecast/daily?q=Dhaka&APPID=62f6de3f7c0803216a3a13bbe4ea9914&cnt=7
    @GET("data/2.5/forecast/daily")
    Call<DailyForecast> getDailyForecast(@Query("q") String city, @Query("APPID") String apikey, @Query("cnt") int cnt);
}
