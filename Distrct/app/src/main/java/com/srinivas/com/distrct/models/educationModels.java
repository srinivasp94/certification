package com.srinivas.com.distrct.models;

/**
 * Created by pegasys on 5/4/2018.
 */

public class educationModels {
    String scl_name;
    String scl_address;
    String scl_phonenumber;
    int scl_image;

    public educationModels(String scl_name, String scl_address, String scl_phonenumber, int scl_image) {
        this.scl_name = scl_name;
        this.scl_address = scl_address;
        this.scl_phonenumber = scl_phonenumber;
        this.scl_image = scl_image;
    }

    public String getScl_name() {
        return scl_name;
    }

    public void setScl_name(String scl_name) {
        this.scl_name = scl_name;
    }

    public String getScl_address() {
        return scl_address;
    }

    public void setScl_address(String scl_address) {
        this.scl_address = scl_address;
    }

    public String getScl_phonenumber() {
        return scl_phonenumber;
    }

    public void setScl_phonenumber(String scl_phonenumber) {
        this.scl_phonenumber = scl_phonenumber;
    }

    public int getScl_image() {
        return scl_image;
    }

    public void setScl_image(int scl_image) {
        this.scl_image = scl_image;
    }
}
