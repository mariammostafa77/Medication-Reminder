package com.example.medicationreminder.ShowMedication.model;

import com.example.medicationreminder.MedAdapter;
import com.example.medicationreminder.MedInfo;

import java.util.ArrayList;

public interface IshowMedicationModel {
    void getMed(ArrayList<MedInfo> medList, MedAdapter medAdapter, int day, int month, int year);
    boolean betweenDate(int sDay,int sMonth,int sYear,int eDay,int eMonth,int eYear,int cDay,int cMonth,int cYear);
    boolean equalDate(int sDay,int sMonth,int sYear,int eDay,int eMonth,int eYear,int cDay,int cMonth,int cYear);
    int converToDayNum(String myDate);
    String dayName(int dayNum);
     int[] splitMedDate(String data);
}
