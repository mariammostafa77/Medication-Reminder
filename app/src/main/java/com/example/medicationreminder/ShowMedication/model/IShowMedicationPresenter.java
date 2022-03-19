package com.example.medicationreminder.ShowMedication.model;

import com.example.medicationreminder.Model.MedInfo;
import com.example.medicationreminder.ShowMedication.View.MedAdapter;

import java.util.ArrayList;

public interface IShowMedicationPresenter {
    void onCalenderItemSlected(ArrayList<MedInfo> medList, MedAdapter medAdapter, int day, int month, int year);
}
