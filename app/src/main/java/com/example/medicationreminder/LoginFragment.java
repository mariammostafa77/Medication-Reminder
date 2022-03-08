package com.example.medicationreminder;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import android.util.Patterns;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;


public class LoginFragment extends Fragment {

  EditText edtEmail,edtPassword;
  Button btnLogin;
  private FirebaseAuth mAuth;

    public LoginFragment() {
        // Required empty public constructor
    }



    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mAuth=FirebaseAuth.getInstance();

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View v= inflater.inflate(R.layout.fragment_login, container, false);
        edtEmail=v.findViewById(R.id.edtEmailL);
        edtPassword=v.findViewById(R.id.edtPasswordL);
        btnLogin=v.findViewById(R.id.btnLogin);
        btnLogin.setOnClickListener(new View.OnClickListener() {
          @Override
          public void onClick(View v) {
            String email=edtEmail.getText().toString().trim();
            String password=edtPassword.getText().toString().trim();
            if(!email.isEmpty() && !password.isEmpty() &&Patterns.EMAIL_ADDRESS.matcher(email).matches()&&password.length()>=6){
              mAuth.signInWithEmailAndPassword(email,password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                @Override
                public void onComplete(@NonNull Task<AuthResult> task) {
                  if(task.isSuccessful()){
                    Intent i = new Intent(getActivity(), HomeActivity.class);
                    startActivity(i);

                  }
                  else{
                    Toast.makeText(getActivity(), "Email or Password is incorrect", Toast.LENGTH_SHORT).show();
                  }
                }
              });

            }
            else {
              if (email.isEmpty()) {
                edtEmail.setError("Email is required");
                edtEmail.requestFocus();
              }
              if (password.isEmpty()) {
                edtPassword.setError("Password is required");
                edtPassword.requestFocus();
              }
              if(!Patterns.EMAIL_ADDRESS.matcher(email).matches()){
                edtEmail.setError("please enter a valid email");
                edtEmail.requestFocus();
              }
              if(password.length()<6){
                edtPassword.setError("min password length is 6");
                edtPassword.requestFocus();
              }
            }

          }
        });
        return v;
    }
}