package com.test.weatherapp.ui.addcity.di;

import com.test.weatherapp.di.scope.ActivityScope;
import com.test.weatherapp.ui.addcity.AddCityContract;
import com.test.weatherapp.ui.addcity.AddCityPresenter;

import dagger.Module;
import dagger.Provides;

/**
 * Created by alex-balandin on 8/7/18
 */
@Module
public class AddCityModule {

    private AddCityContract.View mainView;

    public AddCityModule(AddCityContract.View mainView) {
        this.mainView = mainView;
    }

    @Provides
    @ActivityScope
    AddCityContract.View provideAddCityView() {
        return mainView;
    }

    @Provides
    @ActivityScope
    AddCityContract.Presenter provideAddCityPresenter(AddCityPresenter presenter) {
        return presenter;
    }
}
