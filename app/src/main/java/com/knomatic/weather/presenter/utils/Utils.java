package com.knomatic.weather.presenter.utils;

import android.content.Context;
import android.zetterstrom.com.forecast.models.DataPoint;

import com.knomatic.weather.R;

/**
 * Created by Hogar on 09/03/2016.
 */
public class Utils {

    static final String ICON_CLEAR_DAY = "clear-day";
    static final String ICON_RAIN = "rain";
    static final String ICON_FOG = "fog";
    static final String ICON_CLOUDY = "cloudy";
    static final String ICON_HAIL = "hail";
    public static int GetIconId(String icon){

        int resource = R.drawable.sun;
        switch (icon){
            case ICON_RAIN:
                return R.drawable.cloud_rain;
            case ICON_CLEAR_DAY:
                return R.drawable.sun;
            case ICON_FOG:
                return R.drawable.cloud_fog;
            case ICON_CLOUDY:
                return R.drawable.cloud;
            case ICON_HAIL:
                return R.drawable.cloud_hail;
        }
        return resource;
    }

    public static String getTemp(Context context, DataPoint dataPoint) {
        String temp = "";
        if (dataPoint.getApparentTemperature() != null)
            temp = dataPoint.getApparentTemperature().toString();

        else if (dataPoint.getTemperature() != null)
            temp = dataPoint.getTemperature().toString();

        else if (dataPoint.getApparentTemperatureMax() != null)
            temp = dataPoint.getApparentTemperatureMax().toString();

        else if (dataPoint.getApparentTemperatureMin() != null)
            temp = dataPoint.getApparentTemperatureMin().toString();
        return temp.isEmpty() ? "Unknown" : String.format(context.getString(R.string.temp), temp);
    }
}
