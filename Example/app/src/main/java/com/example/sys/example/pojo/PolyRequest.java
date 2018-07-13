package com.example.sys.example.pojo;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import org.apache.commons.lang3.builder.ToStringBuilder;

/**
 * Created by pegasys on 7/13/2018.
 */

public class PolyRequest {
    @SerializedName("Clasification")
    @Expose
    public String clasification;
    @SerializedName("Percentage")
    @Expose
    public String percentage;
    @SerializedName("FromDate")
    @Expose
    public String fromDate;
    @SerializedName("ToDate")
    @Expose
    public String toDate;
    @SerializedName("CrimeTypeMaster_Id")
    @Expose
    public String crimeTypeMasterId;
    @SerializedName("CrimeSubTypeMaster_Id")
    @Expose
    public String crimeSubTypeMasterId;
    @SerializedName("PsId")
    @Expose
    public String psId;
    @SerializedName("HierarchyID")
    @Expose
    public String hierarchyID;
    @SerializedName("Duraion")
    @Expose
    public String duraion;

    @Override
    public String toString() {
        return new ToStringBuilder(this).append("clasification", clasification).append("percentage", percentage).append("fromDate", fromDate).append("toDate", toDate).append("crimeTypeMasterId", crimeTypeMasterId).append("crimeSubTypeMasterId", crimeSubTypeMasterId).append("psId", psId).append("hierarchyID", hierarchyID).append("duraion", duraion).toString();
    }

}
