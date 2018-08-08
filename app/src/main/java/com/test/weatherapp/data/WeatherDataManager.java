package com.test.weatherapp.data;

import com.test.weatherapp.data.network.services.weather.response.CurrentWeatherResponse;

import io.reactivex.Single;

/**
 * Created by alex-balandin on 8/7/18
 */
public interface WeatherDataManager {

    Single<CurrentWeatherResponse> fetchCurrentWeather(String cityName);
}
