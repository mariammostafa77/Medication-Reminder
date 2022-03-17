package com.example.medicationreminder.ShowMedication.model;

import com.example.medicationreminder.MedAdapter;
import com.example.medicationreminder.MedInfo;

import java.util.ArrayList;

public interface IShowMedicationPresenter {
    void onCalenderItemSlected(ArrayList<MedInfo> medList, MedAdapter medAdapter,int day, int month, int year);
}
