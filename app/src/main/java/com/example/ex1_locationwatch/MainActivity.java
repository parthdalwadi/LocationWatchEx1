package com.example.ex1_locationwatch;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Context;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import java.io.IOException;
import java.util.List;
import java.util.Locale;

public class MainActivity extends AppCompatActivity {


    private static final String TAG = "PARTH";
    LocationManager locationManager;
    LocationListener locationListener;

    TextView lat, lon, add, acc, alt;

    @SuppressLint("MissingPermission")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        lat = findViewById(R.id.LAT);
        lon = findViewById(R.id.LONG);
        alt = findViewById(R.id.ALT);
        acc = findViewById(R.id.ACC);
        add = findViewById(R.id.ADD);


        final Geocoder geocoder = new Geocoder(this, Locale.getDefault());

        locationManager = (LocationManager) this.getSystemService(Context.LOCATION_SERVICE);
        locationListener = new LocationListener() {
            @Override
            public void onLocationChanged(Location l) {

                Log.i(TAG, "onLocationChanged: " + l);


                try {
                    List<Address> addresses = geocoder.getFromLocation(
                            l.getLatitude(),
                            l.getLongitude(),

                            1);
                    Address a = addresses.get(0);

                    lat.setText("Latitude:\n" + String.valueOf(l.getLatitude()));
                    lon.setText("Longitude:\n" + String.valueOf(l.getLongitude()));
                    alt.setText("Altitude:\n" + String.valueOf(l.getAltitude()));
                    acc.setText("Accuracy:\n" + String.valueOf(l.getAccuracy()));
                    add.setText("Address:\n" + String.valueOf(a.getAddressLine(0)));

                } catch (IOException e) {
                    e.printStackTrace();
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
        };


        locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 5000, 10, locationListener);
        //Location l = locationManager.getLastKnownLocation(locationManager.GPS_PROVIDER);





    }
}
