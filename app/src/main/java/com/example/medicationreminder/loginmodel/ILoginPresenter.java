package com.example.medicationreminder.loginmodel;

import android.content.SharedPreferences;

public interface ILoginPresenter {
    void onLoginBtnClick(String email, String password);
    void onLoginGoogleBtnClick(String idToken);
}
