package com.programmer.jgallos.ma_m;

import android.util.Log;

public class FeedbackRecord {
    private String desc, level, status;
    private static final String TAG = FeedbackRecord.class.getSimpleName();

    public FeedbackRecord(String desc, String level, String status) {
        this.desc = desc;
        this.level = level;
        this.status = status;

    }

    public FeedbackRecord() {

    }

    public void setDesc(String desc) {
        this.desc=desc;
        Log.d(TAG,"Set Date = " + desc);
    }
    public void setLevel(String level) {
        Log.d(TAG,"Set signin_time = " + level);
        this.level = level;
    }
    public void setStatus(String status) {
        this.status = status;
    }


    public String getDesc() {
        Log.d(TAG,"Pulled Date = " + desc);
        return desc;
    }
    public String getLevel() {
        Log.d(TAG,"Pulled Signin Time = " + level);
        return level;
    }
    public String getStatus() {
        Log.d(TAG,"Pulled Signout Time = " + status);
        return status;
    }
}
