package com.example.medicationreminder;

public class MedDataWeek {
    String medId;
    String dose;
    String time;
    String dayOfWeek;

    public MedDataWeek() {
    }

    public MedDataWeek(String medId, String dose, String time, String dayOfWeek) {
        this.medId = medId;
        this.dose = dose;
        this.time = time;
        this.dayOfWeek = dayOfWeek;
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

    public String getDayOfWeek() {
        return dayOfWeek;
    }

    public void setDayOfWeek(String dayOfWeek) {
        this.dayOfWeek = dayOfWeek;
    }
}
