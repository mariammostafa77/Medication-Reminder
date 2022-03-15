package com.example.medicationreminder;

import com.google.common.reflect.TypeToken;

import java.util.List;

public class MedData <T>{
    String medName, medUnit, startDate, endDate,userId,medId,medTakenUnit;
    int numOfTimes;
    RefillMed refillMedData;
    List<T> timeList;
    public MedData() {
    }

    public MedData(String medName, String medUnit, String startDate, String endDate,String userId,String medId,int numOfTimes,String medTakenUnit,List<T> timeList) {
        this.medName = medName;
        this.medUnit = medUnit;
        this.startDate = startDate;
        this.endDate = endDate;
        this.userId=userId;
        this.medId=medId;
        this.numOfTimes = numOfTimes;
        this.medTakenUnit=medTakenUnit;
        this.timeList=timeList;
    }

    public MedData(String medName, String medUnit, String startDate, String endDate,String userId,String medId,int numOfTimes,List<T> timeList,RefillMed refillMedData) {
        this.medName = medName;
        this.medUnit = medUnit;
        this.startDate = startDate;
        this.endDate = endDate;
        this.userId=userId;
        this.medId=medId;
        this.numOfTimes = numOfTimes;
        this.timeList=timeList;
        this.refillMedData=refillMedData;
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

    public String getMedId() {
        return medId;
    }

    public void setMedId(String medId) {
        this.medId = medId;
    }

    public List<T> getTimeList() {
        return timeList;
    }

    public void setTimeList(List<T> timeList) {
        this.timeList = timeList;
    }

    public RefillMed getRefillMedData() {
        return refillMedData;
    }

    public void setRefillMedData(RefillMed refillMedData) {
        this.refillMedData = refillMedData;
    }

    public String getMedTakenUnit() {
        return medTakenUnit;
    }

    public void setMedTakenUnit(String medTakenUnit) {
        this.medTakenUnit = medTakenUnit;
    }
}