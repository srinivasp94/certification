package com.example.sys.example.pojo;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import org.apache.commons.lang3.builder.ToStringBuilder;

/**
 * Created by pegasys on 12/26/2017.
 */

public class DocID {
    public DocID(String doctorId) {
        this.doctorId = doctorId;
    }

    @SerializedName("doctor_id")
    @Expose
    public String doctorId;


    @Override
    public String toString() {
        return new ToStringBuilder(this).append("doctorId", doctorId).toString();
    }
}
