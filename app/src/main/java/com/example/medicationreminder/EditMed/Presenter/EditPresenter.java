package com.example.medicationreminder.EditMed.Presenter;

import android.util.Log;

import com.example.medicationreminder.AddMed.Model.RefillMed;
import com.example.medicationreminder.EditMed.Database.RepoInterface;
import com.example.medicationreminder.EditMed.Database.Repository;
import com.example.medicationreminder.Model.MedInfo;
import com.example.medicationreminder.Model.TimeOfMed;

import java.util.HashMap;
import java.util.List;

public class EditPresenter implements EditInterface {

    MedInfo medInfo=new MedInfo();
    RefillMed refillMed=new RefillMed();
    RepoInterface repo=new Repository();

    @Override
    public void getAllUpdatedDate(String medName, String medStartDate, String endDate, List<TimeOfMed> timesList,
                                  String userId,String medTakenUnit,String medUnit,String numOfTimes,
                                  String medId) {
        medInfo.setMedName(medName);
        medInfo.setStartDate(medStartDate);
        medInfo.setEndDate(endDate);
        medInfo.setTimeList(timesList);
        medInfo.setUserId(userId);
        medInfo.setMedUnit(medUnit);
        medInfo.setMedId(medId);
        medInfo.setMedTakenUnit(medTakenUnit);
        medInfo.setNumOfTimes(Integer.parseInt(numOfTimes));
        repo.editDataWithOutReffill(medInfo);
    }

    @Override
    public void getAllUpdatedDateWithRemind(String medName, String medStartDate, String endDate,
                                               List<TimeOfMed> timesList,String userId,String medTakenUnit,
                                               String medUnit,String numOfTimes, int medLeftNum, int medRemindNum,
                                               String remindTime,String medId) {
        medInfo.setMedName(medName);
        medInfo.setStartDate(medStartDate);
        medInfo.setMedId(medId);
        medInfo.setEndDate(endDate);
        medInfo.setTimeList(timesList);
        medInfo.setUserId(userId);
        medInfo.setMedUnit(medUnit);
        medInfo.setMedTakenUnit(medTakenUnit);
        medInfo.setNumOfTimes(Integer.parseInt(numOfTimes));
        refillMed.setNumOfRemind(medRemindNum);
        refillMed.setRemindTime(remindTime);
        refillMed.setpillLeftNum(medLeftNum);
        medInfo.setRefillMedData(refillMed);
        repo.editDataWithRefill(medInfo);
    }


}
