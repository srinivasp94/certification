package com.example.sys.example;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import org.apache.commons.lang3.builder.ToStringBuilder;

/**
 * Created by sys on 12/5/2017.
 */

public class Resp {
        @SerializedName("name")
        @Expose
        public String name;
        @SerializedName("mobile")
        @Expose
        public String mobile;
        @SerializedName("user_id")
        @Expose
        public String userId;
        @SerializedName("otp")
        @Expose
        public String otp;
        @SerializedName("status")
        @Expose
        public String status;


        @Override
        public String toString() {
            return new ToStringBuilder(this).append("name", name).append("mobile", mobile).append("userId", userId).append("otp", otp).append("status", status).toString();
        }

}

