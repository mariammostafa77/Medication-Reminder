package com.example.medicationreminder.RegisterPackage.RegistrationsModel;

public interface IRegisterPresenter {
    void onRegistrationBtnClick(String username,String phone,String email,String password);
    void onRegistrationGoogleBtnClick(String idToken);
}
