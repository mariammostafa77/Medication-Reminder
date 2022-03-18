package com.example.medicationreminder.loginmodel;



public interface ILoginModel {
    boolean userLogin(LoginModel.DatabaseCallback databaseCallback, String email, String password);
    boolean loginWithGoogle(LoginModel.DatabaseCallback databaseCallback,String idToken);
}
