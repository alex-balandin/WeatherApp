package com.test.weatherapp.ui.details;

import android.annotation.SuppressLint;

import com.test.weatherapp.data.WeatherDataManager;
import com.test.weatherapp.util.Logging;

import javax.inject.Inject;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by alex-balandin on 8/10/18
 */
public class WeatherDetailsPresenter implements WeatherDetailsContract.Presenter {
    private static final String LOG_TAG = "WeatherDetailsPresenter";

    private WeatherDataManager weatherDataManager;

    private WeatherDetailsContract.View view;

    @Inject
    public WeatherDetailsPresenter(WeatherDataManager weatherDataManager) {
        this.weatherDataManager = weatherDataManager;
    }

    @Override
    public void attach(WeatherDetailsContract.View view) {
        this.view = view;
    }

    @Override
    public void detach() {
        this.view = null;
    }

    @SuppressLint("CheckResult")
    @Override
    public void setCityId(int cityId) {
        weatherDataManager.getCityWeatherCached(cityId)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(cityWeather -> {
                    Logging.d(LOG_TAG, "getCityWeatherCached success: " + cityWeather);

                    if (view != null) {
                        view.showWeatherData(cityWeather);
                    }

                }, throwable -> {
                    Logging.d(LOG_TAG, "getCityWeatherCached failed: " + throwable);

                    if (view != null) {
                        view.showGeneralErrorMessage();
                    }
                });
    }
}
