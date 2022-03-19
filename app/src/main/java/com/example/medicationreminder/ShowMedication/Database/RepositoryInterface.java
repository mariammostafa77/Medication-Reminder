package com.example.medicationreminder.ShowMedication.Database;

import com.example.medicationreminder.ShowMedication.model.MedInfo;
import com.example.medicationreminder.ShowMedication.model.TimeOfMed;

import java.util.List;

public interface RepositoryInterface {
    void deleteAllDoses(MedInfo medInfo);
    void deleteDose(String doseId);
}
