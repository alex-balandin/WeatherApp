package com.test.weatherapp.ui.main;

import com.test.weatherapp.ui.common.BasePresenter;

/**
 * Created by alex-balandin on 8/7/18
 */
public interface MainContract {

    interface View {
        void showMessage(String message);
        void showErrorMessage(String message);
    }

    interface Presenter extends BasePresenter<View> {
    }
}
