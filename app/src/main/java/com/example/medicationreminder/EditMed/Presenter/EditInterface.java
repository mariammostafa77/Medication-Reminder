package com.example.medicationreminder.EditMed.Presenter;

import com.example.medicationreminder.Model.MedInfo;
import com.example.medicationreminder.Model.TimeOfMed;

import java.util.List;

public interface EditInterface {
    void getAllUpdatedDate(String medName, String medStartDate, String endDate, List<TimeOfMed> timesList,
                                     String userId,String medTakenUnit,String medUnit,String numOfTimes,String medId);
    void getAllUpdatedDateWithRemind(String medName, String medStartDate, String endDate,
                                               List<TimeOfMed> timesList,String userId,String medTakenUnit,
                                               String medUnit,String numOfTimes, int medLeftNum, int medRemindNum,
                                               String remindTime,String medId);
}
