package com.knomatic.weather.view;

import android.Manifest;
import android.content.pm.PackageManager;
import android.location.Location;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import com.knomatic.weather.presenter.interfaces.LocationListener;

import fr.quentinklein.slt.LocationTracker;
import fr.quentinklein.slt.TrackerSettings;

/**
 * Created by Hogar on 09/03/2016.
 */
public class LocationActivity extends AppCompatActivity {

    private static final int OPEN_FOR_PERMISSION_CODE = 55;
    protected LocationListener listener;
    private String TAG = LocationActivity.class.getSimpleName();
    private LocationTracker tracker;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED
                && ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {

            ActivityCompat.requestPermissions(this,
                    new String[]{Manifest.permission.ACCESS_COARSE_LOCATION, Manifest.permission.ACCESS_FINE_LOCATION},
                    OPEN_FOR_PERMISSION_CODE);
        } else {
            initiateLocationListening();
        }
    }

    private void initiateLocationListening() {
        try {
            tracker = new LocationTracker(this,
                    new TrackerSettings()
                            .setUseGPS(true)
                            .setUseNetwork(true)
                            .setUsePassive(true)) {
                @Override
                public void onLocationFound(Location location) {
                    if (listener != null) {
                        listener.OnLocationFound(location.getLatitude(), location.getLongitude());
                    }
                    stopLocation();
                }

                @Override
                public void onTimeout() {

                }
            };
            tracker.startListening();
        } catch (SecurityException se) {
            Log.e(TAG, se.getMessage());
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == OPEN_FOR_PERMISSION_CODE) {
            if (grantResults.length > 0) {
                int grantedCount = 0;
                for (int possible : grantResults) {
                    if (possible == PackageManager.PERMISSION_GRANTED) {
                        grantedCount++;
                    }
                }
                if (grantedCount == grantResults.length) {
                    initiateLocationListening();
                }
            }
        }
    }

    private void stopLocation() {
        try {
            if (tracker != null) tracker.stopListen();
        } catch (Exception e) {
            Log.e(TAG, e.getMessage());
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        stopLocation();
    }
}
