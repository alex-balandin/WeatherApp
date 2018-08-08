package com.test.weatherapp.application;

import android.app.Application;

import com.test.weatherapp.di.AppComponent;
import com.test.weatherapp.di.AppModule;
import com.test.weatherapp.di.DaggerAppComponent;

/**
 * Created by alex-balandin on 8/7/18
 */
public class WeatherApplication extends Application {
    private static AppComponent appComponent;

    @Override
    public void onCreate() {
        super.onCreate();
        initAppComponents();
    }

    public static AppComponent getAppComponent() {
        return appComponent;
    }

    public void initAppComponents() {
        appComponent = DaggerAppComponent.builder()
                .appModule(new AppModule(this))
                .build();

    }
}
