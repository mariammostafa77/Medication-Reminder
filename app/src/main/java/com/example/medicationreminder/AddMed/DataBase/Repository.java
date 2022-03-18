package com.example.medicationreminder.AddMed.DataBase;

import androidx.annotation.NonNull;

import com.example.medicationreminder.AddMed.Model.MedData;
import com.example.medicationreminder.AddMed.Model.MedDataDay;
import com.example.medicationreminder.AddMed.Model.MedDataMonth;
import com.example.medicationreminder.AddMed.Model.MedDataWeek;
import com.example.medicationreminder.AddMed.Model.RefillMed;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class Repository implements RepoInterface {
    DatabaseReference mDatabase=FirebaseDatabase.getInstance().getReference();
    FirebaseUser currentFirebaseUser=FirebaseAuth.getInstance().getCurrentUser() ;
    String id= currentFirebaseUser.getUid();
    MedData medData=new MedData();
    MedDataDay medDataDay=new MedDataDay();
    MedDataWeek medDataWeek=new MedDataWeek();
    MedDataMonth medDataMonth=new MedDataMonth();
    RefillMed refillMed=new RefillMed();
    String result;




    public String addMed(MedData medData){
        mDatabase.child("MedicationData").push().setValue(medData).addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {
                result ="Added Successfully";
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                result ="Add Failed";
            }
        });
        return result;
    }
    /*public String addMedWithRefillInfo(MedData medData){
        final String[] result = new String[1];
        mDatabase.child("MedicationData").push().setValue(medData).addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {
                result[0] ="Added Successfully";
            }
        });
        result[0] ="Add Failed";
        return result[0];
    }*/
}
