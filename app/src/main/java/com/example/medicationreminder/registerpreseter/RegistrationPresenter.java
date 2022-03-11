package com.example.medicationreminder.registerpreseter;

import android.util.Log;

import com.example.medicationreminder.registerModel.IModel;
import com.example.medicationreminder.registerModel.IPresenter;
import com.example.medicationreminder.registerModel.IView;
import com.example.medicationreminder.registerModel.RegistrationModel;

public class RegistrationPresenter implements IPresenter {
    IModel model;
   IView view;


    public RegistrationPresenter(IView view) {
        this.view = view;
        model=new RegistrationModel();
    }

    @Override
    public void onRegistrationBtnClick(String username,String phone,String email,String password) {
        boolean result=model.insertNewUser(new RegistrationModel.DatabaseCallback() {
            @Override
            public void onCallback(boolean result) {
                view.goToHome(result);
            }
        }, username, phone, email, password);

        Log.i("TAG",""+result+ "1");

    }

    @Override
    public void onRegistrationGoogleBtnClick(String idToken) {
        boolean result=model.registerWithGoogle(new RegistrationModel.DatabaseCallback() {
            @Override
            public void onCallback(boolean result) {
                view.goToHome(result);
            }
        },idToken);

    }
}
