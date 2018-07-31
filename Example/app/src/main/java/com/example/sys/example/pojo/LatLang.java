package com.example.sys.example.pojo;

/**
 * Created by pegasys on 7/30/2018.
 */

public class LatLang {
    double mLat;
    double mLang;


    public LatLang(double mLat, double mLang) {
        this.mLat = mLat;
        this.mLang = mLang;
    }

    public double getmLat() {
        return mLat;
    }

    public void setmLat(double mLat) {
        this.mLat = mLat;
    }

    public double getmLang() {
        return mLang;
    }

    public void setmLang(double mLang) {
        this.mLang = mLang;
    }
}
