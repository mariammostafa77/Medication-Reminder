package com.example.medicationreminder.ShowMedication.View;

import com.example.medicationreminder.ShowMedication.model.MedInfo;
import com.example.medicationreminder.ShowMedication.model.TimeOfMed;

import java.util.List;

public interface ClickListenerInterface {
     void onDeleteAllClick(MedInfo medInfo);
     void onDeleteDoseClick(String doseId);
     void onEditClick(MedInfo medInfo);
}
