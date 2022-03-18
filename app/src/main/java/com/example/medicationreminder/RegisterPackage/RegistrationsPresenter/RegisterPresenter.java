package com.example.medicationreminder.RegisterPackage.RegistrationsPresenter;

import com.example.medicationreminder.RegisterPackage.RegistrationsModel.IRegisterModel;
import com.example.medicationreminder.RegisterPackage.RegistrationsModel.IRegisterPresenter;
import com.example.medicationreminder.RegisterPackage.RegistrationsModel.IRegistersView;
import com.example.medicationreminder.RegisterPackage.RegistrationsModel.RegisterModel;


public class RegisterPresenter implements IRegisterPresenter {
    IRegisterModel model;
    IRegistersView view;

    public RegisterPresenter(IRegistersView view) {
        this.view = view;
        model=new RegisterModel();
    }

    @Override
    public void onRegistrationBtnClick(String username, String phone, String email, String password) {
        boolean result=model.insertNewUser(new RegisterModel.DatabaseCallback() {
            @Override
            public void onCallback(boolean result) {
                view.goToLogin(result);
            }
        }, username, phone, email, password);
    }

    @Override
    public void onRegistrationGoogleBtnClick(String idToken) {
        boolean result=model.registerWithGoogle(new RegisterModel.DatabaseCallback() {
            @Override
            public void onCallback(boolean result) {
                view.goToLogin(result);
            }
        },idToken);
    }
}
