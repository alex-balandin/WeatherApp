package com.test.weatherapp.ui.main;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.test.weatherapp.R;
import com.test.weatherapp.model.CityWeather;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by alex-balandin on 8/9/18
 */
public class CitiesWeatherAdapter extends RecyclerView.Adapter<CitiesWeatherAdapter.ViewHolder> {

    private List<CityWeather> citiesWeatherData = new ArrayList<>();

    private CityWeatherClickListener cityWeatherClickListener;

    public void setCitiesWeatherData(List<CityWeather> data) {
        citiesWeatherData.clear();
        citiesWeatherData.addAll(data);
        notifyDataSetChanged();
    }

    public void setCityWeatherClickListener(CityWeatherClickListener listener) {
        cityWeatherClickListener = listener;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View itemView = inflater.inflate(R.layout.v_city_weather_list_item, parent, false);
        return new ViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        CityWeather cityWeather = citiesWeatherData.get(position);
        holder.cityNameView.setText(cityWeather.getCityName());

        String temperatureText = String.valueOf(cityWeather.getTempCelsius()) + " \u2103";
        holder.temperatureView.setText(temperatureText);

        holder.itemView.setOnClickListener(v -> {
            if (cityWeatherClickListener != null) {
                cityWeatherClickListener.onCityWeatherClicked(cityWeather.getCityId());
            }
        });
        holder.deleteBtn.setOnClickListener(v -> {
            if (cityWeatherClickListener != null) {
                cityWeatherClickListener.onDeleteClicked(cityWeather.getCityId());
            }
        });

        Date date = new Date(cityWeather.getTimestamp());
        DateFormat dateFormat = new SimpleDateFormat("MM/dd/yyyy HH:mm:ss");
        String lastUpdateTime = dateFormat.format(date);
        String lastUpdateTimeText = holder.itemView.getContext()
                .getString(R.string.last_update_time, lastUpdateTime);
        holder.lastUpdateTimeText.setText(lastUpdateTimeText);
    }

    @Override
    public int getItemCount() {
        return citiesWeatherData.size();
    }

    public interface CityWeatherClickListener {
        void onCityWeatherClicked(int cityId);
        void onDeleteClicked(int cityId);
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        View itemView;
        TextView cityNameView;
        TextView temperatureView;
        View deleteBtn;
        TextView lastUpdateTimeText;

        public ViewHolder(View itemView) {
            super(itemView);
            this.itemView = itemView;
            cityNameView = itemView.findViewById(R.id.city_name);
            temperatureView = itemView.findViewById(R.id.temperature);
            deleteBtn = itemView.findViewById(R.id.delete_btn);
            lastUpdateTimeText = itemView.findViewById(R.id.last_update_time_text);
        }
    }
}
