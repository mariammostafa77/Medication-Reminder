package com.example.medicationreminder;

public class RefillMed {
    String remindTime;
    int pillLeftNum,numOfRemind;

    public RefillMed() {
    }

    public RefillMed(String remindTime, int pillLeftNum, int numOfRemind) {
        this.remindTime = remindTime;
        this.pillLeftNum = pillLeftNum;
        this.numOfRemind = numOfRemind;
    }



    public String getRemindTime() {
        return remindTime;
    }

    public void setRemindTime(String remindTime) {
        this.remindTime = remindTime;
    }

    public int getpillLeftNum() {
        return pillLeftNum;
    }

    public void setpillLeftNum(int pillLeftNum) {
        this.pillLeftNum = pillLeftNum;
    }

    public int getNumOfRemind() {
        return numOfRemind;
    }

    public void setNumOfRemind(int numOfRemind) {
        this.numOfRemind = numOfRemind;
    }
}
