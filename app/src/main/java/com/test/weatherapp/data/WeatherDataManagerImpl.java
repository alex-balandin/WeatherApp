package com.test.weatherapp.data;

import com.test.weatherapp.data.db.DbManager;
import com.test.weatherapp.data.network.ApiManager;
import com.test.weatherapp.model.CityWeather;

import io.reactivex.Single;

/**
 * Created by alex-balandin on 8/7/18
 */
public class WeatherDataManagerImpl implements WeatherDataManager {

    private ApiManager apiManager;
    private DbManager dbManager;

    public WeatherDataManagerImpl(ApiManager apiManager, DbManager dbManager) {
        this.apiManager = apiManager;
        this.dbManager = dbManager;
    }

    @Override
    public Single<CityWeather> fetchCurrentWeather(String cityName) {
        return apiManager.getCurrentWeather(cityName)
                .map(response -> {
                    long timestamp = System.currentTimeMillis();
                    CityWeather cityWeather = new CityWeather(response, timestamp);

                    dbManager.saveCityWeather(cityWeather);

                    return cityWeather;
                });
    }
}
