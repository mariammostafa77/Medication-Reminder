package com.example.medicationreminder.AddMed.Model;

public class MedDataWeek {
    String doseId;
    String dose;
    String time;
    String dayOfWeek;

    public MedDataWeek() {
    }

    public MedDataWeek(String doseId, String dose, String time, String dayOfWeek) {
        this.doseId = doseId;
        this.dose = dose;
        this.time = time;
        this.dayOfWeek = dayOfWeek;
    }

    public String getDoseId() {
        return doseId;
    }

    public void setDoseId(String DoseId) {
        this.doseId = DoseId;
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
