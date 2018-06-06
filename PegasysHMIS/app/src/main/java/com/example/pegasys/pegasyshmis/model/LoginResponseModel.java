package com.example.pegasys.pegasyshmis.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import org.apache.commons.lang3.builder.ToStringBuilder;

/**
 * Created by pegasys on 5/22/2018.
 */

public class LoginResponseModel {

    @SerializedName("status")
    @Expose
    public Boolean status;
    @SerializedName("message")
    @Expose
    public String message;
    @SerializedName("user")
    @Expose
    public User user;

    @Override
    public String toString() {
        return new ToStringBuilder(this).append("status", status).append("message", message).append("user", user).toString();
    }
}
