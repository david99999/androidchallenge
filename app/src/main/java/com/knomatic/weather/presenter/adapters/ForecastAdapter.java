package com.knomatic.weather.presenter.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.zetterstrom.com.forecast.models.DataPoint;

import com.knomatic.weather.R;
import com.knomatic.weather.presenter.interfaces.DetailForecastListener;
import com.knomatic.weather.presenter.interfaces.RecyclerItemListener;
import com.knomatic.weather.presenter.utils.Utils;
import com.knomatic.weather.view.holders.ForecastItem;
import com.knomatic.weather.view.holders.GenericHolder;

import java.sql.Date;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

/**
 * Created by Hogar on 09/03/2016.
 */
public class ForecastAdapter extends RecyclerView.Adapter<GenericHolder> implements RecyclerItemListener {

    private DetailForecastListener listener;
    ArrayList<DataPoint> dataPoints;

    public ForecastAdapter(ArrayList<DataPoint> dataPoints, DetailForecastListener listener) {
        this.dataPoints = dataPoints;
        this.listener = listener;
    }

    @Override
    public GenericHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        ForecastItem holder = (ForecastItem) LayoutInflater.from(parent.getContext()).inflate(R.layout.item_forecast, parent, false);
        GenericHolder viewHolder = new GenericHolder(holder);
        viewHolder.setListListener(this);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(GenericHolder holder, int position) {
        SimpleDateFormat sdf = new SimpleDateFormat("EEE d");
        String dayString = sdf.format(dataPoints.get(position).getTime());
        String temp = Utils.getTemp(holder.itemView.getContext(), dataPoints.get(position));
        ((ForecastItem) holder.itemView).setInfo(temp);
        ((ForecastItem) holder.itemView).setDay(dayString);
        ((ForecastItem) holder.itemView).setPic(Utils.GetIconId(dataPoints.get(position).getIcon().getText()));
    }


    @Override
    public int getItemCount() {
        return dataPoints.size();
    }

    @Override
    public void itemClicked(int adapterPosition) {
        if (listener != null) {
            listener.OnHourForecastListener(dataPoints.get(adapterPosition));
        }
    }
}
