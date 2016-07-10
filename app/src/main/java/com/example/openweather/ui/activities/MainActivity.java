package com.example.openweather.ui.activities;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;

import com.example.openweather.R;
import com.example.openweather.ui.fragments.CitiesWeatherFragment;
import com.example.openweather.ui.fragments.extras.FragmentBuilder;
import com.trello.rxlifecycle.components.support.RxAppCompatActivity;

import butterknife.ButterKnife;

public class MainActivity extends RxAppCompatActivity implements CitiesWeatherFragment.OnFragmentInteractionListener {

    private Fragment mLoadedFragment;
    private final static String ARG_FRAG_TAG = "loadedfragment";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        mLoadedFragment = FragmentBuilder.loadFragment(FragmentBuilder.FRAGMENT_CITIESWEATHER, new Bundle());
        if (savedInstanceState != null) {
            //Restore the fragment's instance
            mLoadedFragment = getSupportFragmentManager().getFragment(savedInstanceState, ARG_FRAG_TAG);

        }
        switchFragment(mLoadedFragment);

    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);

        //Save the fragment's instance
        if(mLoadedFragment!=null)
            getSupportFragmentManager().putFragment(outState, ARG_FRAG_TAG, mLoadedFragment);
    }

    @Override
    protected void onResume() {
        super.onResume();
    }

    @Override
    protected void onDestroy() {
        mLoadedFragment = null;
        super.onDestroy();
    }

    @Override
    protected void onPause() {
        mLoadedFragment = null;
        super.onPause();
    }


    public void switchFragment(Fragment fragment) {

        String backStateName = fragment.getClass().getName();
        String fragmentTag = backStateName;

        FragmentManager manager = getSupportFragmentManager();
        boolean fragmentPopped = manager.popBackStackImmediate(backStateName, 0);

        if (!fragmentPopped && manager.findFragmentByTag(fragmentTag) == null) { //fragment not in back stack, create it.
            FragmentTransaction ft = manager.beginTransaction();
            ft.replace(R.id.main_container, fragment, fragmentTag);
            ft.addToBackStack(backStateName);
            ft.commit();
        }
        mLoadedFragment = fragment;
    }

    public void changeActionBarTitle(String text) {
        getSupportActionBar().setTitle(text);
    }

    public void backButtonVisibility(boolean visibility) {
        getSupportActionBar().setDisplayHomeAsUpEnabled(visibility);
    }


    /**
     * Fragments Interaction Listeners.
     */

    @Override
    public void onCitiesWeatherFragmentInteraction() {
        changeActionBarTitle(getString(R.string.city_weather));
        backButtonVisibility(false);

    }


}
