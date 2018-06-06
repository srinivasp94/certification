package com.example.pegasys.pegasyshmis.network;

import android.app.Activity;
import android.app.Fragment;
import android.content.Context;
import android.support.v4.content.WakefulBroadcastReceiver;
import android.util.Log;

import com.example.pegasys.pegasyshmis.MyApplication;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class RetrofitRequester {
    private final RetrofitResponseListener retrofitResponseListener;
    private Activity activity;
    private Context context;

    public RetrofitRequester(RetrofitResponseListener retrofitResponseListener) {

        this.retrofitResponseListener = retrofitResponseListener;

        if(retrofitResponseListener instanceof Activity)
        {
        this.context = (Context) retrofitResponseListener;
            this.activity = (Activity) retrofitResponseListener;
        }
        else if(retrofitResponseListener instanceof Fragment)
        {
            this.context = ((Fragment) retrofitResponseListener).getActivity();
            this.activity = ((Fragment) retrofitResponseListener).getActivity();

        }
        else if(retrofitResponseListener instanceof android.support.v4.app.Fragment)
        {
            this.context = ((android.support.v4.app.Fragment) retrofitResponseListener).getActivity();
            this.activity = ((android.support.v4.app.Fragment) retrofitResponseListener).getActivity();

        }else if(retrofitResponseListener instanceof WakefulBroadcastReceiver)
        {
            this.context =  (Context) retrofitResponseListener;
            this.activity = (Activity) retrofitResponseListener;

        }

    }

    public RetrofitRequester(Context context, RetrofitResponseListener retrofitResponseListener1) {

        this.retrofitResponseListener = retrofitResponseListener1;
        this.context=context;
    }


public void callPostServices(final Object obj, final int requesterId,String url){



    Call<Object> dfdf = MyApplication.getInstance().getAPIInterface().callPost(url, obj);
    dfdf.enqueue(new Callback<Object>() {
        @Override
        public void onResponse(Call<Object> call, Response<Object> response) {
            Log.d("retrofit",response.toString());
            retrofitResponseListener.onResponseSuccess(response.body(),obj,requesterId);
        }

        @Override
        public void onFailure(Call<Object> call, Throwable t) {
        }
    });
}

}
