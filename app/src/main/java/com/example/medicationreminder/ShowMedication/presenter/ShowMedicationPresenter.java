package com.example.medicationreminder.ShowMedication.presenter;

import android.util.Log;

import com.example.medicationreminder.ClickListenerInterface;
import com.example.medicationreminder.MedAdapter;
import com.example.medicationreminder.MedInfo;
import com.example.medicationreminder.ShowMedication.model.IShowMedicationPresenter;
import com.example.medicationreminder.ShowMedication.model.IShowMedicationView;
import com.example.medicationreminder.ShowMedication.model.IshowMedicationModel;
import com.example.medicationreminder.ShowMedication.model.ShowMedicationModel;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class ShowMedicationPresenter implements IShowMedicationPresenter , ClickListenerInterface {
    IshowMedicationModel model;
    IShowMedicationView view;

    public ShowMedicationPresenter(IShowMedicationView view) {

        this.view = view;
        model=new ShowMedicationModel();
    }

    @Override
    public void onCalenderItemSlected(ArrayList<MedInfo> medList, MedAdapter medAdapter, int day, int month, int year) {
        model.getMed(medList,medAdapter,day,month,year);
        view.showMed();
    }

    @Override
    public void onDeleteClick(MedInfo medInfo) {
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
    public void onEditClick(MedInfo medInfo) {
        Log.e("TAG", "onEditClick");
    }
}
