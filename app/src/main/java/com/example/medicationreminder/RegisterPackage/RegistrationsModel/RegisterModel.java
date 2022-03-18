package com.example.medicationreminder.RegisterPackage.RegistrationsModel;

import androidx.annotation.NonNull;

import com.example.medicationreminder.User;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthCredential;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.GoogleAuthProvider;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.Map;

public class RegisterModel implements IRegisterModel{
    boolean result =false;
    boolean result2 =false;
    FirebaseAuth mAuth=FirebaseAuth.getInstance();

    public RegisterModel() {
    }

    @Override
    public boolean insertNewUser(RegisterModel.DatabaseCallback databaseCallback, String username, String phone, String email, String password) {
        mAuth.createUserWithEmailAndPassword(email, password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if (task.isSuccessful()) {
                    //firstore
                    String userID=mAuth.getCurrentUser().getUid();
                    String specialID=username+userID;
                    FirebaseFirestore firebaseFirestore= FirebaseFirestore.getInstance();
                    DocumentReference documentReference=firebaseFirestore.collection("users")
                            .document(userID);
                    Map<String,Object> myUser=new HashMap<>();
                    myUser.put("username",username);
                    myUser.put("phone",phone);
                    myUser.put("email",email);
                    myUser.put("password",password);
                    myUser.put("password",specialID);

                    documentReference.set(myUser).addOnCompleteListener(new OnCompleteListener<Void>() {
                        @Override
                        public void onComplete(@NonNull Task<Void> task) {

                        }
                    });
                    User user = new User(username, phone, email, password,specialID);
                    FirebaseDatabase.getInstance().getReference("Users")
                            .child(FirebaseAuth.getInstance().getCurrentUser().getUid())

                            .setValue(user).addOnCompleteListener(new OnCompleteListener<Void>() {
                        @Override
                        public void onComplete(@NonNull Task<Void> task) {
                            if (task.isSuccessful()) {

                                result =true;
                                databaseCallback.onCallback(result);
                            } else {
                                result =false;
                                databaseCallback.onCallback(result);
                            }
                        }
                    });
                } else {
                    result =false;
                    databaseCallback.onCallback(result);


                }
            }
        });
        return result;
    }

    @Override
    public boolean registerWithGoogle(RegisterModel.DatabaseCallback databaseCallback, String idToken) {
        AuthCredential credential = GoogleAuthProvider.getCredential(idToken, null);
        mAuth.signInWithCredential(credential)
                .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            result =true;
                            databaseCallback.onCallback(result);

                        } else {
                            result =false;
                            databaseCallback.onCallback(result);

                        }
                    }
                });
        return result2;
    }
    public interface DatabaseCallback{
        void onCallback(boolean result);
    }
}
