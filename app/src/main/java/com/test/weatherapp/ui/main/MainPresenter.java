package com.test.weatherapp.ui.main;

import android.annotation.SuppressLint;

import com.test.weatherapp.data.WeatherDataManager;
import com.test.weatherapp.exceptions.CityNotFoundException;
import com.test.weatherapp.exceptions.NetworkException;
import com.test.weatherapp.model.CityWeather;
import com.test.weatherapp.util.Logging;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import io.reactivex.Single;
import io.reactivex.SingleSource;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Function;
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
        fetchCitiesWeather(true, false);
    }

    @Override
    public void detach() {
        this.view = null;
    }

    @SuppressLint("CheckResult")
    private void fetchCitiesWeather(boolean isShowNetworkErrorMessage, boolean isSwipeToRefresh) {
        if (!isSwipeToRefresh) {
            view.showProgressBar();
        }
        weatherDataManager.getAllCitiesWeatherCached()
                .flatMap(cityWeathers -> {
                    List<Single<CityWeather>> fetchWeatherSingles = new ArrayList<>();
                    for (CityWeather cityWeather : cityWeathers) {
                        Single<CityWeather> fetchWeatherSingle = weatherDataManager.fetchCurrentWeather(cityWeather.getCityName());
                        fetchWeatherSingles.add(fetchWeatherSingle);
                    }

                    if (fetchWeatherSingles.size() == 0) {
                        Single<CityWeather> fetchWeatherSingle = weatherDataManager.fetchCurrentWeather(DEFAULT_CITY);
                        fetchWeatherSingles.add(fetchWeatherSingle);
                    }

                    return Single.zip(fetchWeatherSingles, objects -> {
                        List<CityWeather> resultList = new ArrayList<>();
                        for (Object obj : objects){
                            CityWeather cityWeather = (CityWeather) obj;
                            resultList.add(cityWeather);
                        }
                        return resultList;
                    });
                })
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(cityWeathers -> {
                    Logging.d(LOG_TAG, "fetchWeatherInitial success: " + cityWeathers);

                    if (view != null) {
                        view.hideProgressBar();
                        view.finishRefresh();
                        view.setCitiesWeatherData(cityWeathers);
                    }

                }, throwable -> {
                    Logging.d(LOG_TAG, "fetchWeatherInitial failed: " + throwable);

                    if (view != null) {
                        view.hideProgressBar();
                        view.finishRefresh();
                        if (throwable instanceof NetworkException && isShowNetworkErrorMessage) {
                            view.showNetworkErrorMessage();
                        } else {
                            view.showGeneralErrorMessage();
                        }
                    }
                });
    }

    @Override
    public void onCityWeatherClicked(int cityId) {

    }

    @Override
    public void onDeleteClicked(int cityId) {

    }

    @Override
    public void onAddButtonClicked() {
        view.showAddCityScreen();
    }

    @Override
    public void onAddCitySuccess() {
        fetchCitiesWeather(true,  false);
    }

    @Override
    public void onSwipeToRefresh() {
        fetchCitiesWeather(false,  true);
    }
}
