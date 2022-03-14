package com.example.medicationreminder;

public class RefillMed {
    String userId,medId,remindTime;
    int pillLeftMun,numOfRemind;

    public RefillMed() {
    }

    public RefillMed(String userId, String medId, String remindTime, int pillLeftMun, int numOfRemind) {
        this.userId = userId;
        this.medId = medId;
        this.remindTime = remindTime;
        this.pillLeftMun = pillLeftMun;
        this.numOfRemind = numOfRemind;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getMedId() {
        return medId;
    }

    public void setMedId(String medId) {
        this.medId = medId;
    }

    public String getRemindTime() {
        return remindTime;
    }

    public void setRemindTime(String remindTime) {
        this.remindTime = remindTime;
    }

    public int getPillLeftMun() {
        return pillLeftMun;
    }

    public void setPillLeftMun(int pillLeftMun) {
        this.pillLeftMun = pillLeftMun;
    }

    public int getNumOfRemind() {
        return numOfRemind;
    }

    public void setNumOfRemind(int numOfRemind) {
        this.numOfRemind = numOfRemind;
    }
}
