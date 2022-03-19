package com.example.medicationreminder.ShowMedication.View;

import com.example.medicationreminder.Model.MedInfo;

public interface ClickListenerInterface {
     void onDeleteAllClick(MedInfo medInfo);
     void onDeleteDoseClick(String doseId);
     void onEditClick(MedInfo medInfo);
}
