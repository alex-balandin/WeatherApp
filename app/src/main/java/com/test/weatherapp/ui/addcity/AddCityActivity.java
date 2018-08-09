package com.test.weatherapp.ui.addcity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.test.weatherapp.R;
import com.test.weatherapp.application.WeatherApplication;
import com.test.weatherapp.ui.addcity.di.AddCityModule;

import javax.inject.Inject;

/**
 * Created by alex-balandin on 8/9/18
 */
public class AddCityActivity extends AppCompatActivity implements AddCityContract.View {

    @Inject
    AddCityContract.Presenter presenter;

    private EditText cityNameEditText;
    private View progressBar;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.a_add_city);

        progressBar = findViewById(R.id.progress_bar);

        cityNameEditText = findViewById(R.id.city_name_edit_text);

        View addButton = findViewById(R.id.add_button);
        addButton.setOnClickListener(v -> {
            presenter.onAddCityClicked(cityNameEditText.getText().toString());
        });

        WeatherApplication
                .getAppComponent()
                .plus(new AddCityModule(this))
                .inject(this);
        presenter.attach(this);
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
    public void showCityNotFoundErrorMessage() {
        String message = getString(R.string.error_message_city_not_found);
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void showGeneralErrorMessage() {
        String message = getString(R.string.error_message_general_error);
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void finishWithResultOk() {
        setResult(RESULT_OK);
        finish();
    }
}
