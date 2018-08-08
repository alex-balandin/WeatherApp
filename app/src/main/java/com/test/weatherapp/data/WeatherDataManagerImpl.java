package com.test.weatherapp.data;

import android.app.Application;

import com.test.weatherapp.data.network.ApiManager;
import com.test.weatherapp.data.network.services.weather.response.CurrentWeatherResponse;

import io.reactivex.Single;

/**
 * Created by alex-balandin on 8/7/18
 */
public class WeatherDataManagerImpl implements WeatherDataManager {

    private Application application;
    private ApiManager apiManager;

    public WeatherDataManagerImpl(Application application, ApiManager apiManager) {
        this.application = application;
        this.apiManager = apiManager;
    }

    @Override
    public Single<CurrentWeatherResponse> fetchCurrentWeather(String cityName) {
        return apiManager.getCurrentWeather(cityName);
    }
}
