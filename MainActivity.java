package com.example.user.pondfi;

import android.Manifest;
import android.content.pm.PackageManager;
import android.location.Location;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.tasks.OnSuccessListener;

public class MainActivity extends AppCompatActivity {
    Location mLastLocation;
    TextView mLocationTextView= (TextView)findViewById(R.id.t1);
    String TAG;
    Integer REQUEST_LOCATION_PERMISSION;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        @Override
        Button b1= (Button)findViewById(R.id.b1);
        b1.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                private void getLocation() {
                    if (ActivityCompat.checkSelfPermission(this,
                            Manifest.permission.ACCESS_FINE_LOCATION)
                            != PackageManager.PERMISSION_GRANTED) {
                        ActivityCompat.requestPermissions(this, new String[]
                                        {Manifest.permission.ACCESS_FINE_LOCATION},
                                REQUEST_LOCATION_PERMISSION);
                    } else {
                        Log.d(TAG, "getLocation: permissions granted");
                    }
                }
                // Code here executes on main thread after user presses button
            }
        });
        @Override
        public void onRequestPermissionsResult(int requestCode,
        @NonNull String[] permissions, @NonNull int[] grantResults) {
            switch (requestCode) {
                case REQUEST_LOCATION_PERMISSION:
                    // If the permission is granted, get the location,
                    // otherwise, show a Toast
                    if (grantResults.length > 0
                            && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                        getLocation();
                    } else {
                        Toast.makeText(this,"location_permission_denied",Toast.LENGTH_SHORT).show();
                    }
                    break;
            }
        }

        mFusedLocationClient.getLastLocation().addOnSuccessListener(this,new OnSuccessListener<Location>() {

            public void onSuccess(Location location) {
                if (location != null) {
                    mLastLocation = location;
                    mLocationTextView.setText(
                            getString(R.string.location_text,
                                    mLastLocation.getLatitude(),
                                    mLastLocation.getLongitude(),
                                    mLastLocation.getTime()));
                } else {
                    mLocationTextView.setText(R.string.no_location);
                }
            }
        });

    }
}
