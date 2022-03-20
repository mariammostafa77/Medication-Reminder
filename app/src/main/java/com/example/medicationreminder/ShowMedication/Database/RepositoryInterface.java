package com.example.medicationreminder.ShowMedication.Database;

import com.example.medicationreminder.Model.MedInfo;

public interface RepositoryInterface {
    void deleteAllDoses(MedInfo medInfo);
    void deleteDose(MedInfo medInfo,String doseId);
}
