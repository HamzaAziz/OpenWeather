package com.example.openweather.ui.adapters.viewholder;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.openweather.R;
import com.example.openweather.viewmodels.CitiesWeatherViewModel;
import com.squareup.picasso.Picasso;
import com.trello.rxlifecycle.RxLifecycle;

import butterknife.BindView;
import butterknife.ButterKnife;


public class CitiesWeatherViewHolder extends RecyclerView.ViewHolder {

    @BindView(R.id.city_name)
    protected TextView mCityNameTextView;

    @BindView(R.id.weather_desc)
    protected TextView mWeatherDescTextView;

    @BindView(R.id.weather_thumbnail)
    protected ImageView mThumnailImageView;


    private final Context mContext;
    private final CitiesWeatherViewModel mViewModel;


    public CitiesWeatherViewHolder(Context context, View itemView) {
        super(itemView);

        mContext = context;

        mViewModel = new CitiesWeatherViewModel();

        ButterKnife.bind(this, itemView);

    }

    /**
     * Bind all subject to it's views.
     */
    public void bind() {

        // Bind text
        mViewModel.cityName().compose(RxLifecycle.bindView(itemView)).subscribe(mCityNameTextView::setText);
        mViewModel.weatherDesc().compose(RxLifecycle.bindView(itemView)).subscribe(mWeatherDescTextView::setText);

        // Bind thumbnail
        mViewModel.thumbnail()
                .map(url -> Picasso.with(mContext).load(url))
                .compose(RxLifecycle.bindView(itemView))
                .subscribe(picasso -> {
                    picasso.into(mThumnailImageView);
                });

      }

    public CitiesWeatherViewModel getViewModel() {
        return mViewModel;
    }
}
