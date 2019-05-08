package com.programmer.jgallos.ma_m;

import android.util.Log;

public class AttendanceRecord {
    private String date, signin, signout, name;
    private static final String TAG = AttendanceRecord.class.getSimpleName();

    public AttendanceRecord(String date, String signin, String signout, String name) {
        this.date = date;
        this.signin = signin;
        this.signout = signout;
        this.name = name;
    }

    public AttendanceRecord() {

    }

    public void setDate(String date) {
        this.date=date;
        Log.d(TAG,"Set Date = " + date);
    }
        /*public  String getUsername() {
            return username;
        }*/

        /*public void setUsername(String username) {
            this.username = username;
        }*/

    public void setSignin(String signin) {
        Log.d(TAG,"Set signin_time = " + signin);

        this.signin = signin;
    }

    public void setSignout(String signout) {
        this.signout = signout;
    }
    public void setName(String name) {
        Log.d(TAG,"Set uid = " + name);

        this.name = name;
    }

    public String getDate() {
        Log.d(TAG,"Pulled Date = " + date);
        return date;
    }
    public String getSignin() {
        Log.d(TAG,"Pulled Signin Time = " + signin);
        return signin;
    }
    public String getSignout() {
        Log.d(TAG,"Pulled Signout Time = " + signout);
        return signout;
    }
    public String getName() {
        Log.d(TAG,"Pulled Uid = " + name);

        return name;
    }
}

