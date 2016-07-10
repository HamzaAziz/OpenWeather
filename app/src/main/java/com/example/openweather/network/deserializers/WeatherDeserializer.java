package com.example.openweather.network.deserializers;

import com.example.openweather.models.City;
import com.google.gson.Gson;
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParseException;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.List;

/**
 * Created by HAMZA_MACB105 on 10/07/16.
 */
public class WeatherDeserializer implements JsonDeserializer<List<City>> {


    public static final Type TYPE = new TypeToken<List<City>>() {}.getType();

    // JsonDeserializer

    @Override
    public List<City> deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {

        try {
            JsonObject jsonObject=(JsonObject) json.getAsJsonObject();
            List<City> cities=new Gson().fromJson(jsonObject.getAsJsonArray("list"), TYPE);
            return cities;
        } catch (Exception exception) {

            // On any error - return null
            return null;
        }
    }
}
