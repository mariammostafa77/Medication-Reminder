package com.example.medicationreminder.AddMed.Presenter;

import com.example.medicationreminder.AddMed.Model.MedData;
import com.example.medicationreminder.AddMed.Model.MedDataDay;
import com.example.medicationreminder.AddMed.Model.RefillMed;

import java.util.List;

public interface PresenterInterface<T> {
    MedDataDay setDataOfEachTime(String time,String dose);
    MedData setMedDataWithOutRefillReminder(String medName, String medUnit, String startDate, String endDate
            , String userId, String medId, int numOfMed, String timeUnitChoice, List<T> timeList );
    String SetDadaIntoDatabase(MedData medData);
    String getTimeUnit();

    RefillMed setRefiledData( String remindTime,int pillLeftNum,int numOfRemind);
    MedData setMedDataWithRefillReminder(String medName, String medUnit, String startDate, String endDate
            , String userId, String medId, int numOfMed, String timeUnitChoice, List<T> timeList, RefillMed refillMed);


}
