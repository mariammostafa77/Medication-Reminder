package com.example.medicationreminder.AddMed.Presenter;

import android.util.Log;

import com.example.medicationreminder.AddMed.DataBase.RepoInterface;
import com.example.medicationreminder.AddMed.DataBase.Repository;
import com.example.medicationreminder.AddMed.Model.MedData;
import com.example.medicationreminder.AddMed.Model.MedDataDay;
import com.example.medicationreminder.AddMed.Model.RefillMed;

import java.util.List;

public class AddMedPresenter<T> implements PresenterInterface {

    private MedData medData=new MedData();
    private RepoInterface myRepo=new Repository();
    @Override
    public MedDataDay setDataOfEachTime(String time, String dose) {
        MedDataDay medDataDay=new MedDataDay(time,dose);
        Log.i("TAG","time from presenter= "+time+" dose= ");
        return medDataDay;
    }

    @Override
    public MedData setMedDataWithOutRefillReminder(String medName, String medUnit, String startDate, String endDate, String userId, String medId, int numOfMed, String timeUnitChoice, List timeList) {
        medData.setMedName(medName);
        medData.setMedUnit(medUnit);
        medData.setStartDate(startDate);
        medData.setEndDate(endDate);
        medData.setUserId(userId);
        medData.setMedId(medId);
        medData.setNumOfTimes(numOfMed);
        medData.setMedTakenUnit(timeUnitChoice);
        medData.setTimeList(timeList);
        return medData;
    }
    @Override
    public String getTimeUnit() {
        return medData.getMedTakenUnit();
    }
    @Override
    public String SetDadaIntoDatabase(MedData medData) {
        return myRepo.addMed(medData);
    }


    @Override
    public RefillMed setRefiledData(String remindTime, int pillLeftNum, int numOfRemind) {
        RefillMed refillMed=new RefillMed(remindTime,pillLeftNum, numOfRemind);
        return refillMed;
    }

    @Override
    public MedData setMedDataWithRefillReminder(String medName, String medUnit, String startDate,
                                                String endDate, String userId, String medId, int numOfMed,
                                                String timeUnitChoice, List timeList, RefillMed refillMed) {
        medData.setMedName(medName);
        medData.setMedUnit(medUnit);
        medData.setStartDate(startDate);
        medData.setEndDate(endDate);
        medData.setUserId(userId);
        medData.setMedId(medId);
        medData.setNumOfTimes(numOfMed);
        medData.setMedTakenUnit(timeUnitChoice);
        medData.setTimeList(timeList);
        medData.setRefillMedData(refillMed);
        return medData;
    }


}

















