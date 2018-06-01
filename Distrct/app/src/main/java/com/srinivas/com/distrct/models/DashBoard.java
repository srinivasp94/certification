package com.srinivas.com.distrct.models;

/**
 * Created by pegasys on 5/1/2018.
 */

public class DashBoard {
    String mName;
    int mColor;
    int mId;

    public DashBoard(String mName, int mColor, int mId) {
        this.mName = mName;
        this.mColor = mColor;
        this.mId = mId;
    }

    public String getmName() {
        return mName;
    }

    public void setmName(String mName) {
        this.mName = mName;
    }

    public int getmColor() {
        return mColor;
    }

    public void setmColor(int mColor) {
        this.mColor = mColor;
    }

    public int getmId() {
        return mId;
    }

    public void setmId(int mId) {
        this.mId = mId;
    }
}
