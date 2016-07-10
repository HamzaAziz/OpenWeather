package com.example.openweather.models;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

/**
 * Created by HAMZA_MACB105 on 10/07/16.
 */
public class Weather implements Serializable {

    // Properties

    @SerializedName("description")
    public String description;

    @SerializedName("icon")
    public String icon;



}
