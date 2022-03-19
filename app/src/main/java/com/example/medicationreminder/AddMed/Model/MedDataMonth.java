package com.example.medicationreminder.AddMed.Model;

public class MedDataMonth {
    String dose;
    String time;
    String dayOfMonth;
    String doseId;
    public MedDataMonth() {
    }

    public MedDataMonth( String dose, String time, String dayOfMonth,String doseId) {
        this.dose = dose;
        this.time = time;
        this.dayOfMonth = dayOfMonth;
        this.doseId=doseId;
    }

    public String getDoseId() {
        return doseId;
    }

    public void setDoseId(String doseId) {
        this.doseId = doseId;
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
