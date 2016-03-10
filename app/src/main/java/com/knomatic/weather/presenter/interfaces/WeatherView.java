package com.knomatic.weather.presenter.interfaces;


/**
 * Created by Hogar on 09/03/2016.
 */
public interface WeatherView {

    void setForecast(Object data);

    void showError(Throwable error);
}
