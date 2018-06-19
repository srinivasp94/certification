package com.srinivas.com.distrct.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import org.apache.commons.lang3.builder.ToStringBuilder;

/**
 * Created by pegasys on 6/5/2018.
 */

public class Mandals {

    @SerializedName("MandalId")
    @Expose
    public int mandalId;
    @SerializedName("MandalName")
    @Expose
    public String mandalName;

    public Mandals(int i, String s) {

    }

    @Override
    public String toString() {
        return new ToStringBuilder(this).append("mandalId", mandalId).append("mandalName", mandalName).toString();
    }
}
