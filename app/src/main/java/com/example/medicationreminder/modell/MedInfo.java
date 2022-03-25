package com.example.medicationreminder.modell;

import com.example.medicationreminder.AddMed.Model.RefillMed;
import com.example.medicationreminder.Model.TimeOfMed;

import java.util.List;

public class MedInfo {
    private String medName;
    private String time;
    private String medUnit;
    private String userId;
    private String startDate;
    private String endDate;
    private String medId;
    private int img;
    private String notes;
    private String taken;
    private int numOfTimes;
    private List<TimeOfMed> timeList;
    private String medTakenUnit;
    private RefillMed refillMedData;


    public String getMedTakenUnit() {
        return medTakenUnit;
    }

    public void setMedTakenUnit(String medTakenUnit) {
        this.medTakenUnit = medTakenUnit;
    }

    public List<TimeOfMed> getTimeList() {
        return timeList;
    }

    public void setTimeList(List<TimeOfMed> timeList) {
        this.timeList = timeList;
    }

    public int getNumOfTimes() {
        return numOfTimes;
    }

    public void setNumOfTimes(int numOfTimes) {
        this.numOfTimes = numOfTimes;
    }

    public String getNotes() {
        return notes;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }

    public String getTaken() {
        return taken;
    }

    public void setTaken(String taken) {
        this.taken = taken;
    }

    public MedInfo(String medName, int img) {
        this.medName = medName;
        this.img = img;
    }

    public int getImg() {
        return img;
    }

    public void setImg(int img) {
        this.img = img;
    }

    public String getMedId() {
        return medId;
    }

    public void setMedId(String medId) {
        this.medId = medId;
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

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userid) {
        this.userId = userid;
    }

    public MedInfo() {
    }


    public String getMedName() {
        return medName;
    }

    public void setMedName(String medName) {
        this.medName = medName;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getMedUnit() {
        return medUnit;
    }

    public void setMedUnit(String medUnit) {
        this.medUnit = medUnit;
    }

    public RefillMed getRefillMedData() {
        return refillMedData;
    }

    public void setRefillMedData(RefillMed refillMedData) {
        this.refillMedData = refillMedData;
    }
}