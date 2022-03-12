package com.example.medicationreminder.loginmodel;

import android.content.Intent;
import android.content.SharedPreferences;
import android.widget.Toast;

import androidx.annotation.NonNull;

import com.example.medicationreminder.HomeActivity;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthCredential;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.GoogleAuthProvider;

public class LoginModel implements ILoginModel {
    boolean result=false;
    boolean result2=false;


    public LoginModel() {
    }
    FirebaseAuth mAuth=FirebaseAuth.getInstance();

    @Override
    public boolean userLogin(DatabaseCallback databaseCallback, String email, String password) {
        mAuth.signInWithEmailAndPassword(email,password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if(task.isSuccessful()){

                    result =true;
                    databaseCallback.onCallback(result);

                }
                else{
                    result =false;
                    databaseCallback.onCallback(result);
                }
            }
        });

        return result;
    }

    @Override
    public boolean loginWithGoogle(DatabaseCallback databaseCallback, String idToken) {
        AuthCredential credential = GoogleAuthProvider.getCredential(idToken, null);
        mAuth.signInWithCredential(credential)
                .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            // Sign in success, update UI with the signed-in user's information
                            result2 =true;
                            databaseCallback.onCallback(result2);
                        } else {
                            result2 =false;
                            databaseCallback.onCallback(result2);
                        }
                    }
                });
        return result2;
    }

    public interface DatabaseCallback{
        void onCallback(boolean result);
    }
}
