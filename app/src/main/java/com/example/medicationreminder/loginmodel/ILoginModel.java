package com.example.medicationreminder.loginmodel;

import android.content.SharedPreferences;



public interface ILoginModel {
    boolean userLogin(LoginModel.DatabaseCallback databaseCallback, String email, String password);
    boolean loginWithGoogle(LoginModel.DatabaseCallback databaseCallback,String idToken);
}
