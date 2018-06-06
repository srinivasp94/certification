package com.example.pegasys.pegasyshmis.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import org.apache.commons.lang3.builder.ToStringBuilder;

/**
 * Created by pegasys on 5/21/2018.
 */

public class SignupModel {

    @SerializedName("firstname")
    @Expose
    public String firstname;
    @SerializedName("lastname")
    @Expose
    public String lastname;
    @SerializedName("username")
    @Expose
    public String username;
    @SerializedName("email")
    @Expose
    public String email;
    @SerializedName("password")
    @Expose
    public String password;
    @SerializedName("deviceid")
    @Expose
    public String deviceid;
    @SerializedName("devicelocation")
    @Expose
    public String devicelocation;

    @Override
    public String toString() {
        return new ToStringBuilder(this).append("firstname", firstname).append("lastname", lastname).append("username", username).append("email", email).append("password", password).append("deviceid", deviceid).append("devicelocation", devicelocation).toString();
    }

}
