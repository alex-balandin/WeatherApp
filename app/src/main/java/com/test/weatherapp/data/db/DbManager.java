package com.test.weatherapp.data.db;

import com.test.weatherapp.model.CityWeather;

import java.util.List;

/**
 * Created by alex-balandin on 8/9/18
 */
public interface DbManager {

    void saveCityWeather(CityWeather cityWeather);

    CityWeather getCityWeather(int cityId);

    List<CityWeather> getAllCitiesWeather();

    void deleteCityWeather(int cityId);
}
