package com.test.weatherapp.data;

import com.test.weatherapp.model.CityWeather;

import java.util.List;

import io.reactivex.Completable;
import io.reactivex.Single;

/**
 * Created by alex-balandin on 8/7/18
 */
public interface WeatherDataManager {

    Single<CityWeather> fetchCurrentWeather(String cityName);

    Single<CityWeather> getCityWeatherCached(int cityId);

    Single<List<CityWeather>> getAllCitiesWeatherCached();

    Completable deleteCityWeatherCache(int cityId);
}
