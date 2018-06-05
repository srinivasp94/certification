package com.srinivas.com.distrct.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import org.apache.commons.lang3.builder.ToStringBuilder;

/**
 * Created by pegasys on 6/5/2018.
 */

public class SubCategoriesModel {
    @SerializedName("SubCategoryID")
    @Expose
    public Integer subCategoryID;
    @SerializedName("SubCategoryName")
    @Expose
    public String subCategoryName;

    @Override
    public String toString() {
        return new ToStringBuilder(this).append("subCategoryID", subCategoryID).append("subCategoryName", subCategoryName).toString();
    }
}
