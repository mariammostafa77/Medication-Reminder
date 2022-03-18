package com.example.medicationreminder.RegisterPackage.RegistrationsModel;



public interface IRegisterModel {
    boolean insertNewUser(RegisterModel.DatabaseCallback databaseCallback, String username, String phone, String email, String password);
    boolean registerWithGoogle(RegisterModel.DatabaseCallback databaseCallback,String idToken);

}
