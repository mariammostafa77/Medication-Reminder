package com.example.medicationreminder.ShowMedication.Database;

import android.util.Log;

import com.example.medicationreminder.ShowMedication.model.MedInfo;
import com.example.medicationreminder.ShowMedication.model.TimeOfMed;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.List;

public class DeleteEditRepo implements RepositoryInterface {
    @Override
    public void deleteAllDoses(MedInfo medInfo) {
        DatabaseReference ref = FirebaseDatabase.getInstance().getReference();
        Query applesQuery = ref.child("MedicationData").orderByChild("medId")
                .equalTo(medInfo.getMedId());

        applesQuery.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for (DataSnapshot appleSnapshot: dataSnapshot.getChildren()) {
                    appleSnapshot.getRef().removeValue();
                }
                Log.e("TAG", "onDataChange");
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                Log.e("TAG", "onCancelled", databaseError.toException());
            }
        });
    }

    @Override
    public void deleteDose(String doseId) {
        DatabaseReference ref = FirebaseDatabase.getInstance().getReference();
        Query applesQuery = ref.child("MedicationData").child("timeList").orderByChild("doseId")
                .equalTo(doseId);

        Log.i("TAG","doseId= "+doseId);
        applesQuery.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for (DataSnapshot appleSnapshot: dataSnapshot.getChildren()) {
                    appleSnapshot.getRef().removeValue();
                }
                Log.i("TAG", "onDataChange");
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                Log.i("TAG", "onCancelled", databaseError.toException());
            }
        });
    }
}
