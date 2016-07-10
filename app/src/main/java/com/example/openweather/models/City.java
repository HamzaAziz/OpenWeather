package com.example.openweather.models;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.List;

/**
 * Created by HAMZA_MACB105 on 10/07/16.
 */
public class City implements Serializable {

    // Properties

    @SerializedName("name")
    public String name;

    @SerializedName("weather")
    public List<Weather> weathers;



}
