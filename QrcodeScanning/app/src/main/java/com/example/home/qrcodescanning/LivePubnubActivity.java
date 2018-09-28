package com.example.home.qrcodescanning;

import android.app.Activity;
import android.support.v4.app.FragmentActivity;
import android.os.Bundle;
import android.util.Log;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;

import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.common.collect.ImmutableMap;
import com.pubnub.api.PNConfiguration;
import com.pubnub.api.PubNub;
import com.pubnub.api.callbacks.PNCallback;
import com.pubnub.api.models.consumer.PNPublishResult;
import com.pubnub.api.models.consumer.PNStatus;


import java.util.Arrays;
import java.util.Map;
import java.util.Random;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;


public class LivePubnubActivity extends FragmentActivity implements OnMapReadyCallback {

    private GoogleMap mMap;
    private static final String TAG = LivePubnubActivity.class.getName();
    private static final Double generatorOriginLat = 17.8199;
    private static final Double generatorOriginLng = 78.4783;
    private PubNub pubNub;
    private String userName = "srinivas";
    private ScheduledExecutorService executorService;
    private Random random = new Random();
    private Long startTime;

    private static ImmutableMap<String, String> getNewLocationMessage(String userName, int randomLat, int randomLng, long elapsedTime) {
        String newLat = Double.toString(generatorOriginLat + ((randomLat + elapsedTime) * 0.000003));
        String newLng = Double.toString(generatorOriginLng + ((randomLng + elapsedTime) * 0.00001));

        return ImmutableMap.<String, String>of("who", userName, "lat", newLat, "lng", newLng);
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_live_pubnub);
        this.pubNub = initPubNub(this.userName);
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
    }

    private PubNub initPubNub(String userName) {
        PNConfiguration pnConfiguration = new PNConfiguration();
        pnConfiguration.setPublishKey(Constants.PUBNUB_PUBLISH_KEY);
        pnConfiguration.setSubscribeKey(Constants.PUBNUB_SUBSCRIBE_KEY);
        pnConfiguration.setSecure(true);
        pnConfiguration.setUuid(userName);

        return new PubNub(pnConfiguration);
    }

    public void setPubNub(PubNub pubNub) {
        this.pubNub = pubNub;
        this.userName = this.pubNub.getConfiguration().getUuid();
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
    public void onMapReady(GoogleMap mMap) {
        /*mMap = googleMap;

        // Add a marker in Sydney and move the camera
        LatLng sydney = new LatLng(-34, 151);
        mMap.addMarker(new MarkerOptions().position(sydney).title("Marker in Sydney"));
        mMap.moveCamera(CameraUpdateFactory.newLatLng(sydney));*/
        mMap = mMap;

        pubNub.addListener(new LocationSubscribePnCallback(new LocationSubscribeMapAdapter(this, mMap), Constants.SUBSCRIBE_CHANNEL_NAME));
        pubNub.subscribe().channels(Arrays.asList(Constants.SUBSCRIBE_CHANNEL_NAME)).execute();

        scheduleRandomUpdates();
    }


    private void scheduleRandomUpdates() {
        this.executorService = Executors.newSingleThreadScheduledExecutor();
        this.startTime = System.currentTimeMillis();

        this.executorService.scheduleAtFixedRate(new Runnable() {
            @Override
            public void run() {
                ((Activity) LivePubnubActivity.this).runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        int randomLat = random.nextInt(10);
                        int randomLng = random.nextInt(10);
                        long elapsedTime = System.currentTimeMillis() - startTime;

                        final Map<String, String> message = getNewLocationMessage(userName, randomLat, randomLng, elapsedTime);

                        pubNub.publish().channel(Constants.SUBSCRIBE_CHANNEL_NAME).message(message).async(
                                new PNCallback<PNPublishResult>() {
                                    @Override
                                    public void onResponse(PNPublishResult result, PNStatus status) {
                                        try {
                                            if (!status.isError()) {
                                                Log.v(TAG, "publish(" + JsonUtil.asJson(result) + ")");
                                            } else {
                                                Log.v(TAG, "publishErr(" + status.toString() + ")");
                                            }
                                        } catch (Exception e) {
                                            e.printStackTrace();
                                        }
                                    }
                                }
                        );
                    }
                });
            }
        }, 0, 5, TimeUnit.SECONDS);
    }

}
