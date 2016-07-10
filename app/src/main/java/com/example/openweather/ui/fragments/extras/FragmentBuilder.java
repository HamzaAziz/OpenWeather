package com.example.openweather.ui.fragments.extras;

import android.os.Bundle;

import com.example.openweather.ui.fragments.BaseFragment;
import com.example.openweather.ui.fragments.CitiesWeatherFragment;

/**
 * Created by HAMZA_MACB105 on 10/07/16.
 */
public class FragmentBuilder {

    public final static int FRAGMENT_CITIESWEATHER = 1;

    public static BaseFragment loadFragment(int fragmentId, Bundle arguments) {
        BaseFragment fragment;
        switch (fragmentId) {
            case FRAGMENT_CITIESWEATHER:
                fragment = CitiesWeatherFragment.newInstance();
                fragment.setArguments(arguments);
                break;

            default:
                fragment = BaseFragment.newInstance();
        }
        return fragment;
    }

}

