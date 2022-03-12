package com.example.medicationreminder.loginmodel;

import android.content.SharedPreferences;

import com.example.medicationreminder.registerModel.RegistrationModel;

public interface ILoginModel {
    boolean userLogin(LoginModel.DatabaseCallback databaseCallback, String email, String password);
    boolean loginWithGoogle(LoginModel.DatabaseCallback databaseCallback,String idToken);
}
