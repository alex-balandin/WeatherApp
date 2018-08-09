package com.test.weatherapp.ui.main;

import com.test.weatherapp.model.CityWeather;
import com.test.weatherapp.ui.common.BasePresenter;

import java.util.List;

/**
 * Created by alex-balandin on 8/7/18
 */
public interface MainContract {

    interface View {
        void setCitiesWeatherData(List<CityWeather> data);
        void showProgressBar();
        void hideProgressBar();
        void showNetworkErrorMessage();
        void showGeneralErrorMessage();

        void finishRefresh();
    }

    interface Presenter extends BasePresenter<View> {
        void onCityWeatherClicked(int cityId);
        void onDeleteClicked(int cityId);

        void onSwipeToRefresh();
    }
}
