package com.srinivas.com.distrct.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import org.apache.commons.lang3.builder.ToStringBuilder;

/**
 * Created by pegasys on 7/10/2018.
 */

public class videomodels {
    @SerializedName("VideoID")
    @Expose
    public Integer videoID;
    @SerializedName("VideoUrl1")
    @Expose
    public String videoUrl1;
    @SerializedName("VideoUrl2")
    @Expose
    public String videoUrl2;
    @SerializedName("VideoUrl3")
    @Expose
    public String videoUrl3;
    @SerializedName("VideoUrl4")
    @Expose
    public String videoUrl4;
    @SerializedName("VideoUrl5")
    @Expose
    public String videoUrl5;
    @SerializedName("IsDeleted")
    @Expose
    public Boolean isDeleted;

    @Override
    public String toString() {
        return new ToStringBuilder(this).append("videoID", videoID).append("videoUrl1", videoUrl1).append("videoUrl2", videoUrl2).append("videoUrl3", videoUrl3).append("videoUrl4", videoUrl4).append("videoUrl5", videoUrl5).append("isDeleted", isDeleted).toString();
    }
}
