package com.test.weatherapp.data.network.api;

import com.test.weatherapp.data.network.services.weather.response.CurrentWeatherResponse;

import io.reactivex.Single;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * Created by alex-balandin on 8/9/18
 */
public interface WeatherApi {
    String BASE_URL = "https://api.openweathermap.org/data/2.5/";

    @GET("weather/")
    Single<CurrentWeatherResponse> getCurrentWeather(@Query("appid") String appId,
                                                     @Query("q") String cityName,
                                                     @Query("units") String units);
}
