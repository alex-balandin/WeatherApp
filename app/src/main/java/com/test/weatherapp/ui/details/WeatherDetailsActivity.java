package com.test.weatherapp.ui.details;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;
import android.widget.Toast;

import com.test.weatherapp.R;
import com.test.weatherapp.application.WeatherApplication;
import com.test.weatherapp.model.CityWeather;
import com.test.weatherapp.ui.details.di.WeatherDetailsModule;

import javax.inject.Inject;

/**
 * Created by alex-balandin on 8/10/18
 */
public class WeatherDetailsActivity extends AppCompatActivity implements WeatherDetailsContract.View {

    private static final String CITY_ID_INTENT_EXTRA = "city_id";

    @Inject
    WeatherDetailsContract.Presenter presenter;

    private TextView weatherDetailsView;

    public static Intent getStartIntent(Context context, int cityId) {
        Intent intent = new Intent(context, WeatherDetailsActivity.class);
        intent.putExtra(CITY_ID_INTENT_EXTRA, cityId);
        return intent;
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.a_weather_details);

        weatherDetailsView = findViewById(R.id.weather_details_text);

        WeatherApplication
                .getAppComponent()
                .plus(new WeatherDetailsModule(this))
                .inject(this);

        if (getIntent() != null) {
            int cityId = getIntent().getIntExtra(CITY_ID_INTENT_EXTRA, -1);
            presenter.setCityId(cityId);
        }

        presenter.attach(this);
    }

    @Override
    public void showWeatherData(CityWeather cityWeather) {
        weatherDetailsView.setText(cityWeather.toString());
    }

    @Override
    public void showGeneralErrorMessage() {
        String message = getString(R.string.error_message_general_error);
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
    }
}
