package com.knomatic.weather.view.holders;

import android.content.Context;
import android.support.v7.widget.CardView;
import android.util.AttributeSet;
import android.widget.ImageView;
import android.widget.TextView;

import com.knomatic.weather.R;


/**
 * Created by Hogar on 09/03/2016.
 */
public class ForecastItem extends CardView {
    private TextView tvDesc, tvDay;
    private ImageView ivPic;

    public ForecastItem(Context context) {
        super(context);
    }

    public ForecastItem(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public ForecastItem(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected void onFinishInflate() {
        super.onFinishInflate();
        tvDesc = (TextView) findViewById(R.id.tvStatus);
        tvDay = (TextView) findViewById(R.id.tvForecastDay);
        ivPic = (ImageView) findViewById(R.id.ivStatus);
    }

    public void setInfo(String summary) {
        tvDesc.setText(summary);
    }

    public void setDay(String summary) {
        tvDay.setText(summary);
    }

    public void setPic(int pic) {
        ivPic.setImageResource(pic);
    }
}
