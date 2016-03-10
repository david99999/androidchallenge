package com.knomatic.weather.presenter.implementations;

import android.zetterstrom.com.forecast.ForecastClient;
import android.zetterstrom.com.forecast.models.Forecast;

import com.knomatic.weather.presenter.interfaces.WeatherPresenter;
import com.knomatic.weather.presenter.interfaces.WeatherView;

import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by Hogar on 09/03/2016.
 */
public class WeatherPresenterImp implements WeatherPresenter {


    private final WeatherView view;

    public WeatherPresenterImp(WeatherView view) {
        this.view = view;
    }

    @Override
    public void loadForecast(double latitude, double longitude) {
        ForecastClient.getInstance()
                .getForecast(latitude, longitude, new Callback<Forecast>() {
                    @Override
                    public void onResponse(Response<Forecast> response) {
                        if (response.isSuccess() && view != null) {
                            view.setForecast(response.body());
                        }
                    }

                    @Override
                    public void onFailure(Throwable t) {
                        if (view != null) {
                            view.showError(t);
                        }
                    }
                });
    }
}
