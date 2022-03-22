package com.example.medicationreminder.ShowMedication.Database;

import android.util.Log;

import com.example.medicationreminder.Model.MedInfo;
import com.example.medicationreminder.Model.TimeOfMed;
import com.example.medicationreminder.ShowMedication.View.MedAdapter;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class DeleteEditRepo implements RepositoryInterface {
    static int count;
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
    public void deleteDose(MedInfo medInfo,String doseId) {
        count=medInfo.getTimeList().size();
        DatabaseReference ref = FirebaseDatabase.getInstance().getReference();
        Query applesQuery = ref.child("MedicationData").child(medInfo.getMedId()).child("timeList").orderByChild("doseId")
                .equalTo(doseId);

        if(count==1){
            deleteAllDoses(medInfo);

        } else{
            applesQuery.addListenerForSingleValueEvent(new ValueEventListener() {
                @Override
                public void onDataChange(DataSnapshot dataSnapshot) {
                    for (DataSnapshot appleSnapshot : dataSnapshot.getChildren()) {
                        appleSnapshot.getRef().removeValue();
                    }
                    List<TimeOfMed> myList=new ArrayList<>();

                    for (int i = 0; i < medInfo.getTimeList().size(); i++) {

                        if (medInfo.getTimeList().get(i).getDoseId()!=doseId){
                            myList.add(medInfo.getTimeList().get(i));
                        }
                        else{
                            count--;
                        }
                        Log.i("TAG","Size of list"+myList.size());
                        Log.i("TAG","count = "+count);

                    }
                    DatabaseReference myRef = FirebaseDatabase.getInstance().getReference().child("MedicationData");
                    HashMap hashMap=new HashMap();
                    hashMap.put("medName",medInfo.getMedName());
                    hashMap.put("startDate",medInfo.getStartDate());
                    hashMap.put("endDate",medInfo.getEndDate());
                    hashMap.put("medTakenUnit",medInfo.getMedTakenUnit());
                    hashMap.put("medUnit",medInfo.getMedUnit());
                    hashMap.put("numOfTimes",count);
                    hashMap.put("userId",medInfo.getUserId());
                    hashMap.put("timeList",myList);
                    medInfo.setRefillMedData(medInfo.getRefillMedData());
                    myRef.child(medInfo.getMedId()).updateChildren(hashMap).addOnSuccessListener(new OnSuccessListener<Void>() {
                        @Override
                        public void onSuccess(Void unused) {
                            Log.i("TAG","success");
                        }
                    });



                }

                @Override
                public void onCancelled(DatabaseError databaseError) {
                    Log.i("TAG", "onCancelled", databaseError.toException());
                }
            });
        }
        Log.i("TAG", "count = " + count);
    }
}
