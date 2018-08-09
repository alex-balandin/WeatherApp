package com.test.weatherapp.ui.details;

import com.test.weatherapp.model.CityWeather;
import com.test.weatherapp.ui.common.BasePresenter;

/**
 * Created by alex-balandin on 8/7/18
 */
public interface WeatherDetailsContract {

    interface View {
        void showWeatherData(CityWeather cityWeather);
        void showGeneralErrorMessage();
    }

    interface Presenter extends BasePresenter<View> {
        void setCityId(int cityId);
    }
}
