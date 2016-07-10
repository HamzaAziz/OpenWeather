package com.example.openweather.models;

/**
 * Created by HAMZA_MACB105 on 10/07/16.
 */
public class WeatherAPIArguments {

    private String bbox;
    private String cluster;
    private String appid;

    public String getBbox() {
        return bbox;
    }

    public void setBbox(String bbox) {
        this.bbox = bbox;
    }

    public String getAppid() {
        return appid;
    }

    public void setAppid(String appid) {
        this.appid = appid;
    }

    public String getCluster() {
        return cluster;
    }

    public void setCluster(String cluster) {
        this.cluster = cluster;
    }
}
