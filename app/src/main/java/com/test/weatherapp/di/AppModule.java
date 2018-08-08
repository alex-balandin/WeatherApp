package com.test.weatherapp.di;

import com.test.weatherapp.application.WeatherApplication;
import com.test.weatherapp.data.WeatherDataManager;
import com.test.weatherapp.data.WeatherDataManagerImpl;
import com.test.weatherapp.data.network.ApiManagerImpl;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

/**
 * Created by alex-balandin on 8/7/18
 */
@Module
public class AppModule {
    WeatherApplication app;

    public AppModule(WeatherApplication application) {
        app = application;
    }

    @Provides
    @Singleton
    protected WeatherDataManager provideWeatherDataManager() {
        return new WeatherDataManagerImpl(app, new ApiManagerImpl());
    }
}
