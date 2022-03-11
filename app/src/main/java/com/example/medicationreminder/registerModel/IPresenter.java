package com.example.medicationreminder.registerModel;

public interface IPresenter {
    void onRegistrationBtnClick(String username,String phone,String email,String password);
    void onRegistrationGoogleBtnClick(String idToken);
}
