package com.example.pegasys.pegasyshmis.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import org.apache.commons.lang3.builder.ToStringBuilder;

/**
 * Created by pegasys on 5/21/2018.
 */

public class ResponseModel {
    @SerializedName("status")
    @Expose
    public Boolean status;
    @SerializedName("message")
    @Expose
    public String message;

    @Override
    public String toString() {
        return new ToStringBuilder(this).append("status", status).append("message", message).toString();
    }
}
