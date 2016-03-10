package com.knomatic.weather.view.holders;

import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.knomatic.weather.presenter.interfaces.RecyclerItemListener;

/**
 * Created by Hogar on 09/03/2016.
 */
public class GenericHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

    public void setListListener(RecyclerItemListener listListener) {
        this.listListener = listListener;
    }

    RecyclerItemListener listListener;

    public GenericHolder(View itemView) {
        super(itemView);
        itemView.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        if (listListener != null) {
            listListener.itemClicked(getAdapterPosition());
        }
    }
}
