package com.example.sys.example.pojo;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import org.apache.commons.lang3.builder.ToStringBuilder;

import java.util.List;

/**
 * Created by pegasys on 7/13/2018.
 */

public class PolyResponce {
    @SerializedName("CrimeThematicList")
    @Expose
    public List<CrimeThematicList> crimeThematicList = null;
    @SerializedName("code")
    @Expose
    public String code;
    @SerializedName("Message")
    @Expose
    public String message;

    @Override
    public String toString() {
        return new ToStringBuilder(this).append("crimeThematicList", crimeThematicList).append("code", code).append("message", message).toString();
    }
}
