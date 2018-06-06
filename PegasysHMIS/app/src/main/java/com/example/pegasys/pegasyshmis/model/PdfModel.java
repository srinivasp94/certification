package com.example.pegasys.pegasyshmis.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import org.apache.commons.lang3.builder.ToStringBuilder;

import java.util.List;


/**
 * Created by pegasys on 5/28/2018.
 */

public class PdfModel {
    @SerializedName("status")
    @Expose
    public Integer status;
    @SerializedName("records")
    @Expose
    public List<Records> records = null;

    @Override
    public String toString() {
        return new ToStringBuilder(this).append("status", status).append("records", records).toString();
    }
}
