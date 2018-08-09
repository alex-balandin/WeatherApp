package com.test.weatherapp.ui.addcity.di;

import com.test.weatherapp.di.scope.ActivityScope;
import com.test.weatherapp.ui.addcity.AddCityActivity;

import dagger.Subcomponent;

/**
 * Created by alex-balandin on 8/7/18
 */
@ActivityScope
@Subcomponent(
        modules = {AddCityModule.class}
)
public interface AddCityComponent {
    void inject(AddCityActivity addCityActivity);
}
