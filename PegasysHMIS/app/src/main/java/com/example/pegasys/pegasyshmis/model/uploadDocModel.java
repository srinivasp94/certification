package com.example.pegasys.pegasyshmis.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import org.apache.commons.lang3.builder.ToStringBuilder;

/**
 * Created by pegasys on 5/23/2018.
 */

public class uploadDocModel {
    @SerializedName("status")
    @Expose
    public Boolean status;
    @SerializedName("message")
    @Expose
    public String message;
    @SerializedName("pdfcontent")
    @Expose
    public String pdfcontent;
    @SerializedName("pdfdetails")
    @Expose
    public String pdfdetails;
    @SerializedName("filename")
    @Expose
    public String filename;

    @Override
    public String toString() {
        return new ToStringBuilder(this).append("status", status).append("message", message).append("pdfcontent", pdfcontent).append("pdfdetails", pdfdetails).append("filename", filename).toString();
    }
}
