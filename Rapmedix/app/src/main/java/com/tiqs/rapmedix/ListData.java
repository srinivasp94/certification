package com.tiqs.rapmedix;
/*
 * ListData class will hold data for displaying in ListView
 * */
public class ListData {

    String Description;

    String title,title1;
    int imgResId;

    public String getDescription() {
        return Description;
    }

    public void setDescription(String description) {
        Description = description;
    }

    public String getTitle() {
        return title;


    }

    public void setTitle(String title,String title1) {
        this.title = title;
        this.title1 = title1;
    }

    public int getImgResId() {
        return imgResId;
    }

    public void setImgResId(int imgResId) {
        this.imgResId = imgResId;
    }

}
