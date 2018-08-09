package com.test.weatherapp.di;

import com.test.weatherapp.ui.addcity.di.AddCityComponent;
import com.test.weatherapp.ui.addcity.di.AddCityModule;
import com.test.weatherapp.ui.details.di.WeatherDetailsComponent;
import com.test.weatherapp.ui.details.di.WeatherDetailsModule;
import com.test.weatherapp.ui.main.di.MainComponent;
import com.test.weatherapp.ui.main.di.MainModule;

import javax.inject.Singleton;

import dagger.Component;

/**
 * Created by alex-balandin on 8/7/18
 */
@Singleton
@Component(modules = {AppModule.class})
public interface AppComponent {
    MainComponent plus(MainModule module);
    AddCityComponent plus(AddCityModule module);
    WeatherDetailsComponent plus(WeatherDetailsModule module);
}
