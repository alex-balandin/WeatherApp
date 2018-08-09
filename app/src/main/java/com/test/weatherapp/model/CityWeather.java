package com.test.weatherapp.model;

import com.test.weatherapp.data.network.services.weather.response.CurrentWeatherResponse;

public class CityWeather {

    private int cityId;
    private String cityName;

    private String weatherDescription;
    private double tempCelsius;
    private double pressure;
    private double humidityPercents;
    private double windSpeed;
    private double windDegree;

    private long timestamp;

    public CityWeather(int cityId, String cityName,
                       String weatherDescription,
                       double tempCelsius, double pressure, double humidityPercents,
                       double windSpeed, double windDegree,
                       long timestamp) {
        this.cityId = cityId;
        this.cityName = cityName;
        this.weatherDescription = weatherDescription;
        this.tempCelsius = tempCelsius;
        this.pressure = pressure;
        this.humidityPercents = humidityPercents;
        this.windSpeed = windSpeed;
        this.windDegree = windDegree;
        this.timestamp = timestamp;
    }

    public CityWeather(CurrentWeatherResponse serverResponse, long timestamp) {
        this.cityId = serverResponse.getId();
        this.cityName = serverResponse.getName();
        this.weatherDescription = serverResponse.getWeather().get(0).getDescription();
        this.tempCelsius = serverResponse.getMain().getTemp();
        this.pressure = serverResponse.getMain().getPressure();
        this.humidityPercents = serverResponse.getMain().getHumidity();
        this.windSpeed = serverResponse.getWind().getSpeed();
        this.windDegree = serverResponse.getWind().getDeg();
        this.timestamp = timestamp;
    }

    public int getCityId() {
        return cityId;
    }

    public String getCityName() {
        return cityName;
    }

    public String getWeatherDescription() {
        return weatherDescription;
    }

    public double getTempCelsius() {
        return tempCelsius;
    }

    public double getPressure() {
        return pressure;
    }

    public double getHumidityPercents() {
        return humidityPercents;
    }

    public double getWindSpeed() {
        return windSpeed;
    }

    public double getWindDegree() {
        return windDegree;
    }

    public long getTimestamp() {
        return timestamp;
    }

    @Override
    public String toString() {
        return "CityWeather{" +
                "cityId=" + cityId +
                ", cityName='" + cityName + '\'' +
                ", weatherDescription='" + weatherDescription + '\'' +
                ", tempCelsius=" + tempCelsius +
                ", pressure=" + pressure +
                ", humidityPercents=" + humidityPercents +
                ", windSpeed=" + windSpeed +
                ", windDegree=" + windDegree +
                ", timestamp=" + timestamp +
                '}';
    }
}
