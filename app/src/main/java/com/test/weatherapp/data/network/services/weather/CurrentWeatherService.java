package com.test.weatherapp.data.network.services.weather;

import com.test.weatherapp.data.network.api.WeatherApi;
import com.test.weatherapp.data.network.services.weather.response.CurrentWeatherResponse;
import com.test.weatherapp.exceptions.CityNotFoundException;
import com.test.weatherapp.exceptions.NetworkException;
import com.test.weatherapp.exceptions.UnknownStatusCodeException;

import java.io.IOException;

import io.reactivex.Single;
import retrofit2.HttpException;

/**
 * Created by alex-balandin on 8/9/18
 */
public class CurrentWeatherService {
    private static final String LOG_TAG = "CurrentWeatherService";

    private static final int STATUS_CODE_OK = 200;

    private static final int HTTP_CODE_CITY_NOT_FOUND = 404;

    private static final String UNITS = "metric";

    private WeatherApi weatherApi;

    public CurrentWeatherService(WeatherApi weatherApi) {
        this.weatherApi = weatherApi;
    }

    public Single<CurrentWeatherResponse> getCurrentWeather(String appId, String cityName) {
        return weatherApi.getCurrentWeather(appId, cityName, UNITS)
                .onErrorResumeNext(this::handleError)
                .flatMap(this::handleStatusCode);
    }

    private Single<CurrentWeatherResponse> handleError(Throwable throwable) {
        if (throwable instanceof IOException) {
            return Single.error(new NetworkException());

        } else if (throwable instanceof HttpException
                && ((HttpException) throwable).code() == HTTP_CODE_CITY_NOT_FOUND) {
            return Single.error(new CityNotFoundException());
        }
        return Single.error(throwable);
    }

    private Single<CurrentWeatherResponse> handleStatusCode(CurrentWeatherResponse response) {
        int code = response.getCod();

        if (code == STATUS_CODE_OK) {
            return Single.just(response);
        } else {
            return Single.error(new UnknownStatusCodeException(code));
        }
    }
}
