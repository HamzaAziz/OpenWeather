package com.example.openweather.viewmodels;

import com.example.openweather.controllers.CitiesWeatherController;
import com.example.openweather.models.City;
import com.example.openweather.models.WeatherAPIArguments;
import com.example.openweather.network.OpenWeatherApiFactory;
import com.jakewharton.rxrelay.PublishRelay;

import java.util.List;

import rx.Observable;
import rx.subjects.BehaviorSubject;

/**
 * Created by HAMZA_MACB105 on 10/07/16.
 */
public final class CitiesWeatherViewModel {

    // Observable

    public final Observable<String> cityName() {
        return mCityNameSubject.asObservable();
    }

    public final Observable<String> weatherDesc() {
        return mWeatherDescSubject.asObservable();
    }

    public final Observable<String> thumbnail() {
        return mThumbnailUrlSubject.asObservable();
    }

    public final Observable<List<City>> cities() {
        return mCities.asObservable();
    }




    // Commands

    public final PublishRelay<WeatherAPIArguments> loadCities = PublishRelay.create();
    /**
     * Send item to this command to trigger changes to the displayable subjects.
     */
    public final PublishRelay<City> setCityItemCommand = PublishRelay.create();


    // Members

    private final BehaviorSubject<List<City>> mCities = BehaviorSubject.create();
    private final BehaviorSubject<String> mCityNameSubject = BehaviorSubject.create();
    private final BehaviorSubject<String> mWeatherDescSubject = BehaviorSubject.create();
    private final BehaviorSubject<String> mThumbnailUrlSubject = BehaviorSubject.create();


    public CitiesWeatherViewModel() {
        this(new CitiesWeatherController());
    }

    public CitiesWeatherViewModel(CitiesWeatherController controller) {
        super();


        loadCities
                .concatMap(controller::getCities)
                .retry()
                .subscribe(mCities::onNext);


        setCityItemCommand
                .map(city -> {
                    if (city.name == null) {
                        return "";
                    }
                    return city.name;
                })
                .retry()
                .subscribe(mCityNameSubject::onNext);

        setCityItemCommand
                .map(city -> {
                    if (city.weathers.isEmpty()) {
                        return "";
                    }
                    return city.weathers.get(0).description;
                })
                .retry()
                .subscribe(mWeatherDescSubject::onNext);


        setCityItemCommand
                .map(city -> {
                    if (city.weathers.isEmpty()) {
                        return "";
                    }
                    return OpenWeatherApiFactory.API_BASEURL_ICON+city.weathers.get(0).icon+".png";
                })
                .retry()
                .subscribe(mThumbnailUrlSubject::onNext);


    }
}
