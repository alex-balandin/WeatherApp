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

    private AddCityContract.View view;

    public AddCityModule(AddCityContract.View mainView) {
        this.view = mainView;
    }

    @Provides
    @ActivityScope
    AddCityContract.View provideAddCityView() {
        return view;
    }

    @Provides
    @ActivityScope
    AddCityContract.Presenter provideAddCityPresenter(AddCityPresenter presenter) {
        return presenter;
    }
}
