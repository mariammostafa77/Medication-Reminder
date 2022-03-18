package com.example.medicationreminder.AddMed.Model;

public class MedDataDay {
   String dose;
   String time;


   public MedDataDay() {
   }

   public MedDataDay( String dose, String time) {
      this.dose = dose;
      this.time = time;
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
