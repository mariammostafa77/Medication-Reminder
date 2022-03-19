package com.example.medicationreminder.ShowMedication.model;

public class TimeOfMed {

        String medId;
        String dose;
        String time;
        String dayOfMonth;
        String dayOfWeek;
        String doseId;

        public TimeOfMed() {
        }

        public TimeOfMed(String dose, String time) {
            this.dose = dose;
            this.time = time;
        }

        public String getMedId() {
            return medId;
        }

        public void setMedId(String medId) {
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

        public void setDayOfMonth(String dayOfMonth) {
            this.dayOfMonth = dayOfMonth;
        }

        public String getDayOfWeek() {
            return dayOfWeek;
        }

        public void setDayOfWeek(String dayOfWeek) {
            this.dayOfWeek = dayOfWeek;
        }

    public String getDoseId() {
        return doseId;
    }

    public void setDoseId(String doseId) {
        this.doseId = doseId;
    }
}

