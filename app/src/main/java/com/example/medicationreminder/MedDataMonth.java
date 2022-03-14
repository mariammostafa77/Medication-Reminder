package com.example.medicationreminder;

public class MedDataMonth {
    String medId;
    String dose;
    String time;
    String dayOfMonth;
    public MedDataMonth() {
    }

    public MedDataMonth(String medId, String dose, String time, String dayOfMonth) {
        this.medId = medId;
        this.dose = dose;
        this.time = time;
        this.dayOfMonth = dayOfMonth;
    }

    public String getMedId() {
        return medId;
    }

    public void setId(String medId) {
        this.medId = medId;
    }

    public String getDose() {
        return dose;
    }

    public void setDose(String dose) {
        this.dose = dose;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getDayOfMonth() {
        return dayOfMonth;
    }

    public void setDayOfMonth(String dayOfWeek) {
        this.dayOfMonth = dayOfWeek;
    }
}