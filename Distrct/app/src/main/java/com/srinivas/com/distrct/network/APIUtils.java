package com.srinivas.com.distrct.network;

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

    public static final String BASE_URL = "http://13.59.168.219/api/";


    public static ApiInterface getAPIService() {

        return RetrofitApiClient.getClient(BASE_URL).create(ApiInterface.class);
    }
}
