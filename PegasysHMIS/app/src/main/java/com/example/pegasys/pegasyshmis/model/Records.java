package com.example.pegasys.pegasyshmis.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import org.apache.commons.lang3.builder.ToStringBuilder;

/**
 * Created by pegasys on 5/28/2018.
 */

public class Records {
    @SerializedName("filename")
    @Expose
    public String filename;
    @SerializedName("filepath")
    @Expose
    public String filepath;

    @Override
    public String toString() {
        return new ToStringBuilder(this).append("filename", filename).append("filepath", filepath).toString();
    }
}
