package com.example.openweather.network;

import com.example.openweather.models.City;

import java.util.List;

import retrofit.http.GET;
import retrofit.http.Query;
import rx.Observable;

/**
 * Created by HAMZA_MACB105 on 10/07/16.
 */
public interface OpenWeatherApiService {

// GET

    /**
     * Returns the obeservable list of weather.
     */
    // example http://api.openweathermap.org/data/2.5/box/city?bbox=12,32,15,37,10&cluster=yes&APPID=1be233ec7cc2edbb0391f48c9beed773
    @GET("/data/2.5/box/city")
    Observable<List<City>> getWeatherByCities(@Query("bbox") String bbox, @Query("cluster") String cluster, @Query("APPID") String APPID);
}
