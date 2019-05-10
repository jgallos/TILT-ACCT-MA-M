package com.programmer.jgallos.ma_m;

public class FeedbackRecord {
    private String desc, level, status;

    public FeedbackRecord(String desc, String level, String status) {
        this.desc = desc;
        this.level = level;
        this.status = status;

    }

    public FeedbackRecord() {

    }

    public void setDesc(String desc) { this.desc=desc; }
    public void setLevel(String level) { this.level = level; }
    public void setStatus(String status) {
        this.status = status;
    }
    public String getDesc() { return desc; }
    public String getLevel() { return level; }
    public String getStatus() { return status; }
}
