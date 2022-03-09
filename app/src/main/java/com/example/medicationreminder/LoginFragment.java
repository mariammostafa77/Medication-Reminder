package com.example.medicationreminder;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.NavDirections;
import androidx.navigation.Navigation;

import android.util.Log;
import android.util.Patterns;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.common.api.ApiException;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthCredential;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.GoogleAuthProvider;


public class LoginFragment extends Fragment {

  EditText edtEmail,edtPassword;
  Button btnLogin;
  private FirebaseAuth mAuth;
  TextView txtResetPassword;
  NavController navController;
  NavDirections navDirections;
  ImageView imgGoogle;
  public static final String fileName="login";
  public static final String myEmail="email";
  public static final String myPassword="password";
  SharedPreferences sharedPreferences;
  private GoogleSignInClient mGoogleSignInClient;
  private final static int RC_SIGN_IN=123;

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
        txtResetPassword=v.findViewById(R.id.txtResetPassword);
      imgGoogle=v.findViewById(R.id.imgGoogle);
      sharedPreferences= getActivity().getSharedPreferences(fileName, Context.MODE_PRIVATE);

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
                    SharedPreferences.Editor editor=sharedPreferences.edit();
                    editor.putString(myEmail,email);
                    editor.putString(myPassword,password);
                    editor.commit();
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
        txtResetPassword.setOnClickListener(new View.OnClickListener() {
          @Override
          public void onClick(View v) {
            navController= Navigation.findNavController(v);
            navDirections=LoginFragmentDirections.loginResetDect();
            navController.navigate(navDirections);
          }
        });


      return v;
    }



}