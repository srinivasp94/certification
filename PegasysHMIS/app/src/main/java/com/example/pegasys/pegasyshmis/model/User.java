package com.example.pegasys.pegasyshmis.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import org.apache.commons.lang3.builder.ToStringBuilder;

/**
 * Created by pegasys on 5/22/2018.
 */

public class User {
    @SerializedName("userid")
    @Expose
    public String userid;
    @SerializedName("username")
    @Expose
    public String username;
    @SerializedName("email")
    @Expose
    public String email;
    @SerializedName("password")
    @Expose
    public String password;
    @SerializedName("first_name")
    @Expose
    public String firstName;
    @SerializedName("last_name")
    @Expose
    public String lastName;
    @SerializedName("userpin")
    @Expose
    public String userpin;
    @SerializedName("created_on")
    @Expose
    public String createdOn;
    @SerializedName("user_status")
    @Expose
    public String userStatus;
    @SerializedName("device_id")
    @Expose
    public String deviceId;
    @SerializedName("device_location")
    @Expose
    public String deviceLocation;

    @Override
    public String toString() {
        return new ToStringBuilder(this).append("userid", userid).append("username", username).append("email", email).append("password", password).append("firstName", firstName).append("lastName", lastName).append("userpin", userpin).append("createdOn", createdOn).append("userStatus", userStatus).append("deviceId", deviceId).append("deviceLocation", deviceLocation).toString();
    }
}
