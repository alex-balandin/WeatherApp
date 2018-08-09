package com.test.weatherapp.data;

import com.test.weatherapp.data.db.DbManager;
import com.test.weatherapp.data.network.ApiManager;
import com.test.weatherapp.model.CityWeather;

import java.util.List;

import io.reactivex.Completable;
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

    @Override
    public Single<CityWeather> getCityWeatherCached(int cityId) {
        return Single.create(emitter -> {
            CityWeather cityWeather = dbManager.getCityWeather(cityId);
            emitter.onSuccess(cityWeather);
        });
    }

    @Override
    public Single<List<CityWeather>> getAllCitiesWeatherCached() {
        return Single.create(emitter -> {
            List<CityWeather> cityWeatherList = dbManager.getAllCitiesWeather();
            emitter.onSuccess(cityWeatherList);
        });
    }

    @Override
    public Completable deleteCityWeatherCache(int cityId) {
        return Completable.create(emitter -> {
            dbManager.deleteCityWeather(cityId);
            emitter.onComplete();
        });
    }
}
