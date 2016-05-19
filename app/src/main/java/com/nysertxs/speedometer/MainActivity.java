package com.nysertxs.speedometer;

import android.content.Context;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;

import pl.pawelkleczkowski.customgauge.CustomGauge;

public class MainActivity extends AppCompatActivity implements LocationListener{

    private TextView speedTextView;
    private TextView locationTextView;
    private CustomGauge gauge;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        locationTextView = (TextView)findViewById(R.id.locationTextView);
        speedTextView = (TextView)findViewById(R.id.speedTextView);
        gauge = (CustomGauge)findViewById(R.id.gauge3);

        LocationManager lm = (LocationManager)getSystemService(Context.LOCATION_SERVICE);
        lm.requestLocationUpdates(LocationManager.GPS_PROVIDER,1,1,this);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onLocationChanged(Location location) {
        if(location!=null) {
            if(location.hasSpeed()){

                int msSpeed = Math.round(location.getSpeed());
                int kmhSpeed = Math.round((msSpeed * 3600) / 1000);

                String locationString = "location: " + location.getLatitude() + "," + location.getLongitude();
                String kmhSpeedString = kmhSpeed + " km/h";
                String msSpeedString = msSpeed+ " m/s";
                speedTextView.setText(kmhSpeedString);
                locationTextView.setText(locationString);
                gauge.setValue(kmhSpeed);
            }
        }
    }

    @Override
    public void onStatusChanged(String provider, int status, Bundle extras) {

    }

    @Override
    public void onProviderEnabled(String provider) {

    }

    @Override
    public void onProviderDisabled(String provider) {

    }
}
