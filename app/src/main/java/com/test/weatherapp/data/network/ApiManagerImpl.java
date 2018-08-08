package com.test.weatherapp.data.network;

import com.test.weatherapp.data.network.api.WeatherApi;
import com.test.weatherapp.data.network.services.weather.CurrentWeatherService;
import com.test.weatherapp.data.network.services.weather.response.CurrentWeatherResponse;

import io.reactivex.Single;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by alex-balandin on 8/9/18
 */
public class ApiManagerImpl implements ApiManager {

    private static final String APP_ID = "764ccb7c6d69e0bc8557207068e3ec31";

    private WeatherApi weatherApi;

    public ApiManagerImpl() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(WeatherApi.BASE_URL)
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        weatherApi = retrofit.create(WeatherApi.class);
    }

    @Override
    public Single<CurrentWeatherResponse> getCurrentWeather(String cityName) {
        CurrentWeatherService service = new CurrentWeatherService(weatherApi);
        return service.getCurrentWeather(APP_ID, cityName);
    }
}
