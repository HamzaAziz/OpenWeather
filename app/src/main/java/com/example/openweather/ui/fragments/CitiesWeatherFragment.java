package com.example.openweather.ui.fragments;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.openweather.R;
import com.example.openweather.models.WeatherAPIArguments;
import com.example.openweather.ui.adapters.CitiesWeatherAdapter;
import com.example.openweather.utils.ApiFailEvent;
import com.example.openweather.utils.InternetConnectivity;
import com.example.openweather.viewmodels.CitiesWeatherViewModel;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import butterknife.BindView;
import butterknife.ButterKnife;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;


public class CitiesWeatherFragment extends BaseFragment {

    private CitiesWeatherViewModel mCitiesWeatherViewModel;

    @BindView(R.id.cityweather_recycler_view)
    protected RecyclerView mRecyclerView;

    @BindView(R.id.infoTextView)
    protected TextView minfoView;

    private CitiesWeatherAdapter mCitiesWeatherAdapter;
    private OnFragmentInteractionListener mListener;

    public CitiesWeatherFragment() {
    }

    public static CitiesWeatherFragment newInstance() {
        CitiesWeatherFragment frag = new CitiesWeatherFragment();
        frag.setRetainInstance(true);
        return frag;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_cityweather, container, false);
        ButterKnife.bind(this, view);
        loadCitiesIfInternetAvailable();
        if (mListener != null) {
            mListener.onCitiesWeatherFragmentInteraction();
        }
        return view;
    }

    private void loadCitiesIfInternetAvailable() {
        if (InternetConnectivity.isNetworkAvailable(getActivity())) {
            initView();
            initViewModel();
            loadCities();
        } else {
            minfoView.setText(getString(R.string.nointenet));
        }
    }

    private void initView() {
        // Recycler
        LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity());
        mRecyclerView.setLayoutManager(layoutManager);
        mRecyclerView.setHasFixedSize(true);

    }

    private void initViewModel() {
        // View model
        mCitiesWeatherViewModel = new CitiesWeatherViewModel();
        // Bind search results

        mCitiesWeatherViewModel.cities()
                .map(cities -> mCitiesWeatherAdapter = new CitiesWeatherAdapter(getActivity(), cities))
                .compose(bindToLifecycle())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<CitiesWeatherAdapter>() {
                    @Override
                    public void onCompleted() {
                        minfoView.setText("");
                    }

                    @Override
                    public void onError(Throwable e) {
                        minfoView.setText(getString(R.string.fetchingerror));
                    }

                    @Override
                    public void onNext(CitiesWeatherAdapter citiesWeatherAdapter) {
                        minfoView.setText("");
                        mRecyclerView.setAdapter(citiesWeatherAdapter);
                    }
                });
    }

    private void loadCities() {
        minfoView.setText(getString(R.string.fetching));
        WeatherAPIArguments args=new WeatherAPIArguments();
        args.setBbox("12,32,15,37,10");
        args.setCluster("yes");
        args.setAppid(getString(R.string.api_key));
        mCitiesWeatherViewModel.loadCities.call(args);
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnFragmentInteractionListener) {
            mListener = (OnFragmentInteractionListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnFragmentInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    @Override
    public void onPause() {
        super.onPause();
        EventBus.getDefault().unregister(this);
    }

    @Override
    public void onResume() {
        super.onResume();
        EventBus.getDefault().register(this);
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onEvent(ApiFailEvent event) {
        if (minfoView != null)
            minfoView.setText(event.getFailText());
    }

    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     */
    public interface OnFragmentInteractionListener {
        void onCitiesWeatherFragmentInteraction();
    }
}
