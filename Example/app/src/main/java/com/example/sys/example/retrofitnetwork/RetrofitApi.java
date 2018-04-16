package com.example.sys.example.retrofitnetwork;

import com.example.sys.example.pojo.DocID;
import com.example.sys.example.pojo.Qualification;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

/*
 * Created by srinivas on 23-12-2017.
 */

public interface RetrofitApi {
    @POST("/webservices/getallspecialisations")
    Call<Qualification> getservice(@Body DocID docID);
}
