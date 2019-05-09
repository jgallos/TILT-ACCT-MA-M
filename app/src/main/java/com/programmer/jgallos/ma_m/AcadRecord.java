package com.programmer.jgallos.ma_m;

import android.util.Log;

public class AcadRecord {
    private String title, desc, imageUrl, username, uid, date;
    private static final String TAG = AcadRecord.class.getSimpleName();

    public AcadRecord(String title, String desc, String imageUrl, String username, String uid, String date) {
        this.title = title;
        this.desc = desc;
        this.imageUrl = imageUrl;
        this.username = username;
        this.uid = uid;
        this.date = date;

    }

    public AcadRecord() {

    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl=imageUrl;
    }
    public void setUsername(String username) {
        this.username = username;
    }
    public void setTitle(String title) {
        this.title = title;
    }
    public void setDesc(String desc) {
        this.desc = desc;
    }
    public void setUid(String uid) {
        this.uid = uid;
    }
    public void setDate(String date) {
        this.date = date;
    }


    public String getImageUrl() {
        return imageUrl;
    }
    public String getTitle() {
        return title;
    }
    public  String getUsername() {
        return username;
    }
    public String getDesc() {
        Log.d(TAG,desc);
        return desc;
    }
    public String getUid() {
        return uid;
    }
    public String getDate() {
        return date;
    }
}
