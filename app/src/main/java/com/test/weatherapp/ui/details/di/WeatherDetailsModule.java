package com.test.weatherapp.ui.details.di;

import com.test.weatherapp.di.scope.ActivityScope;
import com.test.weatherapp.ui.addcity.AddCityContract;
import com.test.weatherapp.ui.addcity.AddCityPresenter;
import com.test.weatherapp.ui.details.WeatherDetailsContract;
import com.test.weatherapp.ui.details.WeatherDetailsPresenter;

import dagger.Module;
import dagger.Provides;

/**
 * Created by alex-balandin on 8/7/18
 */
@Module
public class WeatherDetailsModule {

    private WeatherDetailsContract.View view;

    public WeatherDetailsModule(WeatherDetailsContract.View view) {
        this.view = view;
    }

    @Provides
    @ActivityScope
    WeatherDetailsContract.View provideWeatherDetailsView() {
        return view;
    }

    @Provides
    @ActivityScope
    WeatherDetailsContract.Presenter provideWeatherDetailsPresenter(WeatherDetailsPresenter presenter) {
        return presenter;
    }
}
