package com.example.gps_location;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import android.Manifest;
import android.content.Context;
import android.content.pm.PackageManager;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.tasks.OnSuccessListener;

import java.io.IOException;
import java.util.List;
import java.util.Locale;

import static android.Manifest.permission.ACCESS_FINE_LOCATION;

public class MainActivity extends AppCompatActivity {

    private FusedLocationProviderClient client;

    TextView tvLocation, tvCity;
    Button btnGps;
    private double longitude, latitude;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        requestPermission();

        client = LocationServices.getFusedLocationProviderClient(this);

        tvLocation = findViewById(R.id.textLocation);
        tvCity = findViewById(R.id.textCity);
        btnGps = findViewById(R.id.buttonLocation);

        btnGps.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (ActivityCompat.checkSelfPermission(MainActivity.this, ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
                    return;
                }

                client.getLastLocation().addOnSuccessListener(MainActivity.this, new OnSuccessListener<Location>() {
                    @Override
                    public void onSuccess(Location location) {
                        if(location != null){

                            longitude = location.getLongitude();
                            latitude = location.getLatitude();
                            tvLocation.setText("Longitude:" + longitude +"\nLatitude:" + latitude);

                            Geocoder myGeo = new Geocoder(MainActivity.this, Locale.getDefault());
                            List<Address> myAddress = null;
                            try {
                                myAddress = myGeo.getFromLocation(latitude, longitude, 1);
                            } catch (IOException e) {
                                e.printStackTrace();
                            }

                            if (myAddress.size() > 0){
                                tvCity.setText(myAddress.get(0).getLocality() + "\n"
                                + myAddress.get(0).getCountryName());
                            } else {
                                return;
                            }
                        }

                    }
                });
            }
        });
    }

    private void requestPermission(){
        ActivityCompat.requestPermissions(this, new String[]{ACCESS_FINE_LOCATION}, 1);
    }


}
