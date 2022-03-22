package com.example.medicationreminder.EditMed.Database;

import android.util.Log;

import androidx.annotation.NonNull;

import com.example.medicationreminder.Model.MedInfo;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;

import java.util.HashMap;

public class Repository implements RepoInterface {
    String result;
    DatabaseReference myRef = FirebaseDatabase.getInstance().getReference().child("MedicationData");

    @Override
    public String editDataWithOutReffill(MedInfo medInfo) {
        HashMap hashMap=new HashMap();
        hashMap.put("medName",medInfo.getMedName());
        hashMap.put("startDate",medInfo.getStartDate());
        hashMap.put("endDate",medInfo.getEndDate());
        hashMap.put("medTakenUnit",medInfo.getMedTakenUnit());
        hashMap.put("medUnit",medInfo.getMedUnit());
        hashMap.put("numOfTimes",medInfo.getNumOfTimes());
        hashMap.put("userId",medInfo.getUserId());
        hashMap.put("timeList",medInfo.getTimeList());
        myRef.child(medInfo.getMedId()).updateChildren(hashMap).addOnSuccessListener(new OnSuccessListener<Void>() {
            @Override
            public void onSuccess(Void unused) {
                Log.i("TAG","success");
            }
        });
        return result;
    }

    @Override
    public String editDataWithRefill(MedInfo medInfo) {
        HashMap hashMap=new HashMap();
        hashMap.put("medName",medInfo.getMedName());
        hashMap.put("startDate",medInfo.getStartDate());
        hashMap.put("endDate",medInfo.getEndDate());
        hashMap.put("medTakenUnit",medInfo.getMedTakenUnit());
        hashMap.put("medUnit",medInfo.getMedUnit());
        hashMap.put("numOfTimes",medInfo.getNumOfTimes());
        hashMap.put("userId",medInfo.getUserId());
        hashMap.put("timeList",medInfo.getTimeList());
        medInfo.setRefillMedData(medInfo.getRefillMedData());
        myRef.child(medInfo.getMedId()).updateChildren(hashMap).addOnSuccessListener(new OnSuccessListener<Void>() {
            @Override
            public void onSuccess(Void unused) {
                Log.i("TAG","success");
            }
        });
        return result;
    }
}
