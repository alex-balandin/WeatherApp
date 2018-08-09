package com.test.weatherapp.data.db;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.test.weatherapp.model.CityWeather;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by alex-balandin on 8/9/18
 */
public class DbManagerImpl implements DbManager {

    private DbHelper dbHelper;

    public DbManagerImpl(Context context) {
        dbHelper = new DbHelper(context);
    }

    @Override
    public void saveCityWeather(CityWeather cityWeather) {
        SQLiteDatabase db = dbHelper.getWritableDatabase();

        db.beginTransaction();
        try {
            ContentValues cv = new ContentValues();
            cv.put(CityWeatherTableContract.Columns.CITY_ID, cityWeather.getCityId());
            cv.put(CityWeatherTableContract.Columns.CITY_NAME, cityWeather.getCityName());
            cv.put(CityWeatherTableContract.Columns.WEATHER_DESCRIPTION, cityWeather.getWeatherDescription());
            cv.put(CityWeatherTableContract.Columns.TEMP_CELSIUS, cityWeather.getTempCelsius());
            cv.put(CityWeatherTableContract.Columns.PRESSURE, cityWeather.getPressure());
            cv.put(CityWeatherTableContract.Columns.HUMIDITY_PERCENTS, cityWeather.getHumidityPercents());
            cv.put(CityWeatherTableContract.Columns.WIND_SPEED, cityWeather.getWindSpeed());
            cv.put(CityWeatherTableContract.Columns.WIND_DEGREE, cityWeather.getWindDegree());
            cv.put(CityWeatherTableContract.Columns.TIMESTAMP, cityWeather.getTimestamp());

            db.insertWithOnConflict(CityWeatherTableContract.TABLE_NAME, null, cv,
                    SQLiteDatabase.CONFLICT_REPLACE);

            db.setTransactionSuccessful();
        } finally {
            db.endTransaction();
        }
    }

    @Override
    public CityWeather getCityWeather(int cityId) {
        String whereClause = CityWeatherTableContract.Columns.CITY_ID + " == ?";
        String[] whereArgs = new String[]{String.valueOf(cityId)};

        Cursor cursor = dbHelper.getReadableDatabase().query(
                CityWeatherTableContract.TABLE_NAME, null, whereClause, whereArgs, null, null, null);

        CityWeather cityWeather = null;

        try {
            if (cursor != null && cursor.moveToFirst()) {
                String cityName = cursor.getString(cursor.getColumnIndex(CityWeatherTableContract.Columns.CITY_NAME));
                String weatherDescription = cursor.getString(cursor.getColumnIndex(CityWeatherTableContract.Columns.WEATHER_DESCRIPTION));
                double tempCelsius = cursor.getDouble(cursor.getColumnIndex(CityWeatherTableContract.Columns.TEMP_CELSIUS));
                double pressure = cursor.getDouble(cursor.getColumnIndex(CityWeatherTableContract.Columns.PRESSURE));
                double humidityPercents = cursor.getDouble(cursor.getColumnIndex(CityWeatherTableContract.Columns.HUMIDITY_PERCENTS));
                double windSpeed = cursor.getDouble(cursor.getColumnIndex(CityWeatherTableContract.Columns.WIND_SPEED));
                double windDegree = cursor.getDouble(cursor.getColumnIndex(CityWeatherTableContract.Columns.WIND_DEGREE));
                long timestamp = cursor.getLong(cursor.getColumnIndex(CityWeatherTableContract.Columns.TIMESTAMP));

                cityWeather = new CityWeather(cityId, cityName, weatherDescription, tempCelsius,
                        pressure, humidityPercents, windSpeed, windDegree, timestamp);
            }
        } finally {
            if (cursor != null) {
                cursor.close();
            }
        }
        return cityWeather;
    }

    @Override
    public List<CityWeather> getAllCitiesWeather() {
        Cursor cursor = dbHelper.getReadableDatabase().query(
                CityWeatherTableContract.TABLE_NAME, null, null, null, null, null, null);

        List<CityWeather> citiesWeather = new ArrayList<>();
        try {
            if (cursor != null && cursor.moveToFirst()) {
                do {
                    int cityId = cursor.getInt(cursor.getColumnIndex(CityWeatherTableContract.Columns.CITY_ID));
                    String cityName = cursor.getString(cursor.getColumnIndex(CityWeatherTableContract.Columns.CITY_NAME));
                    String weatherDescription = cursor.getString(cursor.getColumnIndex(CityWeatherTableContract.Columns.WEATHER_DESCRIPTION));
                    double tempCelsius = cursor.getDouble(cursor.getColumnIndex(CityWeatherTableContract.Columns.TEMP_CELSIUS));
                    double pressure = cursor.getDouble(cursor.getColumnIndex(CityWeatherTableContract.Columns.PRESSURE));
                    double humidityPercents = cursor.getDouble(cursor.getColumnIndex(CityWeatherTableContract.Columns.HUMIDITY_PERCENTS));
                    double windSpeed = cursor.getDouble(cursor.getColumnIndex(CityWeatherTableContract.Columns.WIND_SPEED));
                    double windDegree = cursor.getDouble(cursor.getColumnIndex(CityWeatherTableContract.Columns.WIND_DEGREE));
                    long timestamp = cursor.getLong(cursor.getColumnIndex(CityWeatherTableContract.Columns.TIMESTAMP));

                    citiesWeather.add(new CityWeather(cityId, cityName, weatherDescription, tempCelsius,
                            pressure, humidityPercents, windSpeed, windDegree, timestamp));
                } while (cursor.moveToNext());
            }

        } finally {
            if (cursor != null) {
                cursor.close();
            }
        }
        return citiesWeather;
    }

    @Override
    public void deleteCityWeather(int cityId) {
        String whereClause = CityWeatherTableContract.Columns.CITY_ID + " == ?";
        String[] whereArgs = new String[]{String.valueOf(cityId)};

        SQLiteDatabase db = dbHelper.getWritableDatabase();
        db.beginTransaction();
        try {
            db.delete(CityWeatherTableContract.TABLE_NAME, whereClause, whereArgs);
            db.setTransactionSuccessful();
        } finally {
            db.endTransaction();
        }
    }
}
