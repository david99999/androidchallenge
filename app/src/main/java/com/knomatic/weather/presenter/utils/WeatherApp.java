package com.knomatic.weather.presenter.utils;

import android.app.Application;
import android.zetterstrom.com.forecast.ForecastClient;
import android.zetterstrom.com.forecast.ForecastConfiguration;

import static com.knomatic.weather.presenter.utils.Constants.*;

/**
 * Created by Hogar on 09/03/2016.
 */
public class WeatherApp extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        ForecastConfiguration configuration =
                new ForecastConfiguration.Builder(API_KEY)
                        .setCacheDirectory(getCacheDir())
                        .build();
        ForecastClient.create(configuration);
    }
}
