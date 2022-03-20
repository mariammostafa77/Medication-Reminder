package com.example.medicationreminder.ShowMedication.View;

import com.example.medicationreminder.Model.MedInfo;

public interface ClickListenerInterface {
     void onDeleteAllClick(MedInfo medInfo);
     void onDeleteDoseClick(MedInfo medInfo,String doseId);
     void onEditClick(MedInfo medInfo);
}
