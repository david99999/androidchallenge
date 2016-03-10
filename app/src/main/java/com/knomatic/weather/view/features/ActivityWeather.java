package com.knomatic.weather.view.features;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.FrameLayout;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;
import android.zetterstrom.com.forecast.models.DataPoint;
import android.zetterstrom.com.forecast.models.Forecast;

import com.knomatic.weather.R;
import com.knomatic.weather.presenter.implementations.WeatherPresenterImp;
import com.knomatic.weather.presenter.interfaces.DetailForecastListener;
import com.knomatic.weather.presenter.interfaces.LocationListener;
import com.knomatic.weather.presenter.interfaces.WeatherView;
import com.knomatic.weather.view.LocationActivity;

import butterknife.Bind;
import butterknife.ButterKnife;

public class ActivityWeather extends LocationActivity implements WeatherView, DetailForecastListener {

    WeatherPresenterImp interactor;

    @Bind(R.id.tvInformation)
    TextView tvInfo;

    @Bind(R.id.pbLoading)
    ProgressBar pbLoading;

    @Bind(R.id.toolbar)
    Toolbar tToolbar;

    @Bind(R.id.flContainer)
    FrameLayout flContainer;
    private boolean displayed = false;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_weather);
        ButterKnife.bind(this);
        setSupportActionBar(tToolbar);
        setTitle(getString(R.string.title));
        interactor = new WeatherPresenterImp(this);
        listener = new LocationListener() {
            @Override
            public void OnLocationFound(double latitude, double longitude) {
                if (!displayed) {
                    displayed = true;
                    tvInfo.setText(R.string.obtaining_weather);
                    interactor.loadForecast(latitude, longitude);
                }
            }
        };
    }

    @Override
    public void setForecast(Object data) {
        pbLoading.setVisibility(View.GONE);
        tvInfo.setVisibility(View.GONE);
        flContainer.setVisibility(View.VISIBLE);
        getSupportFragmentManager()
                .beginTransaction()
                .add(R.id.flContainer, FragmentCurrentWeather.newInstance((Forecast) data), FragmentCurrentWeather.TAG)
                .commit();
    }

    @Override
    public void showError(Throwable error) {
        Toast.makeText(this, error.getMessage(), Toast.LENGTH_SHORT).show();
    }

    @Override
    public void OnHourForecastListener(DataPoint hour) {
        Toast.makeText(this, hour.getSummary(), Toast.LENGTH_SHORT).show();
    }
}
