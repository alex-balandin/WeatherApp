package com.test.weatherapp.data.db;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by alex-balandin on 8/7/18
 */
public class DbHelper extends SQLiteOpenHelper {
    private static final int DB_VERSION = 1;
    private static final String DB_NAME = "weatherapp.db";

    private static final String CREATE_CITY_WEATHER_TABLE_SQL =
            "CREATE TABLE IF NOT EXISTS " + CityWeatherTableContract.TABLE_NAME + " (" +
                    CityWeatherTableContract.Columns._ID + " INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, " +
                    CityWeatherTableContract.Columns.CITY_ID + " INTEGER DEFAULT 0 UNIQUE, " +
                    CityWeatherTableContract.Columns.CITY_NAME + " TEXT, " +
                    CityWeatherTableContract.Columns.WEATHER_DESCRIPTION + " TEXT, " +
                    CityWeatherTableContract.Columns.TEMP_CELSIUS + " REAL, " +
                    CityWeatherTableContract.Columns.PRESSURE + " REAL, " +
                    CityWeatherTableContract.Columns.HUMIDITY_PERCENTS + " REAL, " +
                    CityWeatherTableContract.Columns.WIND_SPEED + " REAL, " +
                    CityWeatherTableContract.Columns.WIND_DEGREE + " REAL, " +
                    CityWeatherTableContract.Columns.TIMESTAMP + " INTEGER " +
                    ");";

    public DbHelper(Context context) {
        super(context, DB_NAME, null, DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_CITY_WEATHER_TABLE_SQL);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
