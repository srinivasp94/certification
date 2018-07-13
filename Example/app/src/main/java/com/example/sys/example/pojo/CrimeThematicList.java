package com.example.sys.example.pojo;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import org.apache.commons.lang3.builder.ToStringBuilder;

/**
 * Created by pegasys on 7/13/2018.
 */

public class CrimeThematicList {

    @SerializedName("PsId")
    @Expose
    public String psId;
    @SerializedName("PsName")
    @Expose
    public String psName;
    @SerializedName("PsCode")
    @Expose
    public String psCode;
    @SerializedName("Classification")
    @Expose
    public String classification;
    @SerializedName("Mindate")
    @Expose
    public String mindate;
    @SerializedName("Maxdate")
    @Expose
    public String maxdate;
    @SerializedName("Count")
    @Expose
    public String count;
    @SerializedName("Boundary")
    @Expose
    public String boundary;
    @SerializedName("Latitude")
    @Expose
    public String latitude;
    @SerializedName("Longitude")
    @Expose
    public String longitude;
    @SerializedName("Color")
    @Expose
    public String color;

    @Override
    public String toString() {
        return new ToStringBuilder(this).append("psId", psId).append("psName", psName).append("psCode", psCode).append("classification", classification).append("mindate", mindate).append("maxdate", maxdate).append("count", count).append("boundary", boundary).append("latitude", latitude).append("longitude", longitude).append("color", color).toString();
    }

}
