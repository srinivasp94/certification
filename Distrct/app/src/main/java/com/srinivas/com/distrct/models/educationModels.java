package com.srinivas.com.distrct.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import org.apache.commons.lang3.builder.ToStringBuilder;

/**
 * Created by pegasys on 5/4/2018.
 */

public class educationModels {
    @SerializedName("Userid")
    @Expose
    public int userid;
    @SerializedName("Title")
    @Expose
    public String title;
    @SerializedName("Name")
    @Expose
    public String name;
    @SerializedName("Phonenumber")
    @Expose
    public String phonenumber;
    @SerializedName("Image")
    @Expose
    public String image;
    @SerializedName("Landmark")
    @Expose
    public String landmark;
    @SerializedName("Address")
    @Expose
    public String address;
    @SerializedName("Timings")
    @Expose
    public String timings;
    @SerializedName("YearOfEstrablish")
    @Expose
    public String yearOfEstrablish;
    @SerializedName("Description")
    @Expose
    public String description;
    @SerializedName("roleid")
    @Expose
    public int roleid;
    @SerializedName("Category")
    @Expose
    public int category;
    @SerializedName("SubCategory")
    @Expose
    public Integer subCategory;
    @SerializedName("Mandal")
    @Expose
    public int mandal;
    @SerializedName("Post")
    @Expose
    public int post;
    @SerializedName("IsCom")
    @Expose
    public Boolean isCom;
    @SerializedName("CreatedBy")
    @Expose
    public int createdBy;
    @SerializedName("ModifiedBY")
    @Expose
    public int modifiedBY;
    @SerializedName("CreatedDate")
    @Expose
    public String createdDate;
    @SerializedName("ModifiedDate")
    @Expose
    public String modifiedDate;
    @SerializedName("IsProfileCompleted")
    @Expose
    public Boolean isProfileCompleted;
    @SerializedName("IsDeleted")
    @Expose
    public Boolean isDeleted;

    @Override
    public String toString() {
        return new ToStringBuilder(this).append("userid", userid).append("title", title).append("name", name).append("phonenumber", phonenumber).append("image", image).append("landmark", landmark).append("address", address).append("timings", timings).append("yearOfEstrablish", yearOfEstrablish).append("description", description).append("roleid", roleid).append("category", category).append("subCategory", subCategory).append("mandal", mandal).append("post", post).append("isCom", isCom).append("createdBy", createdBy).append("modifiedBY", modifiedBY).append("createdDate", createdDate).append("modifiedDate", modifiedDate).append("isProfileCompleted", isProfileCompleted).append("isDeleted", isDeleted).toString();
    }
}
