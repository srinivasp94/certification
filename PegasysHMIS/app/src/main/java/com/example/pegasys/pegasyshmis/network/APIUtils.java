package com.example.pegasys.pegasyshmis.network;

import android.app.Activity;
import android.content.Context;

/**
 * Created by pegasys on 5/21/2018.
 */

public class APIUtils {
    private Context context;
    private Activity activity;

    public APIUtils(Context context) {
        this.context = context;
    }

    private APIUtils() {
    }

    public static final String BASE_URL = "http://mscoolingsolutions.com/";


    public static APIInterface getAPIService() {

        return RetrofitClient.getClient(BASE_URL).create(APIInterface.class);
    }
}
