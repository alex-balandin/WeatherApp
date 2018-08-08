package com.test.weatherapp.ui.main.di;

import com.test.weatherapp.di.scope.ActivityScope;
import com.test.weatherapp.ui.main.MainContract;
import com.test.weatherapp.ui.main.MainPresenter;

import dagger.Module;
import dagger.Provides;

/**
 * Created by alex-balandin on 8/7/18
 */
@Module
public class MainModule {

    private MainContract.View mainView;

    public MainModule(MainContract.View mainView) {
        this.mainView = mainView;
    }

    @Provides
    @ActivityScope
    MainContract.View provideMainView() {
        return mainView;
    }

    @Provides
    @ActivityScope
    MainContract.Presenter provideMainPresenter(MainPresenter presenter) {
        return presenter;
    }
}
