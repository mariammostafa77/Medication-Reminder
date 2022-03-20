package com.example.medicationreminder.EditMed.Presenter;

import com.example.medicationreminder.Model.MedInfo;
import com.example.medicationreminder.Model.TimeOfMed;

import java.util.List;

public interface EditInterface {
    public MedInfo getAllUpdatedDate(String medName, String medStartDate, String endDate, List<TimeOfMed> timesList);
    public MedInfo getAllUpdatedDateWithRemind(String medName,String medStartDate,String endDate,List<TimeOfMed>timesList,int medLeftNum,int medRemindNum, String remindTime);
}
