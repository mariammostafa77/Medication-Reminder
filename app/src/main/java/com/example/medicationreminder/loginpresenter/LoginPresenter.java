package com.example.medicationreminder.loginpresenter;

import android.content.SharedPreferences;

import com.example.medicationreminder.loginmodel.ILoginModel;
import com.example.medicationreminder.loginmodel.ILoginPresenter;
import com.example.medicationreminder.loginmodel.ILoginView;
import com.example.medicationreminder.loginmodel.LoginModel;
import com.example.medicationreminder.registerModel.RegistrationModel;

public class LoginPresenter implements ILoginPresenter {
    ILoginModel model;
    ILoginView view;
    public LoginPresenter(ILoginView view){
        this.view=view;
        model=new LoginModel();

    }

    @Override
    public void onLoginBtnClick(String email, String password) {
        boolean result=model.userLogin(new LoginModel.DatabaseCallback() {
            @Override
            public void onCallback(boolean result) {
                view.goToHome(result);
            }
        },email,password);
    }

    @Override
    public void onLoginGoogleBtnClick(String idToken) {
        boolean result=model.loginWithGoogle(new LoginModel.DatabaseCallback() {
            @Override
            public void onCallback(boolean result) {
                view.goToHome(result);
            }
        },idToken);
    }
}
