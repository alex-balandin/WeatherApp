package com.test.weatherapp.data.network;

import com.test.weatherapp.data.network.services.weather.response.CurrentWeatherResponse;

import io.reactivex.Single;

/**
 * Created by alex-balandin on 8/9/18
 */
public interface ApiManager {

    Single<CurrentWeatherResponse> getCurrentWeather(String cityName);
}
