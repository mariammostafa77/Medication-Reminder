package com.example.medicationreminder.EditMed.Database;

import com.example.medicationreminder.Model.MedInfo;

public interface RepoInterface {
    String editDataWithOutReffill(MedInfo medInfo);
    String editDataWithRefill(MedInfo medInfo);
}
