package com.srinivas.com.distrct.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import org.apache.commons.lang3.builder.ToStringBuilder;

/**
 * Created by pegasys on 6/5/2018.
 */

public class Categories {
    @SerializedName("CategoryID")
    @Expose
    public int categoryID;
    @SerializedName("CategoryName")
    @Expose
    public String categoryName;

    public Categories(int i, String s) {

    }

    @Override
    public String toString() {
        return new ToStringBuilder(this).append("categoryID", categoryID).append("categoryName", categoryName).toString();
    }
}
