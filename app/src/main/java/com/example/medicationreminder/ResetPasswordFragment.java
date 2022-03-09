package com.example.medicationreminder;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.NavDirections;
import androidx.navigation.Navigation;

import android.util.Patterns;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;


public class ResetPasswordFragment extends Fragment {

EditText edtRestPassword;
Button btnReset;
private FirebaseAuth mAuth;
    NavController navController;
    NavDirections navDirections;

    public ResetPasswordFragment() {
        // Required empty public constructor
    }




    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mAuth= FirebaseAuth.getInstance();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v=inflater.inflate(R.layout.fragment_reset_password, container, false);
        edtRestPassword=v.findViewById(R.id.edtRestPassword);
        btnReset=v.findViewById(R.id.btnReset);
        btnReset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               String email=edtRestPassword.getText().toString().trim();
               if(!email.isEmpty() && Patterns.EMAIL_ADDRESS.matcher(email).matches()){
                   mAuth.sendPasswordResetEmail(email).addOnCompleteListener(new OnCompleteListener<Void>() {
                       @Override
                       public void onComplete(@NonNull Task<Void> task) {
                           if(task.isSuccessful()){
                               Toast.makeText(getActivity(), "Please Check your Email to reset your password", Toast.LENGTH_SHORT).show();
                               navController= Navigation.findNavController(v);
                               navDirections=ResetPasswordFragmentDirections.resetPasswordLoginDect();
                               navController.navigate(navDirections);
                           }
                           else{
                               Toast.makeText(getActivity(), "Try again something wrong happen!", Toast.LENGTH_SHORT).show();
                           }
                       }
                   });
               }
               else{
                   if(email.isEmpty()){
                       edtRestPassword.setError("Email is required");
                       edtRestPassword.requestFocus();
                   }
                   if(!Patterns.EMAIL_ADDRESS.matcher(email).matches()){
                       edtRestPassword.setError("please enter a valid email");
                       edtRestPassword.requestFocus();

                   }
               }
            }
        });
        return v;
    }
}