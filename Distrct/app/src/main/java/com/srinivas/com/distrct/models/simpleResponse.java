package com.srinivas.com.distrct.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import org.apache.commons.lang3.builder.ToStringBuilder;

/**
 * Created by pegasys on 6/5/2018.
 */

public class simpleResponse {
    /*@SerializedName("Message")
    @Expose
    public String Message;*/

    @SerializedName("NotSpecified")
    @Expose
    public Integer notSpecified;
    @SerializedName("Status")
    @Expose
    public Integer status;
    @SerializedName("Message")
    @Expose
    public String message;
    @SerializedName("Message1")
    @Expose
    public Object message1;
    @SerializedName("Message2")
    @Expose
    public Object message2;
    @SerializedName("Message3")
    @Expose
    public Object message3;
    @SerializedName("Message4")
    @Expose
    public Object message4;
    @SerializedName("Message5")
    @Expose
    public Object message5;
    @SerializedName("UserType")
    @Expose
    public Object userType;
    @SerializedName("Extra1")
    @Expose
    public Object extra1;
    @SerializedName("Data")
    @Expose
    public Object data;
    @SerializedName("Extra2")
    @Expose
    public Object extra2;

    @Override
    public String toString() {
        return new ToStringBuilder(this).append("notSpecified", notSpecified).append("status", status).append("message", message).append("message1", message1).append("message2", message2).append("message3", message3).append("message4", message4).append("message5", message5).append("userType", userType).append("extra1", extra1).append("data", data).append("extra2", extra2).toString();
    }
}
