package com.example.medicationreminder;

import java.util.HashMap;

public class MedDataDay {
   String medId;
   String dose;
   String time;


   public MedDataDay() {
   }

   public MedDataDay(String medId, String dose, String time) {
      this.medId = medId;
      this.dose = dose;
      this.time = time;
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

   public void add(MedDataDay medDataDay) {
   }
}
