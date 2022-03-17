package com.example.medicationreminder.ShowMedication.presenter;

import com.example.medicationreminder.MedAdapter;
import com.example.medicationreminder.MedInfo;
import com.example.medicationreminder.ShowMedication.model.IShowMedicationPresenter;
import com.example.medicationreminder.ShowMedication.model.IShowMedicationView;
import com.example.medicationreminder.ShowMedication.model.IshowMedicationModel;
import com.example.medicationreminder.ShowMedication.model.ShowMedicationModel;

import java.util.ArrayList;

public class ShowMedicationPresenter implements IShowMedicationPresenter {
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
}
