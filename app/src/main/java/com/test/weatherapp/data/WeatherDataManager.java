package com.test.weatherapp.data;

import com.test.weatherapp.model.CityWeather;

import io.reactivex.Single;

/**
 * Created by alex-balandin on 8/7/18
 */
public interface WeatherDataManager {

    Single<CityWeather> fetchCurrentWeather(String cityName);
}
