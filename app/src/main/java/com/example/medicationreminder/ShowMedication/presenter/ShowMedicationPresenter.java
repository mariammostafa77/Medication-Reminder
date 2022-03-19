package com.example.medicationreminder.ShowMedication.presenter;

import android.util.Log;

import com.example.medicationreminder.ShowMedication.View.ClickListenerInterface;
import com.example.medicationreminder.ShowMedication.Database.DeleteEditRepo;
import com.example.medicationreminder.ShowMedication.View.MedAdapter;
import com.example.medicationreminder.ShowMedication.model.MedInfo;
import com.example.medicationreminder.ShowMedication.Database.RepositoryInterface;
import com.example.medicationreminder.ShowMedication.model.IShowMedicationPresenter;
import com.example.medicationreminder.ShowMedication.model.IShowMedicationView;
import com.example.medicationreminder.ShowMedication.model.IshowMedicationModel;
import com.example.medicationreminder.ShowMedication.model.ShowMedicationModel;
import com.example.medicationreminder.ShowMedication.model.TimeOfMed;

import java.util.ArrayList;
import java.util.List;

public class ShowMedicationPresenter implements IShowMedicationPresenter , ClickListenerInterface {
    IshowMedicationModel model;
    IShowMedicationView view;
    RepositoryInterface repo=new DeleteEditRepo();

    public ShowMedicationPresenter(IShowMedicationView view) {

        this.view = view;
        model=new ShowMedicationModel();
    }
    public ShowMedicationPresenter() {
    }

    @Override
    public void onCalenderItemSlected(ArrayList<MedInfo> medList, MedAdapter medAdapter, int day, int month, int year) {
        model.getMed(medList,medAdapter,day,month,year);
        view.showMed();
    }

    @Override
    public void onDeleteAllClick(MedInfo medInfo) {
        repo.deleteAllDoses(medInfo);
    }

    @Override
    public void onDeleteDoseClick(String doseId) {
        repo.deleteDose(doseId);
    }

    @Override
    public void onEditClick(MedInfo medInfo) {
        Log.e("TAG", "onEditClick");
    }


}
