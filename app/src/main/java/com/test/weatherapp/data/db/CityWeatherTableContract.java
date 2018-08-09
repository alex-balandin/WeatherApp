package com.test.weatherapp.data.db;

/**
 * Created by alex-balandin on 8/9/18
 */
public class CityWeatherTableContract {
    public static final String TABLE_NAME = "cities_weather_table";

    public static class Columns {
        public static final String _ID = "_id";
        public static final String CITY_ID = "city_id"; //int
        public static final String CITY_NAME = "city_name"; //string
        public static final String WEATHER_DESCRIPTION = "weather_description"; //string
        public static final String TEMP_CELSIUS = "temp_celsius"; //double
        public static final String PRESSURE = "pressure"; //double
        public static final String HUMIDITY_PERCENTS = "humidity_percents"; //double
        public static final String WIND_SPEED = "wind_speed"; //double
        public static final String WIND_DEGREE = "wind_degree"; //double
        public static final String TIMESTAMP = "timestamp"; //long
    }
}
