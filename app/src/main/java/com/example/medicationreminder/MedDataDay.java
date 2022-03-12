package com.example.medicationreminder;

import java.util.HashMap;

public class MedDataDay {
   int id;
   double dose;
   String time;

   public MedDataDay(int id, double dose, String time) {
      this.id = id;
      this.dose = dose;
      this.time = time;
   }

   public MedDataDay() {
   }

   public int getId() {
      return id;
   }

   public void setId(int id) {
      this.id = id;
   }

   public double getDose() {
      return dose;
   }

   public void setDose(double dose) {
      this.dose = dose;
   }

   public String getTime() {
      return time;
   }

   public void setTime(String time) {
      this.time = time;
   }
}
