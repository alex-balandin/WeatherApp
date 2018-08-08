package com.test.weatherapp.ui.main;

import android.annotation.SuppressLint;

import com.test.weatherapp.data.WeatherDataManager;
import com.test.weatherapp.exceptions.CityNotFoundException;
import com.test.weatherapp.exceptions.NetworkException;
import com.test.weatherapp.util.Logging;

import javax.inject.Inject;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by alex-balandin on 8/7/18
 */
public class MainPresenter implements MainContract.Presenter {

    private static final String LOG_TAG = "MainPresenter";

    private static final String DEFAULT_CITY = "Kyiv";

    private MainContract.View view;

    private WeatherDataManager weatherDataManager;

    @Inject
    public MainPresenter(WeatherDataManager weatherDataManager) {
        this.weatherDataManager = weatherDataManager;
    }

    @Override
    public void attach(MainContract.View view) {
        this.view = view;
        fetchCurrentWeather(DEFAULT_CITY);
    }

    @Override
    public void detach() {
        this.view = null;
    }

    @SuppressLint("CheckResult")
    private void fetchCurrentWeather(String cityName) {
        weatherDataManager.fetchCurrentWeather(cityName)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(response -> {
                    Logging.d(LOG_TAG, "fetchCurrentWeather success");

                    if (view != null) {
                        view.showMessage("!!!!!!!!!");
                    }

                }, throwable -> {
                    Logging.d(LOG_TAG, "fetchCurrentWeather failed: " + throwable);

                    if (view != null) {
                        if (throwable instanceof NetworkException) {

                        } else if (throwable instanceof CityNotFoundException) {

                        } else {

                        }
                    }
                });
    }
}
