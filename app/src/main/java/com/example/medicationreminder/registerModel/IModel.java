package com.example.medicationreminder.registerModel;

public interface IModel {
    boolean insertNewUser(RegistrationModel.DatabaseCallback databaseCallback,String username, String phone, String email, String password);
    boolean registerWithGoogle(RegistrationModel.DatabaseCallback databaseCallback,String idToken);
}
