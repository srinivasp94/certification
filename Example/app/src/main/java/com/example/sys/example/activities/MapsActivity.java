package com.example.sys.example.activities;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.util.Log;

import com.example.sys.example.R;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.maps.model.Polygon;
import com.google.android.gms.maps.model.PolygonOptions;
import com.google.android.gms.maps.model.PolylineOptions;

import java.util.ArrayList;

public class MapsActivity extends FragmentActivity implements OnMapReadyCallback {

    private GoogleMap googleMap;
    private Intent intent;
    private String path;
    Polygon polygon;
    PolylineOptions options;
//    Polyline line;

    ArrayList<LatLng> latLang = new ArrayList<LatLng>();
    private ArrayList<LatLng> latLngArray = new ArrayList<>();

    private static final LatLng LOWER_MANHATTAN = new LatLng(40.722543,
            -73.998585);
    private static final LatLng BROOKLYN_BRIDGE = new LatLng(40.7057, -73.9964);
    private static final LatLng WALL_STREET = new LatLng(40.7064, -74.0094);

    final String TAG = "PathGoogleMapActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps2);
        intent = getIntent();
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
        if (intent != null) {
            path = intent.getStringExtra("Boundries");
            String[] seperated = path.replace("(", "").split("\\)");
            Log.i("seperated", seperated.toString());
            for (int i = 0; i < seperated.length; i++) {
                String latandLong = seperated[i];
                try {

                    String[] mLocations = latandLong.split(",");
                    double lat = Double.parseDouble(mLocations[0]);
                    double longti = Double.parseDouble(mLocations[1]);
                    LatLng latLngM = new LatLng(lat, longti);
                    latLngArray.add(latLngM);


                    Log.d("latandlong", lat + "!@#" + longti);
                } catch (NumberFormatException e) {
                    e.printStackTrace();
                    Log.e("latandlongerror", e.toString());
                }

//                latLang = latLngArray;

                Log.i("seperated", seperated[i].toString());
            }

        }

       /* MarkerOptions options = new MarkerOptions();
        options.position(LOWER_MANHATTAN);
        options.position(BROOKLYN_BRIDGE);
        options.position(WALL_STREET);
        googleMap.addMarker(options);

        googleMap.moveCamera(CameraUpdateFactory.newLatLngZoom(BROOKLYN_BRIDGE,
                13));
        addMarkers();*/
    }

    public void Draw_Map() {
       /* PolygonOptions rectOptions = new PolygonOptions();
        rectOptions.addAll(latLngArray);
        rectOptions.strokeColor(Color.BLUE);
        rectOptions.fillColor(Color.CYAN);
        rectOptions.strokeWidth(7);
        polygon = googleMap.addPolygon(rectOptions);*/

        options = new PolylineOptions().width(5).color(Color.BLUE).geodesic(true);
        for (int z = 0; z < latLngArray.size(); z++) {
            LatLng point = latLngArray.get(z);
            options.add(point);
        }
        googleMap.addPolyline(options);
    }


    /**
     * Manipulates the map once available.
     * This callback is triggered when the map is ready to be used.
     * This is where we can add markers or lines, add listeners or move the camera. In this case,
     * we just add a marker near Sydney, Australia.
     * If Google Play services is not installed on the device, the user will be prompted to install
     * it inside the SupportMapFragment. This method will only be triggered once the user has
     * installed Google Play services and returned to the app.
     */
    @Override
    public void onMapReady(GoogleMap googleMap) {
        googleMap = googleMap;

        // Add a marker in Sydney and move the camera
       /* LatLng sydney = new LatLng(-34, 151);
        googleMap.addMarker(new MarkerOptions().position(sydney).title("Marker in Sydney"));*/
        googleMap.moveCamera(CameraUpdateFactory.newLatLng(latLngArray.get(0)));
//        Draw_Map();
        googleMap.getUiSettings().setZoomControlsEnabled(true);
        /*googleMap.getUiSettings().setRotateGesturesEnabled(false);
        googleMap.getUiSettings().setScrollGesturesEnabled(false);
        googleMap.getUiSettings().setTiltGesturesEnabled(false);*/

        MarkerOptions markerOptions = new MarkerOptions();

        markerOptions.position(latLngArray.get(0));

        // Setting titile of the infowindow of the marker
        markerOptions.title("Position");

        // Setting the content of the infowindow of the marker
        markerOptions.snippet("Latitude:" + latLngArray.get(0).latitude + "," + "Longitude:" + latLngArray.get(0).longitude);

//        options = new PolylineOptions().width(5).color(Color.RED).geodesic(true);

        PolygonOptions polygonOptions = new PolygonOptions();
        polygonOptions.addAll(latLngArray);
        polygonOptions.strokeColor(Color.BLUE);
        polygonOptions.strokeWidth(7);
        polygonOptions.fillColor(Color.CYAN);

        PolygonOptions polygonOptions1 = new PolygonOptions();
        polygonOptions1.addAll(latLngArray);
        polygonOptions1.strokeColor(Color.RED);
        polygonOptions1.strokeWidth(2);
        polygonOptions1.fillColor(Color.DKGRAY);

        Polygon polygon = googleMap.addPolygon(polygonOptions);

        Polygon polygon1 = googleMap.addPolygon(polygonOptions1);


       /* for (int z = 0; z < latLngArray.size(); z++) {
            LatLng point = latLngArray.get(z);
            options.add(point);
        }*/
//        googleMap.addPolyline(options);
        googleMap.addMarker(markerOptions);
    }
}
