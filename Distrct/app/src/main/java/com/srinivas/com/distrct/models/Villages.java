package com.srinivas.com.distrct.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import org.apache.commons.lang3.builder.ToStringBuilder;

/**
 * Created by pegasys on 6/26/2018.
 */

public class Villages {

    @SerializedName("VillageID")
    @Expose
    public Integer villageID;
    @SerializedName("VillageName")
    @Expose
    public String villageName;

    @Override
    public String toString() {
        return new ToStringBuilder(this).append("villageID", villageID).append("villageName", villageName).toString();
    }
}
