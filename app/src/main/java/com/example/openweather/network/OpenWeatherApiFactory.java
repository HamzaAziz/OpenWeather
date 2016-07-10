package com.example.openweather.network;

import com.example.openweather.network.deserializers.WeatherDeserializer;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.squareup.okhttp.OkHttpClient;

import retrofit.GsonConverterFactory;
import retrofit.Retrofit;
import retrofit.RxJavaCallAdapterFactory;

/**
 * Created by HAMZA_MACB105 on 10/07/16.
 */
public class OpenWeatherApiFactory {

    // Constants
    public static final int API_ENDPOINT_WEATHER = 1;
    private static final String API_BASEURL = "http://api.openweathermap.org";
    public static final String API_BASEURL_ICON = API_BASEURL+"/img/w/";

    /**
     * Creates an service instance.
     */
    public static OpenWeatherApiService newApi(int endPoint) {
        return newApi(new OkHttpClient(), endPoint);
    }

    /**
     * Creates an service instance with the given client.
     */
    public static OpenWeatherApiService newApi(OkHttpClient client, int apiEndPoint) {

        GsonBuilder gsonBuilder = new GsonBuilder();
        switch (apiEndPoint) {
            case API_ENDPOINT_WEATHER:
                gsonBuilder.registerTypeAdapter(WeatherDeserializer.TYPE, new WeatherDeserializer());
                break;
            default:

        }

        Gson gson = gsonBuilder.create();

        // Builds API service
        return new Retrofit.Builder()
                .client(client)
                .baseUrl(API_BASEURL)
                .addConverterFactory(GsonConverterFactory.create(gson))
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .build()
                .create(OpenWeatherApiService.class);
    }
}
