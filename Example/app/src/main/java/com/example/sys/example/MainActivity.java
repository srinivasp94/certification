package com.example.sys.example;

import android.Manifest;
import android.content.pm.PackageManager;
import android.location.Location;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import com.example.sys.example.retrofitnetwork.APIInterface;
import com.example.sys.example.retrofitnetwork.RetrofitRequester;
import com.example.sys.example.retrofitnetwork.RetrofitResponseListener;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.location.LocationListener;
import com.google.android.gms.location.LocationRequest;
import com.google.android.gms.location.LocationServices;

public class MainActivity extends AppCompatActivity implements GoogleApiClient.ConnectionCallbacks,
        GoogleApiClient.OnConnectionFailedListener,
        LocationListener, RetrofitResponseListener {

    public static final long UPDATE_INTERVAL_IN_MILLISECONDS = 10000;


    public static final long FASTEST_UPDATE_INTERVAL_IN_MILLISECONDS =
            UPDATE_INTERVAL_IN_MILLISECONDS / 2;


    private final static String REQUESTING_LOCATION_UPDATES_KEY = "requesting-location-updates-key";
    private final static String LOCATION_KEY = "location-key";
    private final static String LAST_UPDATED_TIME_STRING_KEY = "last-updated-time-string-key";

    private static final int PERMISSIONS_REQUEST_ACCESS_FINE_LOCATION = 1;
    private static final int REQUEST_CHECK_SETTINGS = 10;
    private GoogleApiClient mGoogleApiClient;
    private LocationRequest mLocationRequest;
    private Location mCurrentLocation;
    private TextView tv_location;
    private APIInterface apiInterface;
    private Object obj;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        tv_location = (TextView) findViewById(R.id.tv_location);
        buildGoogleApiClient();
    }

    protected synchronized void buildGoogleApiClient() {
        Log.i("GoogleApiClient", "Building GoogleApiClient");
        mGoogleApiClient = new GoogleApiClient.Builder(this)
                .addConnectionCallbacks(this)
                .addOnConnectionFailedListener(this)
                .addApi(LocationServices.API)
                .build();
        createLocationRequest();
        Example example=new Example();
        example.name="Anil";
        example.emailId="anil@gmail";
        example.cpassword="Anil";
        example.password="Anil";
        example.deviceToken="Anilsdfsdff";
        example.deviceType="1";
        example.mobile="9695082472";


        try {
            obj=Class.forName(Example.class.getName()).cast(example);
            Log.d("obj",obj.toString());
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        new RetrofitRequester(this).callPostServices(obj,1,"/user/userregister_service",true);

    }

    protected void createLocationRequest() {
        mLocationRequest = new LocationRequest();
        mLocationRequest.setInterval(UPDATE_INTERVAL_IN_MILLISECONDS);
        mLocationRequest.setFastestInterval(FASTEST_UPDATE_INTERVAL_IN_MILLISECONDS);
        mLocationRequest.setPriority(LocationRequest.PRIORITY_HIGH_ACCURACY);
    }


    @Override
    public void onConnected(@Nullable Bundle bundle) {
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {

            return;
        }
        LocationServices.FusedLocationApi.requestLocationUpdates(mGoogleApiClient, mLocationRequest, MainActivity.this);
    }

    @Override
    public void onConnectionSuspended(int i) {

    }

    @Override
    public void onConnectionFailed(@NonNull ConnectionResult connectionResult) {

    }

    @Override
    public void onLocationChanged(Location location) {
        if (location!=null){
            tv_location.setText(location.toString());
        }


    }

    @Override
    public void onPointerCaptureChanged(boolean hasCapture) {

    }

    @Override
    protected void onStart() {
        super.onStart();
        mGoogleApiClient.connect();
    }

    @Override
    public void onResponseSuccess(Object objectResponse, Object objectRequest, int requestId) {

    }
}
