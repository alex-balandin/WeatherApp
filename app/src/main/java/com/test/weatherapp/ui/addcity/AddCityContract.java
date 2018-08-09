package com.test.weatherapp.ui.addcity;

import com.test.weatherapp.ui.common.BasePresenter;

/**
 * Created by alex-balandin on 8/7/18
 */
public interface AddCityContract {

    interface View {
        void showProgressBar();
        void hideProgressBar();

        void showNetworkErrorMessage();
        void showCityNotFoundErrorMessage();
        void showGeneralErrorMessage();

        void finishWithResultOk();
    }

    interface Presenter extends BasePresenter<View> {
        void onAddCityClicked(String cityName);
    }
}
