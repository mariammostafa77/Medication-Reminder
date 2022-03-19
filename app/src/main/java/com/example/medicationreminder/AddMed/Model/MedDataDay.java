package com.example.medicationreminder.AddMed.Model;

public class MedDataDay {
   String dose;
   String time;
   String doseId;


   public MedDataDay() {
   }

   public MedDataDay( String dose, String time,String doseId) {
      this.dose = dose;
      this.time = time;
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

   public void add(MedDataDay medDataDay) {
   }
}
