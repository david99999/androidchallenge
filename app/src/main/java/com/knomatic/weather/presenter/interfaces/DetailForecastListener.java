package com.knomatic.weather.presenter.interfaces;

import android.zetterstrom.com.forecast.models.DataBlock;
import android.zetterstrom.com.forecast.models.DataPoint;
import android.zetterstrom.com.forecast.models.Forecast;

/**
 * Created by Hogar on 09/03/2016.
 */
public interface DetailForecastListener {
    void OnHourForecastListener(DataPoint hour);
}
