package com.example.pegasys.pegasyshmis.network;

/**
 * Created by elancer on 10/12/2017.
 */

public interface RetrofitResponseListener {

    void onResponseSuccess(Object objectResponse, Object objectRequest, int requestId);
}
