package com.example.pegasys.pegasyshmis.model;

/**
 * Created by pegasys on 3/22/2018.
 */

public class AllHealthRecords {
    public String Name;
    public int image;

    public AllHealthRecords(String name, int image) {
        Name = name;
        this.image = image;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public int getImage() {
        return image;
    }

    public void setImage(int image) {
        this.image = image;
    }
}
