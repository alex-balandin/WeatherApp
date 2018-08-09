package com.test.weatherapp.ui.main;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Toast;

import com.test.weatherapp.R;
import com.test.weatherapp.application.WeatherApplication;
import com.test.weatherapp.model.CityWeather;
import com.test.weatherapp.ui.addcity.AddCityActivity;
import com.test.weatherapp.ui.main.di.MainModule;

import java.util.List;

import javax.inject.Inject;

public class MainActivity extends AppCompatActivity implements MainContract.View {

    private static final int ADD_CITY_ACTIVITY_REQUEST_CODE = 12345;

    @Inject
    MainContract.Presenter presenter;

    private CitiesWeatherAdapter citiesWeatherAdapter;
    private SwipeRefreshLayout swipeRefreshLayout;
    private View progressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                presenter.onAddButtonClicked();
            }
        });

        progressBar = findViewById(R.id.progress_bar);

        initCitiesWeatherAdapter();

        swipeRefreshLayout = findViewById(R.id.swipe_refresh_layout);
        swipeRefreshLayout.setOnRefreshListener(() -> {
            presenter.onSwipeToRefresh();
        });

        WeatherApplication
                .getAppComponent()
                .plus(new MainModule(this))
                .inject(this);
        presenter.attach(this);
    }

    private void initCitiesWeatherAdapter() {
        RecyclerView recyclerView = findViewById(R.id.cities_weather_recycler);

        LinearLayoutManager layoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        recyclerView.setLayoutManager(layoutManager);

        citiesWeatherAdapter = new CitiesWeatherAdapter();
        recyclerView.setAdapter(citiesWeatherAdapter);

        citiesWeatherAdapter.setCityWeatherClickListener(new CitiesWeatherAdapter.CityWeatherClickListener() {
            @Override
            public void onCityWeatherClicked(int cityId) {
                presenter.onCityWeatherClicked(cityId);
            }

            @Override
            public void onDeleteClicked(int cityId) {
                presenter.onDeleteClicked(cityId);
            }
        });
    }

    @Override
    protected void onDestroy() {
        presenter.detach();
        super.onDestroy();
    }

    @Override
    public void setCitiesWeatherData(List<CityWeather> data) {
        citiesWeatherAdapter.setCitiesWeatherData(data);
    }

    @Override
    public void showAddCityScreen() {
        Intent intent = new Intent(this, AddCityActivity.class);
        startActivityForResult(intent, ADD_CITY_ACTIVITY_REQUEST_CODE);
    }

    @Override
    public void showWeatherDetailsScreen() {

    }

    @Override
    public void showProgressBar() {
        progressBar.setVisibility(View.VISIBLE);
    }

    @Override
    public void hideProgressBar() {
        progressBar.setVisibility(View.GONE);
    }

    @Override
    public void showNetworkErrorMessage() {
        String message = getString(R.string.error_message_no_internet_connection);
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void showGeneralErrorMessage() {
        String message = getString(R.string.error_message_general_error);
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void finishRefresh() {
        swipeRefreshLayout.setRefreshing(false);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == ADD_CITY_ACTIVITY_REQUEST_CODE && resultCode == Activity.RESULT_OK) {
            presenter.onAddCitySuccess();
        }
    }
}
