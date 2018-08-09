package com.test.weatherapp.ui.details.di;

import com.test.weatherapp.di.scope.ActivityScope;
import com.test.weatherapp.ui.addcity.AddCityActivity;
import com.test.weatherapp.ui.addcity.di.AddCityModule;
import com.test.weatherapp.ui.details.WeatherDetailsActivity;

import dagger.Subcomponent;

/**
 * Created by alex-balandin on 8/7/18
 */
@ActivityScope
@Subcomponent(
        modules = {WeatherDetailsModule.class}
)
public interface WeatherDetailsComponent {
    void inject(WeatherDetailsActivity weatherDetailsActivity);
}
