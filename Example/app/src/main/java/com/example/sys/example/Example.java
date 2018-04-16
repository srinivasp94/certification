package com.example.sys.example;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import org.apache.commons.lang3.builder.ToStringBuilder;

/**
 * Created by sys on 12/5/2017.
 */

public class Example {
        @SerializedName("name")
        @Expose
        public String name;
        @SerializedName("mobile")
        @Expose
        public String mobile;
        @SerializedName("email_id")
        @Expose
        public String emailId;
        @SerializedName("password")
        @Expose
        public String password;
        @SerializedName("cpassword")
        @Expose
        public String cpassword;
        @SerializedName("referee")
        @Expose
        public String referee;
        @SerializedName("device_token")
        @Expose
        public String deviceToken;
        @SerializedName("device_type")
        @Expose
        public String deviceType;

        @Override
        public String toString() {
            return new ToStringBuilder(this).append("name", name).append("mobile", mobile).append("emailId", emailId).append("password", password).append("cpassword", cpassword).append("referee", referee).append("deviceToken", deviceToken).append("deviceType", deviceType).toString();
        }

}

