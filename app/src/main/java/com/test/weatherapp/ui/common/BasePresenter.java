package com.test.weatherapp.ui.common;

/**
 * Created by alex-balandin on 8/7/18
 */
public interface BasePresenter<V> {
    void attach(V view);
    void detach();
}
