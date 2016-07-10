package com.example.openweather.ui.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.openweather.R;
import com.example.openweather.models.City;
import com.example.openweather.ui.adapters.viewholder.CitiesWeatherViewHolder;

import java.util.List;

/**
 * Adapter for displaying cities.
 */
public class CitiesWeatherAdapter extends RecyclerView.Adapter<CitiesWeatherViewHolder>  {


    private final List<City> mCities;
    private final Context mContext;

    public CitiesWeatherAdapter(Context context, List<City> cities) {
        super();

        mContext = context;
        mCities = cities;
    }


    @Override
    public CitiesWeatherViewHolder onCreateViewHolder(ViewGroup viewGroup, int viewType) {
        View itemView = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_view_cityweather, viewGroup, false);
        return new CitiesWeatherViewHolder(mContext, itemView);
    }

    @Override
    public int getItemCount() {
        return mCities.size();
    }

    @Override
    public void onBindViewHolder(CitiesWeatherViewHolder citiesViewHolder, int position) {

        // Rebind
        citiesViewHolder.bind();

        // Send current data to view model
        City city = mCities.get(position);
        citiesViewHolder.getViewModel().setCityItemCommand.call(city);
    }




}
