package com.example.openweather.utils;

/**
 * Created by HAMZA_MACB105 on 10/07/16.
 */
public class ApiFailEvent {

    private final String failText;

    public ApiFailEvent(String failText) {
        this.failText = failText;
    }

    public String getFailText() {
        return failText;
    }
}
