package com.knomatic.weather.view.features;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.zetterstrom.com.forecast.models.DataPoint;
import android.zetterstrom.com.forecast.models.Forecast;

import com.knomatic.weather.R;
import com.knomatic.weather.presenter.adapters.ForecastAdapter;
import com.knomatic.weather.presenter.interfaces.DetailForecastListener;
import com.knomatic.weather.presenter.utils.Utils;

import java.text.SimpleDateFormat;
import java.util.ArrayList;

import butterknife.Bind;
import butterknife.ButterKnife;

public class FragmentCurrentWeather extends Fragment {

    private static final String FORECAST = "FORECAST";
    public static final String TAG = FragmentCurrentWeather.class.getSimpleName();
    private DetailForecastListener clickListener;

    public FragmentCurrentWeather() {
    }

    public static FragmentCurrentWeather newInstance(Forecast forecast) {
        FragmentCurrentWeather fragment = new FragmentCurrentWeather();
        Bundle args = new Bundle();
        args.putSerializable(FORECAST, forecast);
        fragment.setArguments(args);
        return fragment;
    }

    Forecast forecast;
    View view;
    @Bind(R.id.tvCurrentWeather)
    TextView tvCurrentWeather;


    @Bind(R.id.tvCurrentDate)
    TextView tvCurrentDate;

    @Bind(R.id.tvWindSpeed)
    TextView tvWindSpeed;

    @Bind(R.id.tvHumidity)
    TextView tvHumidity;

    @Bind(R.id.rvForecast)
    RecyclerView rvForecast;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            forecast = (Forecast) getArguments().getSerializable(FORECAST);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_current_weather, container, false);
        ButterKnife.bind(this, view);
        try {
            tvCurrentWeather.setText(Utils.getTemp(getActivity(), forecast.getCurrently()));
            rvForecast.setLayoutManager(new LinearLayoutManager(getActivity(), LinearLayoutManager.HORIZONTAL, false));
            rvForecast.setAdapter(new ForecastAdapter(forecast.getDaily().getDataPoints(), (DetailForecastListener) getActivity()));
            tvCurrentDate.setText(new SimpleDateFormat("EEEE, MMMM d ").format(forecast.getCurrently().getTime()));
            tvHumidity.setText(String.valueOf(forecast.getCurrently().getHumidity()));
            tvWindSpeed.setText(String.format(getString(R.string.mps), String.valueOf(forecast.getCurrently().getWindSpeed())));
        } catch (Exception e) {
            Log.e(TAG, e.getMessage());
        }
        return view;
    }

    public void onHourClick(int position) {
        try {
            if (clickListener != null) {
                clickListener.OnHourForecastListener(forecast.getDaily().getDataPoints().get(position));
            }
        } catch (Exception e) {
            Log.e(TAG, e.getMessage());
        }
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        clickListener = (DetailForecastListener) context;
    }

    @Override
    public void onDetach() {
        super.onDetach();
        clickListener = null;
    }
}
