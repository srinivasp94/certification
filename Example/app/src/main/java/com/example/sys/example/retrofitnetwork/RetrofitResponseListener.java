package com.example.sys.example.retrofitnetwork;

import java.util.ArrayList;
import java.util.Map;

/**
 * Created by elancer on 10/12/2017.
 */

public interface RetrofitResponseListener {

    void onResponseSuccess(Object objectResponse, Object objectRequest, int requestId);
}
