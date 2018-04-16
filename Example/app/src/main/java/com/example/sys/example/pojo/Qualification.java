package com.example.sys.example.pojo;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import org.apache.commons.lang3.builder.ToStringBuilder;

import java.util.List;

/**
 * Created by pegasys on 12/26/2017.
 */

public class Qualification {
    @SerializedName("0")
    @Expose
    public List<QualificationList> mQualificationList= null;
    @SerializedName("status")
    @Expose
    public String status;

    @Override
    public String toString() {
        return new ToStringBuilder(this).append("QualificationList", mQualificationList).append("status", status).toString();
    }
}
