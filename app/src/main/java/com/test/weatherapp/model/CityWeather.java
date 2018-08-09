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
        this.weatherDescription = serverResponse.getWeather().get(0).getDescription() != null
                ? serverResponse.getWeather().get(0).getDescription()
                : "";
        this.tempCelsius = serverResponse.getMain().getTemp() != null
                ? serverResponse.getMain().getTemp()
                : 0;
        this.pressure = serverResponse.getMain().getPressure() != null
                ? serverResponse.getMain().getPressure()
                : 0;
        this.humidityPercents = serverResponse.getMain().getHumidity() != null
                ? serverResponse.getMain().getHumidity()
                : 0;
        this.windSpeed = serverResponse.getWind().getSpeed() != null
                ? serverResponse.getWind().getSpeed()
                : 0;
        this.windDegree = serverResponse.getWind().getDeg() != null
                ? serverResponse.getWind().getDeg()
                : 0;
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
                "\ncityId=" + cityId +
                "\n, cityName='" + cityName + '\'' +
                "\n, weatherDescription='" + weatherDescription + '\'' +
                "\n, tempCelsius=" + tempCelsius +
                "\n, pressure=" + pressure +
                "\n, humidityPercents=" + humidityPercents +
                "\n, windSpeed=" + windSpeed +
                "\n, windDegree=" + windDegree +
                "\n, timestamp=" + timestamp +
                "\n}";
    }
}
