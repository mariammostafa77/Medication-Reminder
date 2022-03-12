package com.example.medicationreminder;

public class MedData {
    String medName, medUnit, startDate, endDate,userId;
    int numOfTimes;

    public MedData() {
    }

    public MedData(String medName, String medUnit, String startDate, String endDate,String userId,int numOfTimes) {
        this.medName = medName;
        this.medUnit = medUnit;
        this.startDate = startDate;
        this.endDate = endDate;
        this.userId=userId;
        this.numOfTimes = numOfTimes;
    }

    public String getMedName() {
        return medName;
    }

    public void setMedName(String medName) {
        this.medName = medName;
    }

    public String getMedUnit() {
        return medUnit;
    }

    public void setMedUnit(String medUnit) {
        this.medUnit = medUnit;
    }

    public String getStartDate() {
        return startDate;
    }

    public void setStartDate(String startDate) {
        this.startDate = startDate;
    }

    public String getEndDate() {
        return endDate;
    }

    public void setEndDate(String endDate) {
        this.endDate = endDate;
    }

    public int getNumOfTimes() {
        return numOfTimes;
    }

    public void setNumOfTimes(int numOfTimes) {
        this.numOfTimes = numOfTimes;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }
}