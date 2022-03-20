package com.example.medicationreminder.EditMed.Presenter;

import com.example.medicationreminder.AddMed.Model.RefillMed;
import com.example.medicationreminder.Model.MedInfo;
import com.example.medicationreminder.Model.TimeOfMed;

import java.util.List;

public class EditPresenter implements EditInterface {

    MedInfo medInfo=new MedInfo();
    RefillMed refillMed=new RefillMed();

    @Override
    public MedInfo getAllUpdatedDate(String medName, String medStartDate, String endDate, List<TimeOfMed> timesList) {
        medInfo.setMedName(medName);
        medInfo.setStartDate(medStartDate);
        medInfo.setEndDate(endDate);
        medInfo.setTimeList(timesList);
        return medInfo;
    }

    @Override
    public MedInfo getAllUpdatedDateWithRemind(String medName, String medStartDate, String endDate, List<TimeOfMed> timesList, int medLeftNum, int medRemindNum, String remindTime) {
        medInfo.setMedName(medName);
        medInfo.setStartDate(medStartDate);
        medInfo.setEndDate(endDate);
        medInfo.setTimeList(timesList);
        refillMed.setNumOfRemind(medRemindNum);
        refillMed.setRemindTime(remindTime);
        refillMed.setpillLeftNum(medLeftNum);
        medInfo.setRefillMedData(refillMed);
        return medInfo;
    }
}
