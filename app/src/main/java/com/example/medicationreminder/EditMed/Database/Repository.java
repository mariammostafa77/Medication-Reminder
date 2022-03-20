package com.example.medicationreminder.EditMed.Database;

import android.util.Log;

import androidx.annotation.NonNull;

import com.example.medicationreminder.Model.MedInfo;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;

public class Repository implements RepoInterface {
    @Override
    public String editData(MedInfo medInfo) {
        final String[] result = {""};
        DatabaseReference myRef = FirebaseDatabase.getInstance().getReference().child("MedicationData");
        myRef.child(medInfo.getMedId()).setValue(medInfo).addOnSuccessListener(new OnSuccessListener<Void>() {
            @Override
            public void onSuccess(Void unused) {
                result[0] ="success";
                Log.i("TAG","success");
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                result[0] ="failed";
                Log.i("TAG","failed");

            }
        });
        Log.i("TAG",result[0]);

        return result[0];
    }
}
