package com.test.weatherapp.ui.main.di;

import com.test.weatherapp.di.scope.ActivityScope;
import com.test.weatherapp.ui.main.MainActivity;

import dagger.Subcomponent;

/**
 * Created by alex-balandin on 8/7/18
 */
@ActivityScope
@Subcomponent(
        modules = {MainModule.class}
)
public interface MainComponent {
    void inject(MainActivity mainActivity);
}
