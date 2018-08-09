package com.test.weatherapp.ui.addcity;

import android.annotation.SuppressLint;

import com.test.weatherapp.data.WeatherDataManager;
import com.test.weatherapp.exceptions.CityNotFoundException;
import com.test.weatherapp.exceptions.NetworkException;
import com.test.weatherapp.util.Logging;

import javax.inject.Inject;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by alex-balandin on 8/10/18
 */
public class AddCityPresenter implements AddCityContract.Presenter {
    private static final String LOG_TAG = "AddCityPresenter";

    private WeatherDataManager weatherDataManager;

    private AddCityContract.View view;

    @Inject
    public AddCityPresenter(WeatherDataManager weatherDataManager) {
        this.weatherDataManager = weatherDataManager;
    }

    @Override
    public void attach(AddCityContract.View view) {
        this.view = view;
    }

    @Override
    public void detach() {
        this.view = null;
    }

    @SuppressLint("CheckResult")
    @Override
    public void onAddCityClicked(String cityName) {
        view.showProgressBar();
        weatherDataManager.fetchCurrentWeather(cityName)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(cityWeather -> {
                    Logging.d(LOG_TAG, "fetchCurrentWeather success: " + cityWeather);

                    if (view != null) {
                        view.hideProgressBar();
                        view.finishWithResultOk();
                    }

                }, throwable -> {
                    Logging.d(LOG_TAG, "fetchCurrentWeather failed: " + throwable);

                    if (view != null) {
                        view.hideProgressBar();

                        if (throwable instanceof NetworkException) {
                            view.showNetworkErrorMessage();
                        } else if (throwable instanceof CityNotFoundException) {
                            view.showCityNotFoundErrorMessage();
                        } else {
                            view.showGeneralErrorMessage();
                        }
                    }
                });
    }
}
