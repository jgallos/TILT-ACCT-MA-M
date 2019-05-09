package com.programmer.jgallos.ma_m;

public class ReplyRecord {
    private String date, time, reply;

    public ReplyRecord(String date, String time, String reply) {
        this.reply = reply;
        this.date = date;
        this.time = time;
    }

    public ReplyRecord() {

    }


    public void setDate(String date) {
        this.date=date;
    }
    public void setTime(String time) {
        this.time=time;
    }
    public void setReply(String reply) {
        this.reply=reply;
    }

    public String getDate() {
        return date;
    }
    public String getTime() {
        return time;
    }
    public String getReply() {
        return reply;
    }

}