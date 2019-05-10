package com.programmer.jgallos.ma_m;

public class AttendanceRecord {
    private String date, signin, signout, name;

    public AttendanceRecord(String date, String signin, String signout, String name) {
        this.date = date;
        this.signin = signin;
        this.signout = signout;
        this.name = name;
    }

    public AttendanceRecord() {

    }

    public void setDate(String date) { this.date=date; }
    public void setSignin(String signin) { this.signin = signin; }
    public void setSignout(String signout) {
        this.signout = signout;
    }
    public void setName(String name) { this.name = name; }

    public String getDate() { return date; }
    public String getSignin() { return signin; }
    public String getSignout() { return signout; }
    public String getName() { return name; }
}

