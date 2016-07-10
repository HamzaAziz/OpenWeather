package com.example.openweather.controllers;


import com.example.openweather.models.City;
import com.example.openweather.models.WeatherAPIArguments;
import com.example.openweather.network.OpenWeatherApiFactory;
import com.example.openweather.network.OpenWeatherApiService;
import com.example.openweather.utils.ApiFailEvent;

import org.greenrobot.eventbus.EventBus;

import java.util.ArrayList;
import java.util.List;

import rx.Observable;
import rx.functions.Action1;
import rx.schedulers.Schedulers;

/**
 * Created by HAMZA_MACB105 on 10/07/16.
 */
public class CitiesWeatherController {

    private OpenWeatherApiService mWeatherApiService;


    public CitiesWeatherController() {
        this(OpenWeatherApiFactory.newApi(OpenWeatherApiFactory.API_ENDPOINT_WEATHER));
    }


    public CitiesWeatherController(OpenWeatherApiService apiService) {
        super();
        mWeatherApiService = apiService;
    }

    /**
     * Fetches the weather of the cities within the box values.
     */
    public Observable<List<City>> getCities(WeatherAPIArguments args) {

        return mWeatherApiService
                .getWeatherByCities(args.getBbox(),args.getCluster(),args.getAppid())
                .subscribeOn(Schedulers.io())
                .doOnError(new Action1<Throwable>() {
                    @Override
                    public void call(Throwable throwable) {
                        EventBus.getDefault().post(new ApiFailEvent("Something went wrong with network, please try again"));
                    }
                })
                .map(cities -> cities == null ? new ArrayList<>() : cities);

    }
}
